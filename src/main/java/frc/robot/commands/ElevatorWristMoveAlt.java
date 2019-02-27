/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.wrist.WristMove;
import frc.robot.commands.elevator.*;

public class ElevatorWristMoveAlt extends InstantCommand {
  private String mState;
  private int targetID;
  public ElevatorWristMoveAlt(int heightID) {
    super();
    mState = Robot.sElevator.state;
    targetID = heightID;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    mState = Robot.sElevator.state;
    if(mState=="Cargo"){
      if(targetID == 1){
        new ElevatorMoveLowGear(Constants.kBallLow).start();
      }else if(targetID == 2){
        new ElevatorMoveLowGear(Constants.kBallMid).start();
      }else if(targetID == 3){
        new ElevatorMoveLowGear(Constants.kBallHigh).start();
      }
      new WristMove(Constants.kWristCargoAngle).start();
    }else{
      if(targetID == 1){
        new ElevatorMoveLowGear(Constants.kHatchLow).start();
      }else if(targetID == 2){
        new ElevatorMoveLowGear(Constants.kHatchMid).start();
      }else if(targetID == 3){
        new ElevatorMoveLowGear(Constants.kHatchHigh).start();
      }
      new WristMove(Constants.kWristHatchAngle).start();
    }
  }
}
