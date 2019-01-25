package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * The DriveTrain Subsystem is where the drivetrain is bound to the code
 * through the motors created in RobotMap, which are stored in a Differential
 * Drive Varible
 * 
 * @author CRahne and Wayne
 */
public class Drivetrain extends Subsystem {

  /* IMPORTED VARIBLE FROM ROBOTMAP DELCARATIONS */
  // Motors
  private final WPI_TalonSRX mLeftMaster = RobotMap.DrivetrainLeftMaster;
  private final VictorSPX mLeftSlave = RobotMap.DrivetrainLeftSlave;
  private final WPI_TalonSRX mRightMaster = RobotMap.DrivetrainRightMaster;
  private final VictorSPX mRightSlave = RobotMap.DrivetrainRightSlave;
  
  // Speed Controller Groups
  private final SpeedControllerGroup mLeft = RobotMap.DrivetrainLeft;
  private final SpeedControllerGroup mRight = RobotMap.DrivetrainRight;
  
  // The drivetrain object (for mDrive.tankDrive)
  private final DifferentialDrive mDrive = RobotMap.DrivetrainDifferential;
  
  // Gyro
  private final AHRS mGyro = RobotMap.Drivetrain_Gyro;
  private double kMaxSpeed = Constants.kMaxSpeed;
  private double kSlowSpeed = Constants.kSlowSpeed;
  
  /* IMPORTED VARIBLES FROM CONSTANTS DECLARATION */
  // Vision
  public boolean TargetAligned;
  
  // PID Constants
  private final double kP = Constants.kDrivetrainP;
  private final double kI = Constants.kDrivetrainI;
  private final double kD = Constants.kDrivetrainD;
  
  /* PRIVATE MATH VARIBLES FOR PID */
  private double mSetpoint = 0; // math (example in AlignWithTargetPID.java)
  private double error = 0;
  private double previousError = 0;

  /**
   * Add children to the drivetrain class
   */
  public Drivetrain() {
    setName("Drivetrain");
    addChild(mLeftMaster);
    addChild(mLeftSlave);
    addChild(mRightMaster);
    addChild(mRightSlave);
  }

  /**
   * Will drive forward at 95%.
   */
  public void driveForward() {
    mDrive.tankDrive(kMaxSpeed, kMaxSpeed);
  }

  /**
   * Will drive forward at 65%.
   */
  public void driveForwardSlow() {
    mDrive.tankDrive(kSlowSpeed, kSlowSpeed);
  }

  /**
   * Will drive in reverse at 95%. (probably not a good idea though)
   */
  public void driveReverse() {
    mDrive.tankDrive(-kMaxSpeed, -kMaxSpeed);
  }

  /**
   * Will drive in reverse at 65%. 
   * More recomeended than driveReverse()
   */
  public void driveReverseSlow() {
    mDrive.tankDrive(-kSlowSpeed, -kSlowSpeed);
  }

  /**
   * OperatorDrive is the Method for driving. It uses the differential 
   * drive varible that was created in RobotMap.java. It will grab the 
   * Y-Axis and Z-Axis of the OPStick in OI.java, then drive the robot.
   * 
   * @param Joystick stick
   */
  public void OperatorDrive(Joystick stick) {
    mDrive.arcadeDrive(-stick.getY(), -stick.getZ());
    // mDrive.arcadeDrive(-stick.getY(), 0);
  }

  /**
   * The driveForward method from the 2018 code
   * 
   * @param targetDistance
   */
  public void driveForward(double targetDistance) {
    // Will Re-Zero all sensors
    sensorReset();
    // Math for the Target
    double wheelDiameter = 6;
    double target = (targetDistance/(wheelDiameter*Math.PI))*3*360;
    
    Timer.delay(0.1);
    // continue if the average encoder counts is less than or equal to the modified target distance
    while(getEncoderAverageValue() / 2 <= target) {
      mDrive.arcadeDrive(0.7, 0.7); // Drive at 70 percent power
    }
    mDrive.tankDrive(0, 0); // Stop at the end
  }

  /**
   * Uses input from the encoders on the motors to get
   * the distance the robot has driven
   * 
   * @return average encoder counts in raw encoder counts
   */
  public double getEncoderAverageValue()
  {
    return (mRightMaster.getSelectedSensorPosition() + mLeftMaster.getSelectedSensorPosition()) / 2;
  }

  /**
   * Will stop all motors
   */
  public void StopMotors() {
    mRightMaster.stopMotor();
    mLeftMaster.stopMotor();
  }

  /**
   * Turn left method for continous turning
   */
  public void TurnLeft() {
    mLeft.set(Constants.kTurnSpeed);
    mRight.set(Constants.kTurnSpeed);
  }

  /**
   * The turn right method for continuosly turning right
   */
  public void TurnRight() {
    mLeft.set(-Constants.kTurnSpeed);
    mRight.set(-Constants.kTurnSpeed);
  }

  /**
   * Drives forward at the constant kTargetFollowSpeed (0.2)
   */
  public void MoveForward() {
    mLeft.set(Constants.kTargetFollowSpeed);
    mRight.set(Constants.kTargetFollowSpeed);
  }

