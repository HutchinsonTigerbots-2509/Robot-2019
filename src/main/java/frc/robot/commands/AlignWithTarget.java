/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision;

public class AlignWithTarget extends Command {
  private DriveTrain sDriveTrain = Robot.sDriveTrain;
  private Vision sVision = Robot.sVision;
  private double TargetX;

  public AlignWithTarget() {
    TargetX=sVision.getTargetX();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    sDriveTrain.TargetAligned = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    TargetX=sVision.getTargetX();
    if(TargetX < 0){
      sDriveTrain.TurnLeft();
    } else if(TargetX > 0){
      sDriveTrain.TurnRight();
    } else{
      end();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
  if(TargetX < 0.25 && TargetX > -0.25){
    return TargetX < 0.25 && TargetX > -0.25;
  }else{
    return false;
  }

  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    sDriveTrain.StopMotors();
    sDriveTrain.TargetAligned = true;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
