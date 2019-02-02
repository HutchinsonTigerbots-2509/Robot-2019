package frc.robot.subsystems; // package declaration

// imports

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * The DriveTrain Subsystem is where the drivetrain is bound to the code
 * through the motors created in RobotMap, which are stored in a Differential
 * Drive Varible.
 * 
 * <h3> JavaDoc Categories for Functions: </h3>
 * <li> + Basic Drive Methods - Basic Driving Methods like Operator Drive
 * <li> + Update Voids - Updates something, like data or sensors
 * <li> + Shifter - Voids controlling the shifter piston
 * <li> + PID Controller - Control Voids using a PID Equation
 * <li> + DriveTrain Getters - Will return something, like an object
 * 
 * @author CRahne, Wayne, Tony, Cole, and Nate
 */
public class Drivetrain extends Subsystem {

  // #region IMPORTED VARIBLE FROM ROBOTMAP DELCARATIONS
  // Motors
  private final WPI_TalonSRX mLeftMaster = RobotMap.DrivetrainLeftMaster;
  private final VictorSPX mLeftSlave = RobotMap.DrivetrainLeftSlave;
  private final WPI_TalonSRX mRightMaster = RobotMap.DrivetrainRightMaster;
  private final VictorSPX mRightSlave = RobotMap.DrivetrainRightSlave;
  
  // The Differential Drive Object object (for mDrive.tankDrive)
  private final DifferentialDrive mDrive = RobotMap.DrivetrainDifferential;
  
  // DriveTrian Shifter for High and Low gear
  private final DoubleSolenoid mShifter = RobotMap.DrivetrainShifter;
  
  // Gyro
  private final AHRS mGyro = RobotMap.Drivetrain_Gyro;
  private double kMaxSpeed = Constants.kMaxSpeed;
  private double kSlowSpeed = Constants.kSlowSpeed;
  //#endregion IMPORTED VARIBLE FROM ROBOTMAP DELCARATIONS

  // #region IMPORTED VARIBLES FROM CONSTANTS DECLARATION
  // Vision
  public boolean TargetAligned;
  public boolean TargetDistanceCheck;
  
  // PID Constants For Gyro Turn
  private final double kTurnP = Constants.kDriveTrainGyroTurnP;
  private final double kTurnD = Constants.kDriveTrainGyroTurnD;

  // PID Constants For Aiming to target
  private final double kAimToTargetP = Constants.kDrivetrainAimToTargetP;
  private final double kAimToTargetI = Constants.kDrivetrainAimToTargetI;
  private final double kAimToTargetD = Constants.kDrivetrainAimToTargetD;
  
  // PID Constants For Driving to a Distance
  private final double kDistanceP = Constants.kDriveTrainDistanceP;
  private final double kDIstanceI = Constants.kDriveTrainDistanceI;
  private final double kDistanceD = Constants.kDriveTrainDistanceD;
  // #endregion IMPORTED VARIBLES FROM CONSTANTS DECLARATION

  // #region PRIVATE VARIBLES
  private double mSetpoint = 0; // math (example in AlignWithTargetPID.java)
  private double error = 0;
  private double previousError = 0;
  // #endregion PRIVATE VARIBLES

  /**
   * Add children to the drivetrain class with this constructor
   */
  public Drivetrain() {
    setName("Drivetrain");
    addChild(mLeftMaster);
    addChild(mLeftSlave);
    addChild(mRightMaster);
    addChild(mRightSlave);
  }
  
  // #region BASIC DRIVE METHODS

  /**
   * Will drive forward at 95%.
   * 
   * @category Basic Drive Methods 
   */
  public void driveForward() {
    mDrive.tankDrive(kMaxSpeed, kMaxSpeed);
  }

  /**
   * Will drive forward at 65%.
   * 
   * @category Basic Drive Methods
   */
  public void driveForwardSlow() {
    mDrive.tankDrive(kSlowSpeed, kSlowSpeed);
  }

