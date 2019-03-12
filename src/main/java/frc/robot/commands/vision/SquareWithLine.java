/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Drivetrain;

public class SquareWithLine extends Command {
  private Vision sVision = Robot.sVision;
  private Drivetrain sDrivetrain = Robot.sDrivetrain;
  private double TargetX;
  private double TargetY;
  private double TargetAngle;
  private double InitialTargetAngle;
  private double InitialTargetX;
  public int State;
  private double turnSpeed = .5;
  private double moveSpeed = .5;
  public SquareWithLine() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //sVision.change_vision_pipeline(pipeline); //NEEDS TO BE ADDED
    TargetX = sVision.getTargetX();
    InitialTargetX = sVision.getTargetX();
    TargetY = sVision.getTargetY();
    TargetAngle = sVision.getTargetSkew();
    InitialTargetAngle = sVision.getTargetSkew();
    State = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    TargetX = sVision.getTargetX();
    TargetY = sVision.getTargetY();
    TargetAngle = sVision.getTargetSkew();


    //Turns the robot perpendicular to the line
    if(State == 0){
      if(TargetAngle > 0 && TargetAngle < 89){
        //sDrivetrain.turnLeft(turnSpeed);
        //sDrivetrain.turnRight(turnSpeed);
      } else if(TargetAngle < 0 && TargetAngle > -89){
        //sDrivetrain.turnLeft(turnSpeed);
        //sDrivetrain.turnRight(turnSpeed);
      } else if (TargetAngle == 0){
        if(TargetX < 0){
          sDrivetrain.turnLeft(turnSpeed);
        } else if(TargetX > 0){
          sDrivetrain.turnRight(turnSpeed);
        }
      } else {
        State = 1;
      }
    }


    //Drives the robot forward, so that when it turns it will be lined up
    if(State == 1){
      if(TargetY > 5){
        sDrivetrain.driveForwardWhileCalled(moveSpeed);
      } else if (TargetY < 5){
        State = 2;
      }
    }


    //Turns the robot so that it is square with the line
    if(State == 2){
      if(InitialTargetAngle > 0){
        //turn
      } else if (InitialTargetAngle < 0){
        //turn
      } else if (InitialTargetAngle == 0){
        if(InitialTargetX < 0){
          //turn
        } else if(InitialTargetX > 0){
          //turn
        }
      }
      if(TargetAngle <= 1 && TargetAngle >= -1){ //maybe add a failsafe here, checking if the TargetX value is within an error range
        State = 3;
      }
    }


    //drives the robot forward, remove if necessary
    if(State == 3){
      if(TargetY > 0){
        sDrivetrain.driveForwardWhileCalled(moveSpeed);
      } else {
        State = 4;
      }
    }




  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(State == 4){
      return true;
    } else {
    return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    sDrivetrain.StopMotors();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
