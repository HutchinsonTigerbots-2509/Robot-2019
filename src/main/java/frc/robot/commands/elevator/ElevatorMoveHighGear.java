package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;

/**
 * Elevator Elevates
 */
public class ElevatorMoveHighGear extends Command {
  private final Elevator sElevator = Robot.sElevator;
  private double mTargetHieght = 0;
  private double Elevator_ticks;
  private double Elevator_ticks_is_finsihed;
  public ElevatorMoveHighGear(double targetHieghtInches) {
    requires(sElevator);
    this.mTargetHieght = targetHieghtInches;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // sElevator.setHighGear(true);
    sElevator.setPositionHighGear(mTargetHieght);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    sElevator.setPositionHighGear(mTargetHieght);
    Elevator_ticks = sElevator.CurrentTicks();
    Elevator_ticks_is_finsihed = sElevator.getTargetTicks();

    // SmartDashboard.putNumber("Current Height Ticks",sElevator.CurrentTicks());
    // SmartDashboard.putNumber("Target Height", sElevator.getTargetTicks());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // if(sElevator.getLimitsValue()){
    //   return true;
    // }
    if(Elevator_ticks <= (Elevator_ticks_is_finsihed + 50) && Elevator_ticks >= (Elevator_ticks_is_finsihed - 50)){
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
