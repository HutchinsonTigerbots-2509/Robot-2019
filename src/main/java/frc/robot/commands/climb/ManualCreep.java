package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Climber;

public class ManualCreep extends Command {

private static Climber sClimb = Robot.sClimb;
private Joystick stick;
  public ManualCreep() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
    stick = Robot.oi.getOperatorStick();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    if(stick.getRawAxis(5) != 0){
      sClimb.setMotorSpeed(stick.getRawAxis(5));
    }else{
      sClimb.setMotorSpeed(0);
    }
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    
    sClimb.setMotorSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
