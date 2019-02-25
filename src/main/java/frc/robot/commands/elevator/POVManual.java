/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Elevator;

public class POVManual extends Command {
  private OI oi = Robot.oi;
  private Elevator sElevator = Robot.sElevator;
  private Intake sIntake = Robot.sIntake;
  public POVManual() {
    //requires(sElevator);
    //requires(sIntake);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(oi.getCoOperatorStick().getPOV() == 0 && oi.getCoOperatorStick().getRawButtonPressed(10)){
      sElevator.ElevatorUp();
    } else if (oi.getCoOperatorStick().getPOV() == 180 && oi.getCoOperatorStick().getRawButtonPressed(10)){
      sElevator.ElevatorDown();
    } else if (oi.getCoOperatorStick().getPOV() == 0 && oi.getCoOperatorStick().getRawButtonPressed(10) == false){
      sIntake.WristUp();
    } else if (oi.getCoOperatorStick().getPOV() == 180 && oi.getCoOperatorStick().getRawButtonPressed(10) == false){
      sIntake.WristDown();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(oi.getCoOperatorStick().getPOV() == -1){
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
