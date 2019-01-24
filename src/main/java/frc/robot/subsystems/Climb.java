/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Climb extends Subsystem {

  private final DoubleSolenoid mFirstStage = RobotMap.ClimbFirstStage;
  private final DoubleSolenoid mSecondStage = RobotMap.ClimbSecondStage;

  /**
   * Extends the climb pistons
   * 
   * @author Cole
   * @author Tony
   */
  public void Extend() {
    mFirstStage.set(Value.kForward);
    mSecondStage.set(Value.kForward);
  }

  /**
   * Stops the climb pistons
   * 
   * @author Cole
   * @author Tony
   */
  public void Stop() {
    mFirstStage.set(Value.kOff);
    mSecondStage.set(Value.kOff);
  }

  /**
   * Retracts the climb pistons
   * 
   * @author Cole
   * @author Tony
   */
  public void Retract() {
    mFirstStage.set(Value.kReverse);
    mSecondStage.set(Value.kReverse);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