  /**
   * Will drive in reverse at 95%. (probably not a good idea though)
   * 
   * @category Basic Drive Methods
   */
  public void driveReverse() {
    mDrive.tankDrive(-kMaxSpeed, -kMaxSpeed);
  }

  /**
   * Will drive in reverse at 65%. 
   * More recomeended than driveReverse()
   * 
   * @category Basic Drive Methods
   */
  public void driveReverseSlow() {
    mDrive.tankDrive(-kSlowSpeed, -kSlowSpeed);
  }

  public void track_taget(double left, double right){
    mDrive.tankDrive(left, -right);

  }

  /**
   * OperatorDrive is the Method for driving. It uses the differential 
   * drive varible that was created in RobotMap.java. It will grab the 
   * Y-Axis and Z-Axis of the OPStick in OI.java, then drive the robot.
   * 
   * @param Joystick stick
   * @category Basic Drive Methods
   */
  public void OperatorDrive(Joystick stick) {
    mDrive.arcadeDrive(-stick.getY(), -stick.getZ());
    // mDrive.arcadeDrive(-stick.getY(), 0);
  }

  /**
   * The driveForward method from the 2018 code
   * 
   * @param targetDistance
   * @category Basic Drive Methods
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
   * Will stop all motors
   * 
   * @category Basic Drive Methods
   */
  public void StopMotors() {
    mRightMaster.stopMotor();
    mLeftMaster.stopMotor();
  }

  /**
   * Turn left method for continous turning
   * 
   * @category Basic Drive Methods
   */
  public void TurnLeft() {
    mLeftMaster.set(Constants.kTurnSpeed);
    mRightMaster.set(Constants.kTurnSpeed);
  }

  /**
   * The turn right method for continuosly turning right
   * 
   * @category Basic Drive Methods
   */
  public void TurnRight() {
    mLeftMaster.set(-Constants.kTurnSpeed);
    mRightMaster.set(-Constants.kTurnSpeed);
  }

  /**
   * Drives forward at the constant kTargetFollowSpeed (0.2)
   * 
   * @category Basic Drive Methods
   */
  public void MoveForward() {
    mLeftMaster.set(Constants.kTargetFollowSpeed);
    mRightMaster.set(Constants.kTargetFollowSpeed);
  }

  // #endregion BASIC DRIVE METHODS
  // #region UPDATE VOIDS
  
  /**
   * Will update all values that are sent to the Shuffleboard
   * 
   * @category Update Voids
   */
  public void UpdateTelementary() {

  }

  /**
   * Will change the setpoint varible from whatever it is
   * when this is called to the whatever is the parameter
   * 
   * @param setpoint
   * @category Update Voids
   */
  public void setSetpoint(int setpoint) {
    this.mSetpoint = setpoint;
  }

    /**
   * Reset all sensors - the gyro and two WPI_TalonSRX encoders
   * 
   * @category Update Voids
   */
  public void sensorReset() {
    mGyro.reset();
    mLeftMaster.setSelectedSensorPosition(0);
    mRightMaster.setSelectedSensorPosition(0);
  }

  // #endregion UPDATE VOIDS
  // #region SHIFTER

  /**
   * Shifts the Gear to High
   * 
   * @category Shifter
   * @author Cole
   * @author Tony
   */
  public void ShiftHighGear() {
    mShifter.set(Value.kForward);
  }

  /**
   * Shifts the Gear to Low
   * 
   * @category Shifter
   * @author Cole
   * @author Tony
   */
  public void ShiftLowGear() {
    mShifter.set(Value.kReverse);
  }

  /**
   * Returns a boolean and if True means that it is shifted
   * 
   * @category Shifter
   * @author Cole
   * @author Tony
   */
  public boolean isShifted() {
    if (mShifter.get() == Value.kReverse){
      SmartDashboard.putString("isShifted", "fastGear");
    	return true;
    } else {
      SmartDashboard.putString("isShifted", "slowGear");
      return false;
    }
  }

  // #endregion SHIFTER
  // #region PID CONTROLLER

