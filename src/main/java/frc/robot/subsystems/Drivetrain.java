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
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  public boolean TargetAligned;
  private final WPI_TalonSRX RightMaster = RobotMap.DrivetrainRightMaster;
  private final WPI_TalonSRX RightSlave = RobotMap.DrivetrainRightSlave;
  private final WPI_TalonSRX LeftMaster = RobotMap.DrivetrainLeftMaster;
  private final WPI_TalonSRX LeftSlave = RobotMap.DrivetrainLeftSlave;
  private final SpeedControllerGroup LeftDrive = RobotMap.LeftDriveTrain;
  private final SpeedControllerGroup RightDrive = RobotMap.RightDrivetrain;
  private final DifferentialDrive Drive = RobotMap.RobotDrive;
  private final double kP = Constants.kDrivetrainP;
  private final double kI = Constants.kDrivetrainI;
  private final double kD = Constants.kDrivetrainD;
  private double mSetpoint = 0;
  private double error = 0;
  private double previousError = 0;

  public Drivetrain() {
    setName("Drivetrain");
    addChild(RightMaster);
    addChild(RightSlave);
    addChild(LeftMaster);
    addChild(LeftSlave);
  }

  public void OperatorControl(Joystick stick) {
    Drive.arcadeDrive(stick.getY() * 0.5, stick.getZ() * -0.5);
  }

  public void StopMotors() {
    RightMaster.stopMotor();
    RightSlave.stopMotor();
    LeftMaster.stopMotor();
    LeftSlave.stopMotor();
  }

  public void TurnLeft() {
    LeftDrive.set(Constants.kTurnSpeed);
    RightDrive.set(Constants.kTurnSpeed);
  }

  public void TurnRight() {
    LeftDrive.set(-Constants.kTurnSpeed);
    RightDrive.set(-Constants.kTurnSpeed);
  }

  public void MoveForward() {
    LeftDrive.set(Constants.kTargetFollowSpeed);
    RightDrive.set(Constants.kTargetFollowSpeed);
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

    Drive.arcadeDrive(0, mOutput);

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
    Drive.arcadeDrive(0, speed);
  }

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
