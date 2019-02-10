package frc.robot.commands; // package declaration

// imports

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

/**
 * Will suck up the ball by using the motor-belt
 * system that is in place
 * 
 * Good as of 2/29/2019
 */
public class IntakeBall extends Command {
  private Intake sIntake = Robot.sIntake;
  public IntakeBall() {
    requires(sIntake);
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
    sIntake.EndAll();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    sIntake.EndAll();
  }
}
