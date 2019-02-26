/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.commands.elevator.ElevatorWristMove;

public class IntakeHatchBrush extends CommandGroup {
  /**
   * Add your docs here.
   */
  public IntakeHatchBrush() {
    addSequential(new ElevatorWristMove(Constants.kWristHatchBrushAngle, Constants.kHatchLow));
  }
}
