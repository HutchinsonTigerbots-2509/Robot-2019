/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {

  private final TalonSRX SpoolMaster = RobotMap.ElevatorMotorMaster;
  private final VictorSPX SpoolSlave = RobotMap.ElevatorMotorSlave;
  private final DoubleSolenoid mShifter = RobotMap.ElevatorShifter;

  private final Joystick CoOpStick = Robot.oi.getCoOperatorStick();

  private final double kPulseNumber = Constants.kPulsesPerRotation;
  private final double kMaxHeight = Constants.kMaxHieght;
  private final double kMidHeight = Constants.kMidHieght;
  private final double kMinHeight = Constants.kMinHieght;
  private final double kSpoolDiam = Constants.kSpoolDiam;
  private final double PGain = Constants.kElevatorPGain;
  private final double IGain = Constants.kElevatorPGain;
  private final double DGain = Constants.kElevatorPGain;
  private final double kMaxSpeed = Constants.kElevatorMaxSpeed;
  private final double ElevatorSensitivity = Constants.kElevatorSensitivity;

  private double mError;
  private double mPerpotional;
  private double mDerivative;
  private double mIntegral = 0;
  private double mPerviousError;
  private double mEncoderTargetHieght;

  public Elevator(){
    setSubsystem("Elevator");
    addChild(SpoolMaster);
    addChild(SpoolSlave);
  }

  @Override
  public void initDefaultCommand() {
  }

  public double TargetHeight() { // Gets Goal height
    if (CoOpStick.getRawAxis(1) != 0) {
      mEncoderTargetHieght = mEncoderTargetHieght + ((ElevatorSensitivity) * (CoOpStick.getRawAxis(1) * -1));
    } else if (CoOpStick.getRawButton(4)) {
      mEncoderTargetHieght = (kMaxHeight * ((kSpoolDiam * Math.PI) / kPulseNumber));// Max
    } else if (CoOpStick.getRawButton(2)) {
      mEncoderTargetHieght = (kMidHeight * ((kSpoolDiam * Math.PI) / kPulseNumber));// Mid
    } else if (CoOpStick.getRawButton(1)) {
      mEncoderTargetHieght = (kMinHeight * ((kSpoolDiam * Math.PI) / kPulseNumber));// Min
    }
    return mEncoderTargetHieght;
  }

  public double PIDFinal() { // Calculates PID Speed

    mError = TargetHeight() - CurrentHeight();
    mPerpotional = mError * PGain;
    mDerivative = (mError - mPerviousError) * DGain;
    mIntegral += (mError * .02);
    mPerviousError = mError;

    SmartDashboard.putNumber("ElevatorEncoder", SpoolMaster.getSelectedSensorPosition());
    SmartDashboard.putNumber("Perpotional", mPerpotional);
    SmartDashboard.putNumber("Derivative", mDerivative);
    SmartDashboard.putNumber("Integral", mIntegral);

    return (mPerpotional + mDerivative + (mIntegral * IGain));

  }

  public void ChaseTarget() { // Trys to follow goal height, by sending PID speeds to motors
    SpoolMaster.set(ControlMode.PercentOutput, (1 * PIDFinal()));
  }

  public double CurrentHeight() { // Gets Current Height
    return SpoolMaster.getSelectedSensorPosition() * ((kSpoolDiam * Math.PI) / kPulseNumber);
    // return ElevatorEncoder.get()*((kSpoolDiam*Math.PI)/kPulseNumber);
  }

  /**
   * Shifts the Gear to High
   * @author Cole
   * @author Tony
   */
  public void ShiftHighGear() {
    mShifter.set(Value.kForward);
  }

  /**
   * Shifts the Gear to Low
   * @author Cole
   * @author Tony
   */
  public void ShiftLowGear() {
    mShifter.set(Value.kReverse);
  }

  /**
   * Returns a boolean and if True means that it is shifted
   * @author Cole
   * @author Tony
   */
  public boolean isShifted() {
    if (mShifter.get() == Value.kReverse){
      SmartDashboard.putString("isShifted", "fastGear");
    	return true;
    } else {
      SmartDashboard.putString("isShifted", "slowGear");
      return false;
    }
  }


  // public Encoder getEncoder() {
  // return ElevatorEncoder;
  // }
}
