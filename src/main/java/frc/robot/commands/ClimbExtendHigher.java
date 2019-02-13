/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Climb;

public class ClimbExtendHigher extends InstantCommand {
  private final Climb sClimb = new Climb();
  public ClimbExtendHigher() {
    super();
    requires(sClimb);
  }
  
  @Override
  protected void initialize() {
    sClimb.ExtendHigherPistons();
    Timer.delay(0.1);
    sClimb.StopPistons();
  }
}
