package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;

/**
 * Elevator Elevates
 */
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
    SmartDashboard.putNumber("Current Height Ticks",sElevator.CurrentHeight());
    SmartDashboard.putNumber("Target Height", sElevator.getTargetHeight());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // if(sElevator.getLimitsValue()){
    //   return true;
    // }
    if(sElevator.CurrentHeight()==mTargetHieght){
      return true;
    }else{
     return false;
    }
    // return sElevator.getLimitsValue();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    sElevator.StopMotors();
    
    // if(sElevator.getLimitsValue()){
    //   sElevator.ZeroSensor();
    // }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    sElevator.StopMotors();
  }
}
