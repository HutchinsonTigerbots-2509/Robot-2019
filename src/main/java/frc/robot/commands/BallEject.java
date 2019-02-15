package frc.robot.commands; // package declaration

// imports

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

/**
 * Will eject the ball by reversing the voltage output to
 * the motors (they spin opposite)
 * 
 * Good as of 2/29/2019
 */
public class BallEject extends Command {
  private Intake sIntake = Robot.sIntake;

  public BallEject() {
    requires(sIntake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    sIntake.MotorReverse();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    sIntake.MotorReverse();
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
