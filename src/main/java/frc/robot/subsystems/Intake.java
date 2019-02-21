package frc.robot.subsystems; // package declaration

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.VictorSP;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakeOut;
import frc.robot.commands.WristManualDown;
import frc.robot.commands.WristManualUp;

/**
 * The intake subsystem is the main scoring subsystem of the robot. It can pick
 * up balls and hatches, and then release them into a scoring zone, such as the
 * cargo ship or rocket
 * 
 * @author CRahne, Tony, and Cole G
 */
public class Intake extends Subsystem {
  private final VictorSP mMotor = RobotMap.IntakeMotor;
  private final WPI_TalonSRX mWrist = RobotMap.WristMotor;
  private final ShuffleboardTab mIntakeTab = Shuffleboard.getTab("Intake Tab");
  private final double kWristTicksPerDegree = Constants.kWristTicksPerDegree;

  public Intake() {
    setSubsystem("Intake");
    addChild(mMotor);
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
    SmartDashboard.putNumber("Current Angle", CurrentAngle());
    mWrist.set(ControlMode.Position, rawTargetTicks);
  }
  /**
   * Will take a ball in
   * @author CRahne
   */
  public void In() {
    mMotor.set(Constants.kMaxSpeed);
  }
  /**
   * Will shoot the ball out
   * @author CRahne
   */
  public void Out(){
    mMotor.set(Constants.kMaxSpeed);
  }
  /**
   * Stops the intake motors
   * @author Tony
   */
  public void MotorStop() {
    mMotor.stopMotor();
  }
  
  /** 
   * Will update data on the shuffleboard tab for this class
   */
  public void UpdateTelemetry() {
    // Subsystems Status
    mIntakeTab.add("Motor Speed", mMotor.get());
    mIntakeTab.add("Wrist Position", mWrist.getSelectedSensorPosition());
    // Subsystems Object
    mIntakeTab.add(mMotor);
    mIntakeTab.add(mWrist);
    // Subsystem Intake
    mIntakeTab.add("Intake In", new IntakeIn());
    mIntakeTab.add("Intake Out", new IntakeOut());
    mIntakeTab.add("Wrist Up", new WristManualUp());
    mIntakeTab.add("Wrist Down", new WristManualDown());

    Shuffleboard.update();
  }
  
  /**
   * Will return the intake motor
   * @return Intake Motor
   */
  public VictorSP getIntakeMotor() {
    return mMotor;
  }
  
  /**
   * Retruns the wrist motor;
   * @return Wrist MotorS
   */
  public WPI_TalonSRX getWristMotor(){
    return mWrist;
  }

  public double CurrentAngle(){
    return (mWrist.getSelectedSensorPosition()/Constants.kWristTicksPerDegree);
  }

  public double getWristPower(){
    return mWrist.get();
  }
  public double getWristVoltage(){
    return mWrist.getBusVoltage();
  }
  
  
  @Override
  public void initDefaultCommand() {
  }
}