  /**
   * Will change the setpoint varible from whatever it is
   * when this is called to the whatever is the parameter
   * 
   * @param setpoint
   */
  public void setSetpoint(int setpoint) {
    this.mSetpoint = setpoint;
  }

  /**
   * Will aim to the target from a x coordinate off
   * an image from the limelight camera 
   */
  public void AimToTargetPID(double tx) {
    /* PID CONSTANTS */
    double mIntegral = 0;
    double mDerivative = 0;
    double mOutput = 0;
    double mActual = tx;

    /* MATH */
    error = mSetpoint - mActual;// Error = Target - Actual
    mIntegral += (error * .02);// Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    mDerivative = (error - previousError) / .02;
    mOutput = kP * error + kI * mIntegral + kD * mDerivative;
    previousError = error;// Sets pr

    // Will tell the robot to turn at a rate around the z-axis
    mDrive.arcadeDrive(0, mOutput);
  }

  /**
   * Reset all sensors - the gyro and two WPI_TalonSRX encoders
   */
  public void sensorReset() {
    mGyro.reset();
    // mRightEncoder.reset();
    // mLeftEncoder.reset();
    mLeftMaster.setSelectedSensorPosition(0);
    mRightMaster.setSelectedSensorPosition(0);
  }

  /**
   * The PID Steering method with an input from
   * the camera
   */
  public void PIDSteering(double tx) {
    double kF = -0.1;
    double speed = 0;
    // if(tx<1){
    // speed += kF*tx;
    // }else if(tx>1){
    // speed += kF*tx;
    // }
    speed = kF * tx;
    mDrive.arcadeDrive(0, speed);
  }

  /**
   * PID Distance algorthm still doesn't work that well. Came from AcclProgram Project
   * 
   * @param targ_distance
   */
  public void PID_Distance(double targ_distance)
  {
    /* Varible Declarations */
    double output; // PID Ouput

    double error = targ_distance -((mLeftMaster.getSelectedSensorPosition() + mRightMaster.getSelectedSensorPosition())/2);//Init Error
    //double error = targ_distance - (mLeftEncoder.getDistance() + mRightEncoder.getDistance())/2; // Init Error
    double pre_error = error; // Pre_Error (For Derivative)
    
    // Math Varibles
    double kP = 0.3; // Proportional
    double kI = 0.0; // Integrel
    double kD = 0.0; // Derivative
    
    double Integrel = 0.0; // Integral Adding Varible

    // Reset Encoders
    mLeftMaster.setSelectedSensorPosition(0);
    mRightMaster.setSelectedSensorPosition(0);
    // mLeftEncoder.reset();
    // mRightEncoder.reset();

    // While loop conditional
    // while(!(((mLeftEncoder.getDistance() + mRightEncoder.getDistance())/2) > (targ_distance + 1) && ((mLeftEncoder.getDistance() + mRightEncoder.getDistance())/2 )< (targ_distance - 1)))
    while(!(((mLeftMaster.getSelectedSensorPosition() + mRightMaster.getSelectedSensorPosition())/2) > (targ_distance + 1) && ((mLeftMaster.getSelectedSensorPosition() + mRightMaster.getSelectedSensorPosition())/2 )< (targ_distance - 1)))
    {
      // Maths
      error = targ_distance - ((mLeftMaster.getSelectedSensorPosition() + mRightMaster.getSelectedSensorPosition())/2);
      //error = targ_distance - ((mLeftEncoder.getDistance() + mRightEncoder.getDistance())/2);
      Integrel += error*0.02;
      output = (kP * error) + (kI * Integrel) + (kD * ((error - pre_error)/0.02));
      pre_error = error;

      // Checks if bigger than maxSpeed
      if (output >= 1.0) { output = 0.99; }

      // Tells Robot to drive
      if (output > 0){
        output= Math.max(output, 0.3);
        mDrive.tankDrive(output, output);
      }else if (output < 0 ) {
        output= Math.min(output, -0.3);
        mDrive.tankDrive(output, output);
      }
    }
  }

  /**
   * Will return the drivetrain gyro
   * 
   * @return AHRS Gyro for the drivetrain
   */
  public AHRS getGyro()
  {
    return mGyro;
  }

  /**
   * Will return the Drive Varible from RobotMap.java
   * 
   * @return mDrive
   */
  public DifferentialDrive getDrive() {
    return mDrive;
  }

  /**
   * Will return the DriveTrain's Front Left Motor
   * 
   * @return mDT_LeftFront
   */
  public WPI_TalonSRX getLeftFront() {
    return mLeftMaster;
  }

  /**
   * Will return the DriveTrain's Front Rear Motor
   * 
   * @return mDT_LeftRear
   */
  public VictorSPX getLeftSlave() {
    return mLeftSlave;
  }

  /**
   * Will return the DriveTrain's Right Front Motor
   * 
   * @return mDT_RightFront
   */
  public WPI_TalonSRX getRightFront() {
    return mRightMaster;
  }

  /**
   * Will return the DriveTrain's Right Rear Motor
   * 
   * @return mDT_RightRear
   */
  public VictorSPX getRightSlave() {
    return mRightSlave;
  }

  @Override
  public void initDefaultCommand() {
  }
}