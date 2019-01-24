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
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Drivetrain;

public class FollowTarget extends Command {
  private Drivetrain sDriveTrain = Robot.sDrivetrain;
  private Vision sVision = Robot.sVision;
  private double ErrorX;
  private double ErrorY;
  public boolean TargetDistanceCheck = false;
  private Command AlignWithTarget;
  public double steering_adjust;
  public double X;
  public double Y;
  public double distance_adjust;
  public double right_speed;
  public double left_speed;
  private Drivetrain sDrivetrain = Robot.sDrivetrain;

  public FollowTarget() {
    ErrorX = -sVision.getTargetX();
    ErrorY = -sVision.getTargetY();
    X = sVision.getTargetX();
    Y = sVision.getTargetY();
    right_speed = 0;
    left_speed = 0;
    //Requires(Drivetrain);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    steering_adjust = 0;
    distance_adjust = 0;
    //sDriveTrain.TargetAligned = false;
    sDriveTrain.TargetDistanceCheck = false;
    requires(sDrivetrain);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    ErrorX = -sVision.getTargetX();
    ErrorY = -sVision.getTargetY();
    X = sVision.getTargetX();
    Y = sVision.getTargetY();

    
    
    
    //if (TargetX < 1 && TargetX > -1) {
      // if (TargetX < -3) {
      //   sDriveTrain.TurnLeft();
      // } else if (TargetX > 3) {
      //   sDriveTrain.TurnRight();
      // } else {
      //   sDriveTrain.StopMotors();
      //   sDriveTrain.TargetAligned = true;
      // }
      // if (TargetX < -3 || TargetX > 3) {
      //   sDriveTrain.TargetAligned = false;
      // }
  if (Y > 0){
    if (X > 1){
        steering_adjust = (Constants.KpAim*ErrorX - Constants.min_aim_command);
        distance_adjust = (Constants.KpDistance + ErrorX);
        left_speed = steering_adjust + distance_adjust;
        right_speed = -1 * steering_adjust + distance_adjust;
        sDriveTrain.track_taget(left_speed, right_speed);
        //TargetDistanceCheck = false;
      } else if (X < 1) {
          steering_adjust = (Constants.KpAim*ErrorX + Constants.min_aim_command);
          //sDriveTrain.StopMotors();
          //TargetDistanceCheck = true;
          distance_adjust = (Constants.KpDistance + ErrorX);
          left_speed = steering_adjust + distance_adjust;
          right_speed = -1 * steering_adjust + distance_adjust;
          sDriveTrain.track_taget(left_speed, right_speed);
          
      
        
      }
    }else{

    }
    
  }
  

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (Y < 0) {
      return true;
    } else {
      return false;
    }
    
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    sDriveTrain.StopMotors();
    //sDriveTrain.TargetAligned = false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
