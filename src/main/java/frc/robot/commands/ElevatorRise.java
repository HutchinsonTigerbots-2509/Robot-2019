/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;

public class ElevatorRise extends Command {
  private final Elevator sElevator = Robot.sElevator;
  private double mTargetHieght = 0;
  public ElevatorRise(double targetHieghtInches) {
    requires(sElevator);
    this.mTargetHieght = targetHieghtInches;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    sElevator.setPosition(mTargetHieght);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    sElevator.setPosition(mTargetHieght);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(sElevator.getLimitsValue()){
      return true;
    }
    if(sElevator.CurrentHeight()==mTargetHieght){
      return true;
    }
    return false;
    // return sElevator.getLimitsValue();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    sElevator.StopMotors();
    
    if(sElevator.getLimitsValue()){
      sElevator.ZeroSensor();
    }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    sElevator.StopMotors();
  }
}
