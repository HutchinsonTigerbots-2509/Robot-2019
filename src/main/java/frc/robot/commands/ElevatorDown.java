package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;

public class ElevatorDown extends Command {
  private Elevator sElevator = Robot.sElevator;
  
  public ElevatorDown() {
    requires(sElevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    sElevator.Down();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    sElevator.Down();
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
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
