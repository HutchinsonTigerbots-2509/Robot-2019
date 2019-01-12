  /*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.hal.sim.mockdata.RoboRioDataJNI;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;

/**
 * @author Tony
 * @author Cole
 */
public class Intake extends PIDSubsystem {
  private static DoubleSolenoid leftPush = RobotMap.leftPush;
  private static DoubleSolenoid rightPush = RobotMap.rightPush;
  private static DoubleSolenoid wrist = RobotMap.wrist;
  private static DoubleSolenoid open = RobotMap.open;
  private static SpeedController intakeMotors = RobotMap.intakeMotors;
  /**
   * Add your docs here.
   */
   
  public Intake() {
    // Intert a subsystem name and PID values here
    super("SubsystemName", 1, 2, 3);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  

  }
  public void intakein(){
    intakeMotors.set(1.0);      
  }
  public void wristup(){
    wrist.set(Value.kForward);
  }                                                    
  public void intakeout(){
    intakeMotors.set(-1.0);
  }
  public void wristdown(){
    wrist.set(Value.kReverse);
  }
  public void stopWrist(){
    wrist.set(Value.kOff);
  }
  public void stopMotors(){
    intakeMotors.stopMotor();
  }
  public void openintake(){
    open.set(Value.kForward);
  }
  public void closeintake(){
    open.set(Value.kReverse);
  }
  public void stopOpen(){
    open.set(Value.kOff);
  }
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return 0.0;
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
  }
}
