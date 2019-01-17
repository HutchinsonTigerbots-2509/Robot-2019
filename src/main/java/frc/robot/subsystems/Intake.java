/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.hal.sim.mockdata.RoboRioDataJNI;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final DoubleSolenoid leftPush = RobotMap.leftPush;
  private final DoubleSolenoid rightPush = RobotMap.rightPush;
  private final DoubleSolenoid wrist = RobotMap.wrist;
  private final DoubleSolenoid open = RobotMap.open;
  private final SpeedController intakeMotors = RobotMap.intakeMotors;
  private final double kMaxSpeed =  Constants.kMaxSpeed;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }


  // Controls the intake motors and allows you to take in and reverse the motors.
  /**
   * 
   * @author Cole 
   * @author Tony
   */
  public void In(){
    intakeMotors.set(kMaxSpeed);      
  }
  /**
   * 
   * @author Cole 
   * @author Tony
   */
  public void Out(){
    intakeMotors.set(kMaxSpeed);
  }
  /**
   * 
   * @author Cole 
   * @author Tony
   */
  public void motorsStop(){
    intakeMotors.stopMotor();
  }
// Moves the intake arms up or down
/**
   * 
   * @author Cole 
   * @author Tony
   */
  public void Up(){
    wrist.set(Value.kForward);
  }
  /**
   * 
   * @author Cole 
   * @author Tony
   */  
  public void Down(){
    wrist.set(Value.kReverse);
  }
  /**
   * 
   * @author Cole 
   * @author Tony
   */
  public void StopWrist(){
    wrist.set(Value.kOff);
  }
// Controls the intake arms and allows you to open them up 
/**
   * 
   * @author Cole 
   * @author Tony
   */
  public void Open(){
    open.set(Value.kForward);
  }
  /**
   * 
   * @author Cole 
   * @author Tony
   */
  public void Close(){
    open.set(Value.kReverse);
  }
  /**
   * 
   * @author Cole 
   * @author Tony
   */
  public void StopOpen(){
    open.set(Value.kOff);
  }
}
