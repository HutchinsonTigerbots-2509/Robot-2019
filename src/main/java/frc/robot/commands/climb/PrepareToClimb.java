/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.commands.elevator.ElevatorMoveHighGear;
import frc.robot.commands.wrist.WristMove;
import frc.robot.subsystems.Climber;
import frc.robot.Robot;
public class PrepareToClimb extends CommandGroup {
  private Climber sClimber = Robot.sClimb;
  /**
   * Add your docs here.
   */
  public PrepareToClimb() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
    addParallel(new WristMove(Constants.kWristGroundAngle));
    addSequential(new ElevatorMoveHighGear(Constants.kHABHeight));
    addSequential(new LockWrist());
    sClimber.PreparedToClimb = true;
  }
}
