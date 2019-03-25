/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.elevator.ElevatorMoveLowGear;
import frc.robot.commands.wrist.WristMove;

public class ElevatorWristMove extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ElevatorWristMove(int angle, double height) {
    addParallel(new ElevatorMoveLowGear(height));
    addSequential(new WristMove(angle));
  }
  public ElevatorWristMove(int heightID){
    // String state = Robot.sElevator.state;
    if(Robot.sElevator.state=="Hatch"){
      // SmartDashboard.putString("Buttons", Robot.sElevator.state);
      if(heightID==1){
        addParallel(new ElevatorMoveLowGear(Constants.kHatchLow));
        addSequential(new WristMove(Constants.kWristHatchAngle));
      }else if(heightID==2){
        addParallel(new ElevatorMoveLowGear(Constants.kHatchMid));
        addSequential(new WristMove(Constants.kWristHatchAngle));
      }else if(heightID==3){
        addParallel(new ElevatorMoveLowGear(Constants.kHatchHigh));
        addSequential(new WristMove(Constants.kWristHatchAngle));

      }
    }else if(Robot.sElevator.state=="Cargo"){
      // SmartDashboard.putString("Buttons", Robot.sElevator.state);
      if(heightID==1){
        addParallel(new ElevatorMoveLowGear(Constants.kBallLow));
        addSequential(new WristMove(Constants.kWristCargoAngle));
      }else if(heightID==2){
        addParallel(new ElevatorMoveLowGear(Constants.kBallMid));
        addSequential(new WristMove(Constants.kWristCargoAngle));
      }else if(heightID==3){
        addParallel(new ElevatorMoveLowGear(Constants.kBallHigh));
        addSequential(new WristMove(Constants.kWristCargoAngle));
      }
    }
  }
}
