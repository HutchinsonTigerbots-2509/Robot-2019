/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.ElevatorWristMove;
import frc.robot.commands.elevator.ElevatorMoveLowGear;
import frc.robot.commands.intake.IntakeIn;
import frc.robot.commands.vision.FollowTarget;
import frc.robot.commands.vision.ChangePipeline;
import frc.robot.subsystems.Vision;
public class IntakeBall extends CommandGroup {
  private Vision sVision = Robot.sVision;
  /**
   * Add your docs here.
   */
  public IntakeBall() {
    addParallel(new IntakeIn());
    addParallel(new ElevatorWristMove(Constants.kWristGroundAngle, Constants.kHomePositionInches));
    //addParallel(new ElevatorMoveLowGear(Constants.kHomePositionInches));
    //addParallel(new IntakeIn());
    //addParallel(new ChangePipeline(2));
    
    addParallel(new FollowTarget(2, -0.02, -0.1));
    
    
    // addParallel(new ElevatorWristMove(Constants.kWristGroundAngle, Constants.kHomePositionInches));
    //addSequential(new ElevatorMoveLowGear(Constants.kHomePositionInches));
    //addSequential(new IntakeIn());
  }
}
