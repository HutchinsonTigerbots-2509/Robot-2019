/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Climber;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class ClimbEnd extends InstantCommand {
  private final Climber sClimber = Robot.sClimb;
  /**
   * Add your docs here.
   */
  public ClimbEnd() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {

    sClimber.RetractStageOne();
    sClimber.RetractStageTwo();
    sClimber.setMotorSpeed(0);

  }

}
