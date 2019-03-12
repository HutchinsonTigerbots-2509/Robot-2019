/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorMove extends Command {
  private final Elevator sElevator = Robot.sElevator;
  private double mTargetHeight = 0;
  private double mTargetHeightTicks = 0;
  private double ElevatorTicks = 0;
  private double ElevatorTicksIsFinished = 0;
  private boolean IsHighGear;
  public ElevatorMove(double TargetHeightInches) {
    requires(sElevator);
    this.mTargetHeight = TargetHeightInches;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    IsHighGear = sElevator.isHighGear();
    if(IsHighGear == true){
      sElevator.setPositionHighGear(mTargetHeight);
      mTargetHeightTicks = mTargetHeight*Constants.kElevatorHighGearTicksPerInch;
    } else if (IsHighGear == false){
      sElevator.setPositionLowGear(mTargetHeight);
      mTargetHeightTicks = mTargetHeight*Constants.kElevatorLowGearTicksPerInch;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(IsHighGear == true){
      sElevator.setPositionHighGear(mTargetHeight);
      ElevatorTicks = sElevator.CurrentTicks();
      ElevatorTicksIsFinished = sElevator.getTargetTicks();
    } else if (IsHighGear == false){
      sElevator.setPositionLowGear(mTargetHeight);
      ElevatorTicks = sElevator.CurrentTicks();
      ElevatorTicksIsFinished = sElevator.getTargetTicks();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(ElevatorTicks <= (ElevatorTicksIsFinished + 10) && ElevatorTicks >= (ElevatorTicksIsFinished - 10)){
      return true;
    } else {
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
