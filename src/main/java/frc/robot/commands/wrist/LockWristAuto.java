/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.commands.elevator.ElevatorMoveHighGear;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.commands.elevator.ZeroElevator;

public class LockWristAuto extends CommandGroup {
  /**
   * Add your docs here.
   */
  public LockWristAuto() {
    
    //addParallel(new WristMove(-95));
    // addSequential(new ElevatorMoveHighGear(Constants.kHomePositionInches));
    addSequential(new ZeroElevator());
    addSequential(new ZeroWrist());
    Timer.delay(2);
    addSequential(new LockWristExtend());
    Timer.delay(1.5);
    addSequential(new LockWristRetract());
    Timer.delay(2);


  }
}
