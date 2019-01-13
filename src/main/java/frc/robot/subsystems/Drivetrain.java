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
  private final WPI_TalonSRX RightOne = RobotMap.DrivetrainRightMaster;
  private final WPI_TalonSRX RightTwo = RobotMap.DrivetrainRightSlave;
  private final WPI_TalonSRX LeftOne = RobotMap.DrivetrainLeftMaster;
  private final WPI_TalonSRX LeftTwo = RobotMap.DrivetrainLeftSlave;
  private final SpeedControllerGroup LeftDrive = RobotMap.LeftDriveTrain;
  private final SpeedControllerGroup RightDrive = RobotMap.RightDrivetrain;
  private final DifferentialDrive Drive = RobotMap.RobotDrive;

  public void OperatorControl(Joystick stick){
    Drive.arcadeDrive(stick.getY()*0.5, stick.getZ()*-0.5);
  }
  public void StopMotors(){
    RightOne.stopMotor();
    RightTwo.stopMotor();
    LeftOne.stopMotor();
    LeftTwo.stopMotor();
  }
  public void TurnLeft(){
    LeftDrive.set(Constants.kTurnSpeed);
    RightDrive.set(Constants.kTurnSpeed);
  }
  public void TurnRight(){
    LeftDrive.set(-Constants.kTurnSpeed);
    RightDrive.set(-Constants.kTurnSpeed);
  }
  public void MoveForward(){
    LeftDrive.set(Constants.kTargetFollowSpeed);
    RightDrive.set(Constants.kTargetFollowSpeed);
  }


  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
