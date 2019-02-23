/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Elevator;
public class ResetGyro extends InstantCommand {
  private Drivetrain sDriveTrain = Robot.sDrivetrain;
  private Intake sIntake = Robot.sIntake;
  private Elevator sElevator = Robot.sElevator;
  public ResetGyro() {
    super();
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    sDriveTrain.ResetGyro();
    sElevator.Reset_Elevator();
    
  }
}
