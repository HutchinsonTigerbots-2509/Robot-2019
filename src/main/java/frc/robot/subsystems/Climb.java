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
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Climb extends Subsystem {
  private final DoubleSolenoid HigherPistons = RobotMap.ClimbHigherPiston;
  private final DoubleSolenoid LowerPistons = RobotMap.ClimbLowerPiston;
  private final WPI_TalonSRX Motor = RobotMap.ClimbMotor;
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

  /**
   * Extends the Higher Pistons
   * 
   * @author Cole
   */
  public void ExtendHigherPistons(){
    HigherPistons.set(Value.kForward);
  }

  /**
   * Extends the Lower Pistons
   * 
   * @author Cole
   */
  public void ExtendLowerPistons(){
    LowerPistons.set(Value.kForward);
  }
  
  /**
   * Retracts the Higher Pistons
   * 
   * @author Cole
   */
  public void RetractHigherPistons(){
    HigherPistons.set(Value.kReverse);
  }

  /**
   * Retracts the Lower Pistons
   * 
   * @author Cole
   */
  public void RetractLowerPistons(){
    LowerPistons.set(Value.kReverse);
  }

  /**
   * Stops all Pistons
   * 
   * @author Cole
   */
  public void StopPistons(){
    HigherPistons.set(Value.kOff);
    LowerPistons.set(Value.kOff);
  }
}
