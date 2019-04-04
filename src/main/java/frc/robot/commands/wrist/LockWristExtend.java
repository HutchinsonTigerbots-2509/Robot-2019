/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj.Timer;

/**
 * Add your docs here.
 */
public class LockWristExtend extends InstantCommand {

  private Climber sClimb = Robot.sClimb;

  /**
   * Add your docs here.
   */
  public LockWristExtend() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    sClimb.WristLocked = true;
    Timer.delay(1.5);
    RobotMap.WristLockPiston.set(Value.kForward);
    Timer.delay(0.5);

  }

}
