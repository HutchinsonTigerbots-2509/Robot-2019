/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;

public class ElevatorChaseTarget extends Command {

  private Elevator sElevator = Robot.sElevator;

  private final Elevator mElevator = new Elevator();

  double THieght = mElevator.TargetHeight();
  double CHieght = mElevator.CurrentHeight();
  int ErrorRange = Constants.kEncoderErrorRange;

  public ElevatorChaseTarget() {
    requires(sElevator);
  }

  @Override
  protected void initialize() {
  }
  /*
  Runs all the function
  gets the current height,
  gets the target height,
  calculate the PID speed value,
  and sends the speed to the motors
  */
  @Override
  protected void execute() {
    mElevator.CurrentHeight();
    mElevator.TargetHeight();
    mElevator.PIDFinal();
    mElevator.ChaseTarget();
  }

  // Is finished if error is within 5 encoder counts
  @Override
  protected boolean isFinished() {
    if ((CHieght - THieght) <= ErrorRange && (CHieght - THieght) >= -1*(ErrorRange)){
      return true;
    }else{
    return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    sElevator.StopMotors();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
