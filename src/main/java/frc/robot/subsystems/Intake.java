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
  private final VictorSP mCargoMotor = RobotMap.IntakeMotor;
  private final WPI_TalonSRX mWrist = RobotMap.WristMotor;
  private final ShuffleboardTab mIntakeTab = Shuffleboard.getTab("Intake Tab");
  private final double kWristTicksPerDegree = Constants.kWristTicksPerDegree;

  public Intake() {
    setSubsystem("Intake");
    addChild(mCargoMotor);
    addChild(mWrist);
  }

  /**
   * moves the intake wrist up
   * 
   * @author Tony
   */
  public void WristUp() {
    mWrist.set(ControlMode.PercentOutput,0.9);
  }
  /**
   * moves the intake wrist down
   * @author Tony
   */
  public void WristDown(){
    mWrist.set(ControlMode.PercentOutput,-0.5);//-0.9
  }

  /**
   * stops the movement of the intake wrist
   * @author Tony
   */
  public void StopWrist(){
    mWrist.set(ControlMode.PercentOutput,0);
  }
  public void WristMove(double targetAngle){
    double rawTargetTicks = targetAngle* kWristTicksPerDegree;
    SmartDashboard.putNumber("Target Angle", rawTargetTicks/kWristTicksPerDegree);
    SmartDashboard.putNumber("Target RAW", rawTargetTicks);
    mWrist.set(ControlMode.Position, rawTargetTicks);
  }
  /**
   * Will take a ball in
   * @author CRahne
   */
  public void In() {
    mCargoMotor.set(Constants.kMaxSpeed);
  }
  /**
   * Will shoot the ball out
   * @author CRahne
   */
  public void Out(){
    mCargoMotor.set(Constants.kMaxSpeed);
  }
  /**
   * Stops the intake motors
   * @author Tony
   */
  public void MotorsStop() {
    mCargoMotor.stopMotor();
    mWrist.stopMotor();
  }
  
  /** 
   * Will update data on the shuffleboard tab for this class
   */
  public void UpdateTelemetry() {
    mIntakeTab.add("Motor Speed", mCargoMotor.get());
    mIntakeTab.add("Wrist Position", mWrist.getSelectedSensorPosition());
    mIntakeTab.add(mCargoMotor);
    mIntakeTab.add(mWrist);
    Shuffleboard.update();
  }
  
  @Override
  public void initDefaultCommand() {
  }
}