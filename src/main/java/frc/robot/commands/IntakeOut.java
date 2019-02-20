package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class IntakeOut extends Command {
  public Intake sIntake = Robot.sIntake;
  
  public IntakeOut() {
    requires(sIntake);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    sIntake.Out();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    sIntake.Out();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    sIntake.MotorStop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    sIntake.MotorStop();
  }
}
