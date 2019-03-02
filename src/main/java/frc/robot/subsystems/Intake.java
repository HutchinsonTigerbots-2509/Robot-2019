package frc.robot.subsystems; // package declaration

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.intake.IntakeIn;
import frc.robot.commands.intake.IntakeOut;
import frc.robot.commands.wrist.WristManualDown;
import frc.robot.commands.wrist.WristManualUp;

/**
 * The intake subsystem is the main scoring subsystem of the robot. It can pick
 * up balls and hatches, and then release them into a scoring zone, such as the
 * cargo ship or rocket
 * 
 * @author CRahne, Tony, and Cole G
 */
public class Intake extends Subsystem {
  private final VictorSP mIntakeMotor = RobotMap.IntakeMotor;
  private final WPI_TalonSRX mWristMotor = RobotMap.WristMotor;
  // private final ShuffleboardTab mIntakeMotorTab = Shuffleboard.getTab("Intake Tab");
  private final double kWristTicksPerDegree = Constants.kWristTicksPerDegree;
  private double mWristTargetTicks;

  public Intake() {
    setSubsystem("Intake");
    addChild(mIntakeMotor);
    addChild(mWristMotor);
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
    mIntakeMotor.stopMotor();
  }

  /**
   * moves the intake wrist up
   * 
   * @author Tony
   */
  public void WristUp() {
    mWristMotor.set(ControlMode.PercentOutput, 0.35);
  }
  
  /**
   * moves the intake wrist down
   * @author Tony
   */
  public void WristDown(){
    mWristMotor.set(ControlMode.PercentOutput, -0.35);
  }

  public void WristMove(double targetAngle){
    mWristTargetTicks = targetAngle* kWristTicksPerDegree;
    // SmartDashboard.putNumber("Target Angle", mWristTargetTicks/kWristTicksPerDegree);
    // SmartDashboard.putNumber("Target RAW", mWristTargetTicks);
    mWristMotor.set(ControlMode.Position, mWristTargetTicks);
  }
  
  public void ManualMove(Joystick stick){
    if(stick.getRawAxis(5) < -0.2){
      mWristMotor.set(ControlMode.PercentOutput, -stick.getRawAxis(5));
    }else if(stick.getRawAxis(5) > 0.2){
      mWristMotor.set(ControlMode.PercentOutput, -stick.getRawAxis(5));
    }else{
      mWristMotor.set(ControlMode.PercentOutput, 0);
    }
  }

  public void ManualMoveMark2(Joystick stick){
    if(stick.getRawAxis(5) < -0.2){
      mWristMotor.set(ControlMode.PercentOutput, -stick.getRawAxis(5));
    }else if(stick.getRawAxis(5) > 0.2){
      mWristMotor.set(ControlMode.PercentOutput, -stick.getRawAxis(5));
    }else{
      mWristMotor.set(ControlMode.PercentOutput, 0);
    }
  }

  public void IntakeInManual(Joystick stick){
    if(stick.getRawAxis(2) > 0.2){
      In();
    }else if(stick.getRawAxis(3) > 0.2){
      Out();
    }else{
      mIntakeMotor.set(0);
    }

  }

  /**
   * stops the movement of the intake wrist
   * @author Tony
   */
  public void StopWrist(){
    mWristMotor.stopMotor();
  }
  
  /** 
   * Will update data on the shuffleboard tab for this class
   */
  public void UpdateTelemetry() {
    // // Subsystems Status
    // mIntakeMotorTab.add("Wrist Motor Speed", mWristMotor.get());
    // mIntakeMotorTab.add("Wrist Position", mWristMotor.getSelectedSensorPosition());
    // // Subsystems Object
    // mIntakeMotorTab.add(mIntakeMotor);
    // mIntakeMotorTab.add("Motor Speed", mIntakeMotor.get());
    // mIntakeMotorTab.add("Wrist Position", mWristMotor.getSelectedSensorPosition());
    // mIntakeMotorTab.add(mIntakeMotor);
    // mIntakeMotorTab.add(mWristMotor);
    // // Subsystem Intake
    // mIntakeMotorTab.add("Intake In", new IntakeIn());
    // mIntakeMotorTab.add("Intake Out", new IntakeOut());
    // mIntakeMotorTab.add("Wrist Up", new WristManualUp());
    // mIntakeMotorTab.add("Wrist Down", new WristManualDown());

    Shuffleboard.update();
  }
  
  @Override
  public void initDefaultCommand() {
  }

  public double getCurrentAngle(){
    return (mWristMotor.getSelectedSensorPosition()/Constants.kWristTicksPerDegree);
  }
  public double getCurrentWristTicks(){
    return mWristMotor.getSelectedSensorPosition();
  }

  public WPI_TalonSRX getWrist(){
    return mWristMotor;
  }
  public VictorSP getIntake(){
    return mIntakeMotor;
  }
}