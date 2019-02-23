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
  private final VictorSP mIntakeMotor = RobotMap.IntakeMotor;
  private final WPI_TalonSRX mWristMotor = RobotMap.WristMotor;
  private final ShuffleboardTab mIntakeMotorTab = Shuffleboard.getTab("Intake Tab");
  private final double kWristTicksPerDegree = Constants.kWristTicksPerDegree;

  public Intake() {
    setSubsystem("Intake");
    addChild(mIntakeMotor);
    addChild(mWristMotor);
  }

  /**
   * moves the intake wrist up
   * 
   * @author Tony
   */
  public void WristUp() {
    mWristMotor.set(ControlMode.PercentOutput, 0.5);
  }
  
  /**
   * moves the intake wrist down
   * @author Tony
   */
  public void WristDown(){
    mWristMotor.set(ControlMode.PercentOutput,-0.35);
  }

  /**
   * stops the movement of the intake wrist
   * @author Tony
   */
  public void StopWrist(){
    mWristMotor.set(ControlMode.PercentOutput,0);
  }
  public void WristMove(double targetAngle){
    double rawTargetTicks = targetAngle* kWristTicksPerDegree;
    SmartDashboard.putNumber("Target Angle", rawTargetTicks/kWristTicksPerDegree);
    SmartDashboard.putNumber("Target RAW", rawTargetTicks);
    mWristMotor.set(ControlMode.Position, rawTargetTicks);
  }
  /**
   * Will take a ball in
   * @author CRahne
   */
  public void In() {
    mIntakeMotor.set(Constants.kMaxSpeed);
  }
  /**
   * Will shoot the ball out
   * @author CRahne
   */
  public void Out(){
    mIntakeMotor.set(-Constants.kMaxSpeed);
  }
  /**
   * Stops the intake motors
   * @author Tony
   */
  public void CargoMotorStop() {
    mIntakeMotor.set(0);
    mIntakeMotor.stopMotor();
  }

  public void WristMotorStop() {
    mWristMotor.set(ControlMode.PercentOutput, 0.0);
    mWristMotor.stopMotor();
  }

  public double CurrentAngle(){
    return (mWristMotor.getSelectedSensorPosition()/Constants.kWristTicksPerDegree);
  }

  public void MotorStop() {
    mIntakeMotor.stopMotor();
  }
  
  /** 
   * Will update data on the shuffleboard tab for this class
   */
  public void UpdateTelemetry() {
    // Subsystems Status
    mIntakeMotorTab.add("Wrist Motor Speed", mWristMotor.get());
    mIntakeMotorTab.add("Wrist Position", mWristMotor.getSelectedSensorPosition());
    // Subsystems Object
    mIntakeMotorTab.add(mIntakeMotor);
    mIntakeMotorTab.add("Motor Speed", mIntakeMotor.get());
    mIntakeMotorTab.add("Wrist Position", mWristMotor.getSelectedSensorPosition());
    mIntakeMotorTab.add(mIntakeMotor);
    mIntakeMotorTab.add(mWristMotor);
    // Subsystem Intake
    mIntakeMotorTab.add("Intake In", new IntakeIn());
    mIntakeMotorTab.add("Intake Out", new IntakeOut());
    mIntakeMotorTab.add("Wrist Up", new WristManualUp());
    mIntakeMotorTab.add("Wrist Down", new WristManualDown());

    Shuffleboard.update();
  }
  
  @Override
  public void initDefaultCommand() {
  }
}