/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

//import com.sun.org.apache.xpath.internal.operations.And;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Constants;
import frc.robot.subsystems.Vision;

public class AlignWithTarget extends Command {
  private Drivetrain sDriveTrain = Robot.sDrivetrain;
  private Vision sVision = Robot.sVision;
  private double TargetX;
  private double TargetY;
  private double X;
  private double ErrorX;
  private double steering_adjust;
  public AlignWithTarget() {
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
    ErrorX = -sVision.getTargetX();
    //ErrorY = -sVision.getTargetY();
    X = sVision.getTargetX();
    if (X > 0){
      steering_adjust = Constants.KpAim * ErrorX - Constants.min_aim_command;
      
      //left_speed = steering_adjust;
      //+ distance_adjust;
      //right_speed = steering_adjust;
      // + distance_adjust;
      
      //SmartDashboard.putNumber("right_2", right_speed);
      //SmartDashboard.putNumber("left_2", left_speed);
      
      sDriveTrain.track_taget(0 , steering_adjust, 0);
      //SmartDashboard.putNumber("distance_adjust", distance_adjust);
      //TargetDistanceCheck = false;
    } else if (X < 0) {
        //SmartDashboard.putNumber("right", right_speed
        //SmartDashboard.putNumber("left", left_speed;
        steering_adjust = Constants.KpAim * ErrorX + Constants.min_aim_command;
        //sDriveTrain.StopMotors();
        //TargetDistanceCheck = true;
        
        //left_speed = steering_adjust; 
        //+ distance_adjust;
        //right_speed = steering_adjust; 
        //+ distance_adjust;
        //SmartDashboard.putNumber("right", right_speed);
        //SmartDashboard.putNumber("left", left_speed);
        //sDriveTrain.track_taget(left_speed , -right_speed);
        //SmartDashboard.putNumber("distance_adjust", distance_adjust);
      
        sDriveTrain.track_taget(0, steering_adjust, 0);

    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (X < 0.25 && X > 0) {
      return X < 0.25 && X > 0 ;
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
