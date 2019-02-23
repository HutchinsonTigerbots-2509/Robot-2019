/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

/**
 * Add your docs here.
 */
public class DriveShift extends InstantCommand {
  private Drivetrain sDriveTrain = Robot.sDrivetrain;
  private boolean isShifted;
  /**
   * Add your docs here.
   */
  public DriveShift() {
    super();
    requires(sDriveTrain);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    if(sDriveTrain.getCurrentShifter() == Value.kReverse) {
      sDriveTrain.shiftToHigh();
      SmartDashboard.putString("Shifted to", "High");
    }
    else if(sDriveTrain.getCurrentShifter() == Value.kForward) {
      sDriveTrain.shiftToLow();
      SmartDashboard.putString("Shifted to", "Low");
    }
  }

}
