package frc.robot.commands; // package declaration

// imports

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

/**
 * Will eject the hatch by telling the pistons
 * for that to extend
 * 
 * Good as of 2/29/2019
 */
public class HatchEject extends Command {
  private Intake sIntake = Robot.sIntake;
  public HatchEject() {
    requires(sIntake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    sIntake.HatchEject();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    sIntake.HatchEject();
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
  }
}