  /**
   * The PID Steering method with an input from
   * the camera
   * 
   * @category PID Controller 
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
   * PID Distance algorthm still doesn't work that well. Came from AcclProgram Project.
   * Uses a While Loop Though
   * 
   * @category PID Controller
   * @param targ_distance
   */
  public void PID_Distance(double targ_distance)
  {
    /* VARIBLE DECLARATIONS */    
    double error = targ_distance -((mLeftMaster.getSelectedSensorPosition() + mRightMaster.getSelectedSensorPosition())/2);//Init Error
    double pre_error = error; // Pre_Error (For Derivative)

    double Integrel = 0.0; // Integral Adding Varible
    double output; // PID Ouput

    // Reset Encoders
    mLeftMaster.setSelectedSensorPosition(0);
    mRightMaster.setSelectedSensorPosition(0);

    // While loop conditional
    while(!(((mLeftMaster.getSelectedSensorPosition() + mRightMaster.getSelectedSensorPosition())/2) > (targ_distance + 1) && ((mLeftMaster.getSelectedSensorPosition() + mRightMaster.getSelectedSensorPosition())/2 )< (targ_distance - 1)))
    {
      // Maths
      error = targ_distance - ((mLeftMaster.getSelectedSensorPosition() + mRightMaster.getSelectedSensorPosition())/2);
      Integrel += error*0.02;
      output = (kDistanceP * error) + (kDIstanceI * Integrel) + (kDistanceD * ((error - pre_error)/0.02));
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
   * Will aim to the target from a x coordinate off
   * an image from the limelight camera 
   * 
   * @category PID Controller 
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
    mOutput = (kAimToTargetP * error) + (kAimToTargetI * mIntegral) + (kAimToTargetD * mDerivative);
    previousError = error;// Sets pr

    // Will tell the robot to turn at a rate around the z-axis
    mDrive.arcadeDrive(0, mOutput);
  }

  /**
   * PID Turn Value finder. This should only be used in the execute()
   * part of a command
   * 
   * @category PID Controller
   * @see frc.robot.commands.PID_Turn.java
   * @return PID output for the Turn
   */
  public double getPIDTurnValue(double error, double pre_error, double targ_angle) {
    return -((kTurnP * error) + (kTurnD * (error - pre_error) / 0.2));
  }

  // #endregion PID CONTROLLER
  // #region DRIVETRAIN GETTER

  /**
   * Uses input from the encoders on the motors to get
   * the distance the robot has driven
   * 
   * @category DriveTrain getter
   * @return average encoder counts in raw encoder counts
   */
  public double getEncoderAverageValue() {
    return (mRightMaster.getSelectedSensorPosition() + mLeftMaster.getSelectedSensorPosition()) / 2;
  }

  /**
   * Will return the drivetrain gyro
   * 
   * @category DriveTrain Getter
   * @return AHRS Gyro for the drivetrain
   */
  public AHRS getGyro() {
    return mGyro;
  }

  /**
   * Will return the Drive Varible from RobotMap.java
   * 
   * @category DriveTrain Getter
   * @return mDrive
   */
  public DifferentialDrive getDrive() {
    return mDrive;
  }

  /**
   * Will return the DriveTrain's Front Left Motor
   * 
   * @category DriveTrain Getter
   * @return mDT_LeftFront
   */
  public WPI_TalonSRX getLeftFront() {
    return mLeftMaster;
  }

  /**
   * Will return the DriveTrain's Front Rear Motor
   * 
   * @category DriveTrain Getter
   * @return mDT_LeftRear
   */
  public VictorSPX getLeftSlave() {
    return mLeftSlave;
  }

  /**
   * Will return the DriveTrain's Right Front Motor
   * 
   * @category DriveTrain Getter
   * @return mDT_RightFront
   */
  public WPI_TalonSRX getRightFront() {
    return mRightMaster;
  }

  /**
   * Will return the DriveTrain's Right Rear Motor
   * 
   * @category DriveTrain Getter
   * @return mDT_RightRear
   */
  public VictorSPX getRightSlave() {
    return mRightSlave;
  }

  // #endregion DRIVETRAIN GETTER

  @Override
  public void initDefaultCommand() {
  }
}