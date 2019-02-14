/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Climb;

public class RetractPistons extends InstantCommand {
  private final Climb sClimb = new Climb();
  public RetractPistons() {
    super();
    requires(sClimb);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    sClimb.RetractHigherPistons();
    sClimb.RetractLowerPistons();
    Timer.delay(0.1);
    sClimb.StopPistons();
  }
}
