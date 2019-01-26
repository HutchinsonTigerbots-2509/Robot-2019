/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * The DriveTrain Subsystem is where the drivetrain is bound to the code
 * through the motors created in RobotMap, which are stored in a Differential
 * Drive Varible
 * 
 *                               Front
 *<h4>   DriveTrainLeft   |----------------| DriveTrain Right </h4>
 *<h4>    DTLeft1         |----------------| DTRight1 </h4>
 *<h4>                    |----------------| </h4>
 *<h4>                    |----------------| </h4>
 *<h4>                    |----------------| </h4>
 *<h4>    DTLeft2         |----------------| DTRight2 </h4>
 *                               Back
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
  private double kMaxSpeed = Constants.kMaxSpeed;
  private double kSlowSpeed = Constants.kSlowSpeed;

  public boolean TargetAligned;
  public boolean TargetDistanceCheck;
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
  public void driveForwar() {
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
    mDrive.arcadeDrive(-kMaxSpeed, -kMaxSpeed);
  }

  /**
   * Will drive in reverse at 65%. 
   * More recomeended than driveReverse()
   */
  public void driveReverseSlow() {
    mDrive.tankDrive(-kSlowSpeed, -kSlowSpeed);
  }

  public void track_taget(double left, double right, double pipeline){
    //mDrive.tankDrive(-left, -right);
    if(pipeline == 0){
      mDrive.arcadeDrive(left, -right);
    } else if(pipeline == 2){
        mDrive.arcadeDrive(-left, -right);
    }
    //mDrive.arcadeDrive(-0.5, -right);

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

  public void StopMotors() {
    mRightMaster.stopMotor();
    mRightSlave.stopMotor();
    mLeftMaster.stopMotor();
    mLeftSlave.stopMotor();
  }

  public void TurnLeft() {
    mLeft.set(Constants.kTurnSpeed);
    mRight.set(Constants.kTurnSpeed);
    //mDrive.tankDrive(-Constants.kTurnSpeed, Constants.kTurnSpeed);
  }

  public void TurnRight() {
    //mDrive.tankDrive(Constants.kTurnSpeed, -Constants.kTurnSpeed);
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