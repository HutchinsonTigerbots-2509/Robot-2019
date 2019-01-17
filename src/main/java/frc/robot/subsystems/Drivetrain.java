package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
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

  // Varible Declarations
  private final DifferentialDrive mDrive = RobotMap.DrivetrainDifferential;
  private final SpeedControllerGroup mLeft = RobotMap.DrivetrainLeft;
  private final SpeedControllerGroup mRight = RobotMap.DrivetrainRight;
  private final WPI_TalonSRX mLeftMaster = RobotMap.DrivetrainLeftMaster;
  private final WPI_TalonSRX mLeftSlave = RobotMap.DrivetrainLeftSlave;
  private final WPI_TalonSRX mRightMaster = RobotMap.DrivetrainRightMaster;
  private final WPI_TalonSRX mRightSlave = RobotMap.DrivetrainRightSlave;
  private final Encoder mRightEncoder = RobotMap.DrivetrainRightEncoder;
  private final Encoder mLeftEncoder = RobotMap.DrivetrainLeftEncoder;
  private final AHRS mGyro = RobotMap.Gyro;
  private double kMaxSpeed = Constants.kMaxSpeed;
  private double kSlowSpeed = Constants.kSlowSpeed;
  

  public boolean TargetAligned;
  private final double kP = Constants.kDrivetrainP;
  private final double kI = Constants.kDrivetrainI;
  private final double kD = Constants.kDrivetrainD;
  private double mSetpoint = 0;
  private double error = 0;
  private double previousError = 0;

  public Drivetrain() {
    setName("Drivetrain");
    addChild(mLeftMaster);
    addChild(mLeftSlave);
    addChild(mRightMaster);
    addChild(mRightSlave);
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  @Override
  public void initDefaultCommand() {
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
   * Will drive in reverse at 95%.
   * @warning Highly not reccommened!
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
   * @author CRahne
   */
  public void OperatorDrive(Joystick stick) {
    mDrive.arcadeDrive(stick.getY(), stick.getZ());
  }

  public void driveForward(double targetDistance) {
    sensorReset();
    double wheelDiameter = 6;
    double target = (targetDistance/(wheelDiameter*Math.PI))*3*360;
    Timer.delay(0.1);
    while(getEncoderAverageValue() / 2 <= target) {
      mDrive.arcadeDrive(0.7, 0.7);
    }
    mDrive.tankDrive(0, 0);
  }

  public double getEncoderAverageValue()
  {
    return mRightEncoder.get() + mLeftEncoder.get() / 2;
  }

  public void StopMotors() {
    mRightMaster.stopMotor();
    mRightSlave.stopMotor();
    mLeftMaster.stopMotor();
    mLeftSlave.stopMotor();
  }

  public void TurnLeft() {
    mLeft.set(Constants.kTurnSpeed);
    mRight.set(Constants.kTurnSpeed);
  }

  public void TurnRight() {
    mLeft.set(-Constants.kTurnSpeed);
    mRight.set(-Constants.kTurnSpeed);
  }

  public void MoveForward() {
    mLeft.set(Constants.kTargetFollowSpeed);
    mRight.set(Constants.kTargetFollowSpeed);
  }

  public void setSetpoint(int setpoint) {
    this.mSetpoint = setpoint;
  }

  public void AimToTargetPID(double tx) {
    double mIntegral = 0;
    double mDerivative = 0;
    double mOutput = 0;
    double mActual = tx;

    error = mSetpoint - mActual;// Error = Target - Actual
    mIntegral += (error * .02);// Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    mDerivative = (error - previousError) / .02;
    mOutput = kP * error + kI * mIntegral + kD * mDerivative;
    previousError = error;// Sets pr

    mDrive.arcadeDrive(0, mOutput);

  }

  public void sensorReset() {
    mGyro.reset();
    mRightEncoder.reset();
    mLeftEncoder.reset();
  }

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

  public void rotate(double target)
  {
    mGyro.reset();
    double gyro_input = mGyro.getAngle();

    if(target > gyro_input)
    {
      while(target > gyro_input)
      {
        TurnRight();
      }
    }
    else if(target < gyro_input)
    {
      while(target < gyro_input)
      {
        TurnLeft();
      }
    }
    StopMotors();
  }

  /**
   * Will return the Drive Varible from RobotMap.java
   * 
   * @author CRahne
   * @return mDrive
   */
  public DifferentialDrive getDrive() {
    return mDrive;
  }

  /**
   * Will return the DriveTrain's Front Left Motor
   * 
   * @return mDT_LeftFront
   * @author CRahne
   */
  public WPI_TalonSRX getLeftFront() {
    return mLeftMaster;
  }

  /**
   * Will return the DriveTrain's Front Rear Motor
   * 
   * @return mDT_LeftRear
   * @author CRahne
   */
  public WPI_TalonSRX getLeftRear() {
    return mLeftSlave;
  }

  /**
   * Will return the DriveTrain's Right Front Motor
   * 
   * @return mDT_RightFront
   * @author CRahne
   */
  public WPI_TalonSRX getRightFront() {
    return mRightMaster;
  }

  /**
   * Will return the DriveTrain's Right Rear Motor
   * 
   * @return mDT_RightRear
   * @author CRahne
   */
  public WPI_TalonSRX getRightRear() {
    return mRightSlave;
  }
}