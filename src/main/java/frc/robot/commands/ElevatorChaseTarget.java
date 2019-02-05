/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;

public class ElevatorChaseTarget extends Command {

  private final Elevator sElevator = Robot.sElevator;
  private int mTargetHeight;
  private double CurrentHeight;

  public ElevatorChaseTarget(int TargetHeight) {
    mTargetHeight = TargetHeight;
    requires(sElevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() { 
    CurrentHeight = sElevator.CurrentHeight();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    CurrentHeight = sElevator.CurrentHeight();
    sElevator.setTargetHeight(mTargetHeight);
    sElevator.ChaseTarget();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (sElevator.CurrentHeight() == mTargetHeight){
      return true;
    }
    else { return false; }
  }
  //(CHieght - THieght) <= kErrorRange && (CHieght - THieght) >= -1*(kErrorRange)

  // Called once after isFinished returns true
  @Override
  protected void end() {
    sElevator.StopMotors();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
