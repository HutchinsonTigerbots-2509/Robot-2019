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
  private double TargetY;
  private boolean TargetDistanceCheck = false;
  public FollowTarget() {
    TargetX = sVision.getTargetX();
    TargetY = sVision.getTargetY();
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
    TargetY = sVision.getTargetY();
    if (sDriveTrain.TargetAligned == false){
      if(TargetX < -3){
        sDriveTrain.TurnLeft();
      } else if(TargetX > 3){
        sDriveTrain.TurnRight();
      } else{
        sDriveTrain.StopMotors();
        sDriveTrain.TargetAligned = true;
      }
    if(TargetX < -3 || TargetX > 3){
      sDriveTrain.TargetAligned = false;
    }
    if((Constants.kTargetHeight-Constants.kCameraHeight) / Math.tan(Math.toRadians(Constants.kCameraAngle) + Math.toRadians(TargetY)) < 24){
      sDriveTrain.StopMotors();
      TargetDistanceCheck = true;
    } else if((Constants.kTargetHeight-Constants.kCameraHeight) / Math.tan(Math.toRadians(Constants.kCameraAngle) + Math.toRadians(TargetY)) < 24){
      TargetDistanceCheck = false;
    }
    } else if (sDriveTrain.TargetAligned == true && TargetDistanceCheck == false){
      sDriveTrain.MoveForward();
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
