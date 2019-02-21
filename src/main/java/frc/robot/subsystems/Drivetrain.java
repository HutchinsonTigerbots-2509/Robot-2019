package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * The DriveTrain Subsystem is where the drivetrain is bound to the code through
 * the motors created in RobotMap, which are stored in a Differential Drive
 * Varible.
 * 
 * <h3>JavaDoc Categories for Functions:</h3>
 * <li>+ Basic Drive Methods - Basic Driving Methods like Operator Drive
 * <li>+ Update Voids - Updates something, like data or sensors
 * <li>+ Shifter - Voids controlling the shifter piston
 * <li>+ PID Controller - Control Voids using a PID Equation
 * <li>+ DriveTrain Getters - Will return something, like an object
 * 
 * @author CRahne, Wayne, Tony, Cole, and Nate
 */
public class Drivetrain extends Subsystem {
  // #region Import Varibles

  // Motors
  private final WPI_TalonSRX mLeftMaster = RobotMap.DrivetrainLeftMaster;
  private final WPI_VictorSPX mLeftSlave = RobotMap.DrivetrainLeftSlave;
  private final WPI_TalonSRX mRightMaster = RobotMap.DrivetrainRightMaster;
  private final WPI_VictorSPX mRightSlave = RobotMap.DrivetrainRightSlave;
  
  // The Differential Drive Object object (for mDrive.tankDrive)
  private final DifferentialDrive mDrive = RobotMap.DrivetrainDifferential;
  private final double kSpeedMulti = Constants.kDriveSpeedMulti;
  
  // DriveTrian Shifter for High and Low gear
  private final DoubleSolenoid mShifter = RobotMap.DrivetrainShifter;
  
  // Gyro
  private final AHRS mGyro = RobotMap.DrivetrainGyro;
  
  // Shuffleboard
  private final ShuffleboardTab mDriveTrainTab = Shuffleboard.getTab("Drivetrain");
  
  // Vision
  public boolean TargetAligned;
  public boolean TargetDistanceCheck;
  // Constants
  private double kMaxSpeed = Constants.kMaxSpeed;
  private double kSlowSpeed = Constants.kSlowSpeed;

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
    private double mSetpoint = 0;
    private double mError = 0;
    private double mPreviousError = 0;
  // #endregion
  
  /**
   * Adds children to the object so we can play with components in test mode
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

  public void ResetGyro() {
    mGyro.zeroYaw();
  }

  public void track_taget(double left, double right, double pipeline) {
    // mDrive.tankDrive(-left, -right);
    if (pipeline == 0 || pipeline == 2 || pipeline == 1 || pipeline == 4) {
      mDrive.arcadeDrive(left, -right);
    } // else if(pipeline == 9){
      // mDrive.arcadeDrive(-left, -right);

    // mDrive.arcadeDrive(-0.5, -right);
  }

  /**
   * OperatorDrive is the Method for driving. It uses the differential drive
   * varible that was created in RobotMap.java. It will grab the Y-Axis and Z-Axis
   * of the OPStick in OI.java, then drive the robot.
   * 
   * @param Joystick stick
   */
  public void OperatorDrive(Joystick stick) {    // If the absolute value of the joystick is not greater than 10 %,
    double Speed = stick.getRawAxis(1)*-kSpeedMulti;
    // then don't do anything.
     if (Math.abs(stick.getRawAxis(3)) > Constants.minMoveSpeed || Math.abs(stick.getRawAxis(2)) > Constants.minMoveSpeed) {
      // mDrive.arcadeDrive(stick.getY(), -stick.getZ());
       // mDrive.arcadeDrive(-stick.getY(), 0);

       if(stick.getRawAxis(3) > 0){
        Speed = stick.getRawAxis(3) * -kSpeedMulti;
        }else if(stick.getRawAxis(2) > 0){
          Speed = stick.getRawAxis(2) * kSpeedMulti;
        }else{
          Speed = 0;
        }
   
        if(kSpeedMulti > 0){
          mDrive.arcadeDrive(Speed, stick.getRawAxis(0) * kSpeedMulti);
        }else{
         mDrive.arcadeDrive(Speed, stick.getRawAxis(0) * -kSpeedMulti);
        }

     } else {
       // So the robot will not stay at a +- 0.11 input
       mDrive.arcadeDrive(0, 0);
     }
  }

