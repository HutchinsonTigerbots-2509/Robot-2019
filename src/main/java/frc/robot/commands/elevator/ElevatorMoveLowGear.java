package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;

/**
 * Elevator Elevates
 */
public class ElevatorMoveLowGear extends Command {
  private final Elevator sElevator = Robot.sElevator;
  private double mTargetHieght = 0;
  private double mTargetHeightTicks = 0;
  private double Elevator_ticks = 0;
  private double Elevator_ticks_is_finsihed = 0;
  public ElevatorMoveLowGear(double targetHieghtInches) {
    requires(sElevator);
    this.mTargetHieght = targetHieghtInches;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    sElevator.setHighGear(false);
    sElevator.setPositionLowGear(mTargetHieght);
    mTargetHeightTicks = mTargetHieght*Constants.kElevatorLowGearTicksPerInch;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    sElevator.setPositionLowGear(mTargetHieght);
    // SmartDashboard.putNumber("Current Height Ticks",sElevator.CurrentTicks());
    // SmartDashboard.putNumber("Target Height In Ticks", sElevator.getTargetTicks());
    // SmartDashboard.putNumber("Target Height Inches", mTargetHieght);
    Elevator_ticks = sElevator.CurrentTicks();
    Elevator_ticks_is_finsihed = sElevator.getTargetTicks();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // if(sElevator.getLimitsValue()){
    //   return true;
    // }
    //SmartDashboard.putNumber('Elevator_ticks', Elevator_ticks);
    //Elevator_ticks = sElevator.CurrentTicks();
    // SmartDashboard.putNumber("Elevator_ticks_isfinsished", Elevator_ticks);
    // SmartDashboard.putNumber("Elevator_ticks_Targetisfinished", mTargetHeightTicks);
    if(Elevator_ticks <= (Elevator_ticks_is_finsihed + 10) && Elevator_ticks >= (Elevator_ticks_is_finsihed - 10)){
      // SmartDashboard.putString("Is returning true", "yes");
    
      return true;
    }else{
      // SmartDashboard.putString("Is returning true", "no");
     return false;
    }
    // if(sElevator.CurrentTicks() == mTargetHieght){
    //   SmartDashboard.putString("Is returning true", "yes");
    //   return true;
    // } else {
    //   SmartDashboard.putString("Is returning true", "no");
    //   return false;
    // }
    // return sElevator.getLimitsValue();
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
    sElevator.StopMotors();
  }
}
