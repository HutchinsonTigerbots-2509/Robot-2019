/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class WristDown extends Command {
  private Intake sIntake = Robot.sIntake;
  public WristDown() {
    requires(sIntake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    sIntake.WristDown();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    sIntake.WristDown();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    sIntake.StopWrist();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    sIntake.StopWrist();
  }
}
