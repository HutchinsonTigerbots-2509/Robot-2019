/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Climber;

/**
 * Add your docs here.
 */
public class ClimbHab2 extends InstantCommand {
  private final Climber sClimber = Robot.sClimb;
  /**
   * Add your docs here.
   */
  public ClimbHab2() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {

    sClimber.StageOneStart();
    sClimber.StageTwoStart();
    Timer.delay(1);
    //sClimber.setMotorSpeed(-0.5);
  }

}