   /**
   * OperatorDrive is the Method for driving. It uses the differential drive
   * varible that was created in RobotMap.java. It will grab the Y-Axis and Z-Axis
   * of the OPStick in OI.java, then drive the robot.
   * 
   * @param Joystick stick
   */
  public void OperatorDriveAlt(Joystick stick) {
    // If the absolute value of the joystick is not greater than 10 %,
    // then don't do anything.
    if (Math.abs(stick.getY()) > Constants.minMoveSpeed || Math.abs(stick.getZ()) > Constants.minMoveSpeed) {
      mDrive.arcadeDrive(-stick.getY(), -stick.getZ());
      // mDrive.arcadeDrive(-stick.getY(), 0);
    } else {
      // So the robot will not stay at a +- 0.11 input
      mDrive.arcadeDrive(0, 0);
    }
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
    double target = (targetDistance / (wheelDiameter * Math.PI)) * 3 * 360;

    Timer.delay(0.1);
    // continue if the average encoder counts is less than or equal to the modified
    // target distance
    while (getEncoderAverageValue() / 2 <= target) {
      mDrive.arcadeDrive(0.7, 0.7); // Drive at 70 percent power
    }
    mDrive.tankDrive(0, 0); // Stop at the end
  }

  /**
   * Will stop all motors
   */
  public void StopMotors() {
    mRightMaster.stopMotor();
    mLeftMaster.stopMotor();
  }

  /**
   * Will update all values that are sent to the Shuffleboard
   * 
   * <li>- Left Encoder, Right Encoder, and Encoder Average Distance
   * <li>- Gyro Angle
   * <li>- Differential Drive Object
   * <li>- Shifter Object
   * <li>- isShifted Boolean
   */
  public void UpdateTelemetry() {
    // Subsystem Status
    mDriveTrainTab.add("Left Encoder", mLeftMaster.getSelectedSensorPosition());
    mDriveTrainTab.add("Right Encoder", mRightMaster.getSelectedSensorPosition());
    mDriveTrainTab.add("Encoder Avg", getEncoderAverageValue());
    mDriveTrainTab.add("Gyro Angle", mGyro.getAngle());
    mDriveTrainTab.add("Shifter", getShifter());
    mDriveTrainTab.add("isShifted", isShifted());
    // Subsystem Objects
    mDriveTrainTab.add(mLeftMaster);
    mDriveTrainTab.add(mLeftSlave);
    mDriveTrainTab.add(mRightMaster);
    mDriveTrainTab.add(mRightSlave);
    mDriveTrainTab.add(mDrive);
    mDriveTrainTab.add(mShifter);
    // Subsytem Commands


    Shuffleboard.update();
  }

  /**
   * Will change the setpoint varible from whatever it is when this is called to
   * the whatever is the parameter
   * 
   * @param setpoint
   */
  public void setSetpoint(int setpoint) {
    this.mSetpoint = setpoint;
  }

  /**
   * Reset all sensors - the gyro and two WPI_TalonSRX encoders
   */
  public void sensorReset() {
    mGyro.reset();
    mLeftMaster.setSelectedSensorPosition(0);
    mRightMaster.setSelectedSensorPosition(0);
  }

  /**
   * Shifts the Gear to Low
   * 
   * @author Cole
   * @author Tony
   */
  public void ChangeShift(boolean mHighGear) {
    if (mHighGear) {
      mShifter.set(Value.kForward);
    } else {
      mShifter.set(Value.kReverse);
    }
  }

