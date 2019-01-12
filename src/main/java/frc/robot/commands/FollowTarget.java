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
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision;

public class FollowTarget extends Command {
  private DriveTrain sDriveTrain = Robot.sDriveTrain;
  private Vision sVision = Robot.sVision;
  private double TargetX; 
  private boolean TargetDistanceCheck = false;
  public FollowTarget() {
    TargetX = sVision.getTargetX();
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
    if (sDriveTrain.TargetAligned == false){
      if(TargetX < 0){
        sDriveTrain.TurnLeft();
      } else if(TargetX > 0){
        sDriveTrain.TurnRight();
      } else{
        sDriveTrain.StopMotors();
      }
    } else if (sDriveTrain.TargetAligned == true && TargetDistanceCheck == false){
      sDriveTrain.MoveForward();
    }
    if(TargetX < -3 || TargetX > 3){
      sDriveTrain.TargetAligned = false;
    }
    if((Constants.kCameraHeight+Constants.kTargetHeight) / Math.tan(Constants.kCameraAngle + 4) < 24){
      sDriveTrain.StopMotors();
      TargetDistanceCheck = true;
    } else if(Constants.kCameraHeight / Math.tan(Constants.kCameraAngle + 4) > 24){
      TargetDistanceCheck = false;
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
    sDriveTrain.StopMotors();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
