/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.commands.vision.FollowTarget;
import frc.robot.commands.ElevatorWristMove;

public class IntakeHatchBrush extends CommandGroup {
  /**
   * Add your docs here.
   */
  public IntakeHatchBrush() {
    addSequential(new ElevatorWristMove(Constants.kWristHatchAngle, Constants.kHatchLow));
    addSequential(new FollowTarget(0, -0.001, -0.001));
  }
}
