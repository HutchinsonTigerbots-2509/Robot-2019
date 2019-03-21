/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.elevator.ElevatorShiftLowAuto;
import frc.robot.commands.wrist.LockWristAuto;
import frc.robot.subsystems.Climber;
public class PrepareToClimb extends CommandGroup {
  private Climber sClimber = Robot.sClimb;
  /**
   * Add your docs here.
   */
  public PrepareToClimb() {
    
    addSequential(new LockWristAuto());
    addSequential(new ElevatorShiftLowAuto());

    // addParallel(new WristMove(Constants.kWristGroundAngle));
    // addSequential(new ElevatorMoveHighGear(Constants.kHABHeight));
    // // addSequential(new LockWrist());
    // sClimber.PreparedToClimb = true;
  }
}
