/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.RobotMap;

/**
 * The climbing subsystem is the subsystem where we climb up
 * onto the habitat ramps. We do this be using a two stage
 * piston system on both sides of the robot, with the lower ones
 * firing first, followed by the second stage
 *
 * <h3> JavaDoc Categories for Functions: </h3>
 * <li> + Climbing Voids - All of the voids related to climbing
 * <li> + General - Voids that interact with all of the subsystem components
 * <li> + Climb Getters - Will return objects and data
 *
 * @author Cole
 */
public class Climb extends Subsystem {
  private final DoubleSolenoid HigherPistons = RobotMap.ClimbHigherPiston;
  private final DoubleSolenoid LowerPistons = RobotMap.ClimbLowerPiston;
  private final WPI_TalonSRX Motor = RobotMap.ClimbMotor;
  private final ShuffleboardTab mClimb = Shuffleboard.getTab("Climb");
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Climb(){
    setSubsystem("Climb");
    addChild(HigherPistons);
    addChild(LowerPistons);
    addChild(Motor);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  
  // #region Climbing Voids
  /**
   * Extends the Higher Pistons
   * 
   * @author Cole
   */
  public void ExtendHigherPistons(){
    HigherPistons.set(Value.kForward);
    UpdateTelemetry();
  }

  /**
   * Extends the Lower Pistons
   * 
   * @author Cole
   */
  public void ExtendLowerPistons(){
    LowerPistons.set(Value.kForward);
    UpdateTelemetry();
  }
  
  /**
   * Retracts the Higher Pistons
   * 
   * @author Cole
   */
  public void RetractHigherPistons(){
    HigherPistons.set(Value.kReverse);
    UpdateTelemetry();
  }

  /**
   * Retracts the Lower Pistons
   * 
   * @author Cole
   */
  public void RetractLowerPistons(){
    LowerPistons.set(Value.kReverse);
    UpdateTelemetry();
  }

  /**
   * Stops all Pistons
   * 
   * @author Cole
   */
  public void StopPistons(){
    HigherPistons.set(Value.kOff);
    LowerPistons.set(Value.kOff);
    UpdateTelemetry();
  }

  // #endregion Climbing Voids
  // #region General

  // #endregion General
  // #region Climb Getters
  /**
   * Will return whether the pistons are Extended or Retracted
   * @return
   */
  public String getHigherPistonsStatus() {
    if (HigherPistons.get() == Value.kForward){
      return "Extended";
    } else if (HigherPistons.get() == Value.kReverse){
      return "Retracted";
    } else {
      return "Null";
    }
  }

  /**
   * Will return whether the pistons are Extended or Retracted
   * @return
   */
  public String getLowerPistonsStatus() {
    if (LowerPistons.get() == Value.kForward){
      return "Extended";
    } else if (LowerPistons.get() == Value.kReverse){
      return "Retracted";
    } else {
      return "Null";
    }
  }

  // #endregion Climb Getters

  /**
   * Updates the data for shuffleboard
   */
  public void UpdateTelemetry(){
    mClimb.add("Higher Pistons", getHigherPistonsStatus());
    mClimb.add("Lower Pistons", getLowerPistonsStatus());
    mClimb.add("Motor Speed", Motor.get());
  }
}
