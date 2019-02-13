/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Elevator;

public class ElevatorShift extends InstantCommand {
  private final Elevator mElevator = new Elevator();
  private boolean isShifted;
  public ElevatorShift() {
    super();
    requires(mElevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    isShifted = mElevator.isHighGear();
    mElevator.setHighGear(!isShifted);
  }
}
