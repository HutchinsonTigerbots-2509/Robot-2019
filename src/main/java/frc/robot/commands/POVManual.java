/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Elevator;

public class POVManual extends Command {
  private Elevator sElevator = Robot.sElevator;
  private Intake sIntake = Robot.sIntake;
  private Joystick stick;
  public POVManual() {
    requires(sElevator);
    requires(sIntake);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    stick = Robot.oi.getCoOperatorStick();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("POV",stick.getPOV());
    if(stick.getPOV()!=-1){
      sIntake.WristUp();
    }else if(stick.getPOVCount()!=-1){
      sIntake.WristDown();
    }else{
      sIntake.StopWrist();
    }
    // if(stick.getPOV() == 0 && stick.getRawButtonPressed(10)){
    //   sElevator.ElevatorUp();
    // } else if(stick.getPOV() == 180 && stick.getRawButtonPressed(10)){
    //   sElevator.ElevatorDown();
    // } else if (stick.getPOV() == 0 && !stick.getRawButtonPressed(10)){
    //   sIntake.WristUp();
    // } else if (stick.getPOV() == 180 && !stick.getRawButtonPressed(10)){
    //   sIntake.WristDown();
    // }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    sElevator.StopMotors();
    sIntake.StopWrist();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
