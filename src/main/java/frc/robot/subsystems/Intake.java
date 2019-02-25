package frc.robot.subsystems; // package declaration

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.VictorSP;

// imports

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * The intake subsystem is the main scoring subsystem of the robot. It can pick
 * up balls and hatches, and then release them into a scoring zone, such as the
 * cargo ship or rocket
 * 
 * @author CRahne, Tony, and Cole G
 */
public class Intake extends Subsystem {
  private final VictorSP mIntakeMotor = RobotMap.IntakeMotor;
  private final WPI_TalonSRX mWrist = RobotMap.WristMotor;
  private final ShuffleboardTab mIntakeTab = Shuffleboard.getTab("Intake Tab");
  private final double kWristTicksPerDegree = Constants.kWristTicksPerDegree;

  public Intake() {
    setSubsystem("Intake");
    addChild(mIntakeMotor);
    addChild(mWrist);
  }

  /**
   * moves the intake wrist up
   * 
   * @author Tony
   */
  public void WristUp() {
    mWrist.set(ControlMode.PercentOutput, 0.5);
  }
  
  /**
   * moves the intake wrist down
   * @author Tony
   */
  public void WristDown(){
    mWrist.set(ControlMode.PercentOutput,-0.35);
  }

  /**
   * stops the movement of the intake wrist
   * @author Tony
   */
  public void StopWrist(){
    mWrist.set(ControlMode.PercentOutput, 0);
  }
  public void WristMove(double targetAngle){
    double rawTargetTicks = targetAngle * kWristTicksPerDegree;
    SmartDashboard.putNumber("Target Angle", rawTargetTicks/kWristTicksPerDegree);
    SmartDashboard.putNumber("Target RAW", rawTargetTicks);
    mWrist.set(ControlMode.Position, rawTargetTicks);
  }
  /**
   * Will take a ball in
   * @author CRahne
   */
  public void In() {
    mIntakeMotor.set(-Constants.kMaxSpeed);
  }
  /**
   * Will shoot the ball out
   * @author CRahne
   */
  public void Out(){
    mIntakeMotor.set(Constants.kMaxSpeed);
  }
  /**
   * Stops the intake motors
   * @author Tony
   */
  public void IntakeMotorStop() {
    mIntakeMotor.set(0);
    mIntakeMotor.stopMotor();
  }

  public void WristMotorStop() {
    mWrist.set(ControlMode.PercentOutput, 0.0);
    mWrist.stopMotor();
  }
  
  /** 
   * Will update data on the shuffleboard tab for this class
   */
  public void UpdateTelemetry() {
    mIntakeTab.add("Motor Speed", mIntakeMotor.get());
    mIntakeTab.add("Wrist Position", mWrist.getSelectedSensorPosition());
    mIntakeTab.add(mIntakeMotor);
    mIntakeTab.add(mWrist);
    Shuffleboard.update();
  }
  
  @Override
  public void initDefaultCommand() {
  }
}