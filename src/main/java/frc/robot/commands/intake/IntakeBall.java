/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
// import frc.robot.commands.ElevatorWristMove;

public class IntakeBall extends CommandGroup {
  /**
   * Add your docs here.
   */
  public IntakeBall() {
    // addParallel(new ElevatorWristMove(Constants.kWristGroundAngle, Constants.kHomePositionInches));
    //addSequential(new ElevatorMoveLowGear(Constants.kHomePositionInches));
    addSequential(new IntakeIn());
  }
}
