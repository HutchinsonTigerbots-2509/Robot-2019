/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.commands.elevator.ElevatorMoveLowGear;
import frc.robot.commands.wrist.WristMove;

public class StartPosition extends CommandGroup {
  /**
   * Add your docs here.
   */
  public StartPosition(){
    addSequential(new ElevatorMove(Constants.kElevatorStartingHeight));
    addSequential(new WristMove(Constants.kWristStartingAngle));
  }
}
