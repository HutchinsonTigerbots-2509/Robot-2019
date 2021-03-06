/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.Robot;

public class IntakeIn extends Command {
  private Intake sIntake = Robot.sIntake;
  public IntakeIn() {
    //requires(sIntake);
  }

  // Called just before this Command runs the first time
  @Override
  
  protected void initialize() {
    sIntake.In();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    sIntake.In();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    sIntake.IntakeMotorStop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    sIntake.IntakeMotorStop();
  }
}
