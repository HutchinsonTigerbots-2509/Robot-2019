/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {

  private final WPI_TalonSRX SpoolMaster = RobotMap.ElevatorMotorMaster;
  private final WPI_VictorSPX SpoolSlave = RobotMap.ElevatorMotorSlave;
  private final DoubleSolenoid mShifter = RobotMap.ElevatorShifter;
  private final DigitalInput mLeftLimit = RobotMap.ElevatorLeftLimit;
  private final DigitalInput mRightLimit = RobotMap.ElevatorRightLimit;

  private final Joystick CoOpStick = Robot.oi.getCoOperatorStick();
  private final ShuffleboardTab mElevatorTab = Shuffleboard.getTab("Elevator");

  private final double kPulseNumber = Constants.kPulsesPerRotation;
  private final double kMaxHeight = Constants.kMaxHieght;
  private final double kMidHeight = Constants.kMidHieght;
  private final double kMinHeight = Constants.kMinHieght;
  private final double kHomePositionInches = Constants.kHomePositionInches;
  private final double kSpoolDiam = Constants.kSpoolDiam;
  private final double PGain = Constants.kElevatorPGain;
  private final double IGain = Constants.kElevatorPGain;
  private final double DGain = Constants.kElevatorPGain;
  private final double kMaxSpeed = Constants.kElevatorMaxSpeed;
  private final double ElevatorSensitivity = Constants.kElevatorSensitivity;
  private final double kTicksPerInch = Constants.kElevatorTicksPerInch;

  private final Value kReverse = Value.kReverse;
  private final Value kForward = Value.kForward;
  private final Value kHighGear = Value.kReverse;
  private final Value kLowGear = Value.kForward;

  private double mError;
  private double mPerpotional;
  private double mDerivative;
  private double mIntegral = 0;
  private double mPerviousError;
  private double mEncoderTargetHieght;

  public Elevator() {
    setSubsystem("Elevator");
    addChild(SpoolMaster);
    addChild(SpoolSlave);
    addChild(mShifter);
    addChild(mLeftLimit);
    addChild(mRightLimit);
  }

  @Override
  public void initDefaultCommand() {
  }

  /**
   * Stops both the Master and Slave motors
   */
  public void StopMotors() {
    SpoolMaster.stopMotor();
    SpoolSlave.stopMotor();
  }

  /**
   * Sets the SpoolMasters's enocder position to zero
   */
  public void ZeroSensor() {
    SpoolMaster.setSelectedSensorPosition(0);
  }

  public double getTargetHeight(){
    return mEncoderTargetHieght;
  }

  public void setTargetHeight(double height){
    mEncoderTargetHieght = height;
  }

  /**
   * Calculates PID Speed to send to the master
   */
  public double PIDFinal() {
    mError = getTargetHeight() - CurrentHeight();
    mPerpotional = mError * PGain;
    mDerivative = (mError - mPerviousError) * DGain;
    mIntegral += (mError * .02);
    mPerviousError = mError;
    UpdateTelemetry();
    return (mPerpotional + mDerivative + (mIntegral * IGain));

  }

  /**
   * Trys to follow goal height, by sending PID speeds to motors
   */
  public void ChaseTarget() {
    SpoolMaster.set(ControlMode.PercentOutput, (1 * PIDFinal()));
  }

  /**
   * Changes gear when arm is going down Smith wanted but not currently used he he
   */
  public void ChaseTargetGearChanger() {
    if (PIDFinal() > 0) {
      mShifter.set(kForward);
      SpoolMaster.set(ControlMode.PercentOutput, (1 * PIDFinal()));
    } else {
      mShifter.set(kReverse);
      SpoolMaster.set(ControlMode.PercentOutput, (1 * PIDFinal()));
    }
  }

  /**
   * Gets Current Height in encoder counts
   */
  public double CurrentHeight() {
    return SpoolMaster.getSelectedSensorPosition();

    // * ((kSpoolDiam * Math.PI) / kPulseNumber);
    // return ElevatorEncoder.get()*((kSpoolDiam*Math.PI)/kPulseNumber);
  }

  /**
   * This is similar to `ChaseTarget()` but instead uses the TalonSRX built in PID
   * control loop.
   * 
   * @author Nate
   */
  public void setPosition(double targetInchesOffGround) {
    double positionFromHome = targetInchesOffGround - kHomePositionInches;
    double targetPositionRaw = positionFromHome * kTicksPerInch;
    if (getLimitsValue() == false) {
      SpoolMaster.set(ControlMode.Position, targetPositionRaw);
    } else {
      SpoolMaster.set(ControlMode.PercentOutput, 0);
    }
  }

  /**
   * This will return `true` if either the left or right limit switches return
   * true. Or simply are triggered.
   * 
   * @return
   */
  public boolean getLimitsValue() {
    return (mLeftLimit.get() || mRightLimit.get());
  }

  /**
   * @author Nate
   * @return Current Height in Inches
   */
  public double InchesOffGround() {
    double currentRawPosition = SpoolMaster.getSelectedSensorPosition();
    return (currentRawPosition / kTicksPerInch) + kHomePositionInches;
  }

  /**
   * Shifts the Gear to High
   * 
   * @author Cole
   * @author Tony
   */
  public void ShiftHighGear() {
    mShifter.set(kHighGear);
  }

  /**
   * Shifts the Gear to Low
   * 
   * @author Cole
   * @author Tony
   */
  public void ShiftLowGear() {
    mShifter.set(kLowGear);
  }

  /**
   * Returns a boolean and if True means that it is shifted
   * 
   * @author Cole
   * @author Tony
   */
  public boolean isHighGear() {
    if (mShifter.get() == kHighGear) {
      mElevatorTab.add("Elevator Shifter", getGear());
      // SmartDashboard.putString("Elevator Shifter", "High Gear");
      return true;
    } else {
      mElevatorTab.add("Elevator Shifter", getGear());
      // SmartDashboard.putString("Elevator Shifter", "Low Gear");
      return false;
    }
  }

  /**
   * Returns a string of which gear the Elevator is in
   * 
   * @return "High Gear" || "Low Gear"
   */
  public String getGear() {
    if (mShifter.get() == kHighGear) {
      return "High Gear";
    } else {
      return "Low Gear";
    }
  }

  /**
   * Updates the telemetry in the Elevator Subsystems to the Shuffleboard. Option
   * for the smartdashboard has been removed.
   */
  public void UpdateTelemetry() {
    // SmartDashboard.putNumber("ElevatorEncoder",
    // SpoolMaster.getSelectedSensorPosition());
    // SmartDashboard.putNumber("Perpotional", mPerpotional);
    // SmartDashboard.putNumber("Derivative", mDerivative);
    // SmartDashboard.putNumber("Integral", mIntegral);
    // SmartDashboard.updateValues();

    mElevatorTab.add("ElevatorEncoder", SpoolMaster.getSelectedSensorPosition());
    mElevatorTab.add("Left Limit", mLeftLimit.get());
    mElevatorTab.add("Right Limit", mRightLimit.get());
    mElevatorTab.add("Elevator Shifter", getGear());
    mElevatorTab.add("Perpotional", mPerpotional);
    mElevatorTab.add("Derivative", mDerivative);
    mElevatorTab.add("Integral", mIntegral);
    Shuffleboard.update();
  }
}
