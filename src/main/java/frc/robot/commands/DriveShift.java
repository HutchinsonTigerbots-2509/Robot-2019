/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Drivetrain;

/**
 * Add your docs here.
 */
public class DriveShift extends InstantCommand {
  private Drivetrain mDriveTrain = new Drivetrain();
  private boolean isShifted;
  /**
   * Add your docs here.
   */
  public DriveShift() {
    super();
    requires(mDriveTrain);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    // isShifted = mDriveTrain.isShifted();
    // mDriveTrain.setHighGear(!isShifted);
    mDriveTain.DriveShift
  }

}