  /**
   * Returns a boolean and if True means that it is shifted
   * 
   * @author Cole
   * @author Tony
   */
  public boolean isShifted() {
    if (mShifter.get() == Value.kReverse) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns the Shifter Object
   * 
   * @return Shifter Piston
   */
  public DoubleSolenoid getShifter() {
    return mShifter;
  }

  /**
   * The PID Steering method with an input from the camera
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
   * PID Distance algorthm still doesn't work that well. Came from AcclProgram
   * Project. Uses a While Loop Though
   * 
   * @param targ_distance
   */
  public void PID_Distance(double targ_distance) {
    /* VARIBLE DECLARATIONS */
    double error = targ_distance
        - ((mLeftMaster.getSelectedSensorPosition() + mRightMaster.getSelectedSensorPosition()) / 2);// Init Error
    double pre_error = error; // Pre_Error (For Derivative)
    double Integrel = 0.0; // Integral Adding Varible
    double output; // PID Ouput
    // Reset Encoders
    mLeftMaster.setSelectedSensorPosition(0);
    mRightMaster.setSelectedSensorPosition(0);
    // While loop conditional
    while (!(((mLeftMaster.getSelectedSensorPosition() + mRightMaster.getSelectedSensorPosition()) / 2) > (targ_distance
        + 1)
        && ((mLeftMaster.getSelectedSensorPosition() + mRightMaster.getSelectedSensorPosition()) / 2) < (targ_distance
            - 1))) {
      // Maths
      error = targ_distance
          - ((mLeftMaster.getSelectedSensorPosition() + mRightMaster.getSelectedSensorPosition()) / 2);
      Integrel += error * 0.02;
      output = (kDistanceP * error) + (kDIstanceI * Integrel) + (kDistanceD * ((error - pre_error) / 0.02));
      pre_error = error;
      // Checks if bigger than maxSpeed
      if (output >= 1.0) {
        output = 0.99;
      }
      // Tells Robot to drive
      if (output > 0) {
        output = Math.max(output, 0.3);
        mDrive.tankDrive(output, output);
      } else if (output < 0) {
        output = Math.min(output, -0.3);
        mDrive.tankDrive(output, output);
      }
    }
  }

  /**
   * Will aim to the target from a x coordinate off an image from the limelight
   * camera
   */
  public void AimToTargetPID(double tx) {
    /* PID CONSTANTS */
    double mIntegral = 0;
    double mDerivative = 0;
    double mOutput = 0;
    double mActual = tx;
    /* MATH */
    mError = mSetpoint - mActual;// Error = Target - Actual
    mIntegral += (mError * .02);// Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    mDerivative = (mError - mPreviousError) / .02;
    mOutput = (kAimToTargetP * mError) + (kAimToTargetI * mIntegral) + (kAimToTargetD * mDerivative);
    mPreviousError = mError;// Sets pr
    // Will tell the robot to turn at a rate around the z-axis
    mDrive.arcadeDrive(0, mOutput);
  }

  /**
   * PID Turn Value finder. This should only be used in the execute() part of a
   * command
   * 
   * @see frc.robot.commands.PID_Turn.java
   * @return PID output for the Turn
   */
  public double getPIDTurnValue(double error, double pre_error, double targ_angle) {
    return -((kTurnP * error) + (kTurnD * (error - pre_error) / 0.2));
  }

  /**
   * Uses input from the encoders on the motors to get the distance the robot has
   * driven
   * 
   * @return average encoder counts in raw encoder counts
   */
  public double getEncoderAverageValue() {
    return (mRightMaster.getSelectedSensorPosition() + mLeftMaster.getSelectedSensorPosition()) / 2;
  }

  /**
   * Will return the drivetrain gyro
   * 
   * @return AHRS Gyro for the drivetrain
   */
  public AHRS getGyro() {
    return mGyro;
  }

  /**
   * Will return the Drive Varible
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