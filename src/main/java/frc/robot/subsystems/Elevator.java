package frc.robot.subsystems; // package declaration

// imports

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.elevator.ElevatorMoveHighGear;
import frc.robot.commands.elevator.ElevatorMoveLowGear;
import frc.robot.commands.elevator.ElevatorShift;
import frc.robot.commands.elevator.HeightToggle;
import frc.robot.commands.elevator.ZeroElevator;

/**
 * The Elevator Subsystem is where code that uses the lift mechanism is stored
 * and can be accessed and used throughout the project
 * 
 * <h3>JavaDoc Categories for Functions:</h3>
 * <li>+ Lift Methods - Will use the lift mechanism
 * <li>+ Shifter - Will have something to do with the shifter for the spool
 * masters
 * <li>+ Update Voids - Updates something, like data or sensors
 * <li>+ Elevator Getters - Will return a value or an object
 * 
 * @author DJ, Tony, Cole G, and Nate
 */
public class Elevator extends Subsystem {
  private final WPI_TalonSRX SpoolMaster = RobotMap.ElevatorMotorMaster;
  private final DoubleSolenoid mShifter = RobotMap.ElevatorShifter;
  private final DigitalInput mTopLimit = RobotMap.ElevatorTopLimit;
  private final DigitalInput mBottomLimit = RobotMap.ElevatorBottomLimit;
  private final ShuffleboardTab mElevatorTab = Shuffleboard.getTab("Elevator");
  public String state;
  private Value kHighGear = Value.kForward;
  private Value kLowGear = Value.kReverse;
  private double mEncoderTargetTicks;

  /**
   * Adds children to the object so we can play with components in test mode
   */
  public Elevator() {
    setSubsystem("Elevator");
    addChild(SpoolMaster);
    addChild(mShifter);
    addChild(mTopLimit);
    //addChild(mBottomLimit);
  }

  /* Elevator Move Functions*/
  
  public void setPositionHighGear(double targetInchesOffGround) {
    double targetDistance = targetInchesOffGround - Constants.kHomePositionInches;
    double TargetTicks = targetDistance * 274.38312189; // 215.811165286 //274.38312189
    mEncoderTargetTicks = TargetTicks;
    SmartDashboard.putNumber("TargetTicks", TargetTicks);
    SmartDashboard.putNumber("power", SpoolMaster.get());
    SpoolMaster.set(ControlMode.Position, TargetTicks);
  }

  public void setPositionLowGear(double targetInchesOffGround) {
    double targetDistance = targetInchesOffGround - Constants.kHomePositionInches;
    double TargetTicks = targetDistance * 831.170774803; // 215.811165286 //274.38312189
    mEncoderTargetTicks = TargetTicks;
    SmartDashboard.putNumber("TargetTicks", TargetTicks);
    SmartDashboard.putNumber("power", SpoolMaster.get());
    SpoolMaster.set(ControlMode.Position, TargetTicks);
  }
  
  /* Elevator Shifter Functions */
  /**
   * Toggles the Gear Shift
   * @author Cole & Tony
   */
  public void setHighGear() {
    if (isHighGear()) {
      mShifter.set(kLowGear);
    } else {
      mShifter.set(kHighGear);
    }
  }

  public void setHighGear(boolean mHighGear) {
    if (mHighGear) {
      mShifter.set(kHighGear);
    } else {
      mShifter.set(kLowGear);
    }
  }

  /**
   * Updates the telemetry in the Elevator Subsystems to the Shuffleboard. Option
   * for the smartdashboard has been removed.
   */
  public void UpdateTelemetry() {
    // Subsystem Status
    mElevatorTab.add("Encoder", SpoolMaster.getSelectedSensorPosition());
    mElevatorTab.add("Height (In)", CurrentTicks());
    mElevatorTab.add("Top Limit", mTopLimit.get());
    mElevatorTab.add("Bottom Limit", mBottomLimit.get());
    mElevatorTab.add("Shifter", getGear());
    mElevatorTab.add("Perpotional", Constants.kElevatorPGain);
    mElevatorTab.add("Derivative", Constants.kElevatorIGain);
    mElevatorTab.add("Integral", Constants.kElevatorDGain);
    mElevatorTab.add("State", state);
    // Subsystem Objects
    mElevatorTab.add(SpoolMaster);
    mElevatorTab.add(mTopLimit);
    mElevatorTab.add(mBottomLimit);
    mElevatorTab.add(mShifter);
    // Subsystem Commands
    mElevatorTab.add("Elevator Hatch High", new ElevatorMoveHighGear(Constants.kHatchHigh));
    mElevatorTab.add("Elevator Hatch Mid", new ElevatorMoveHighGear(Constants.kHatchMid));
    mElevatorTab.add("Elevator Hatch Low", new ElevatorMoveHighGear(Constants.kHatchLow));
    mElevatorTab.add("Elevator Ball High", new ElevatorMoveHighGear(Constants.kBallHigh));
    mElevatorTab.add("Elevator Ball Mid", new ElevatorMoveHighGear(Constants.kBallMid));
    mElevatorTab.add("Elevator Ball Low", new ElevatorMoveHighGear(Constants.kBallLow));
    mElevatorTab.add("Elevator HAB", new ElevatorMoveLowGear(Constants.kHABHeight));
    mElevatorTab.add("Elevator 12", new ElevatorMoveHighGear(12));
    mElevatorTab.add("Elevator Shift", new ElevatorShift());
    mElevatorTab.add("Elevator Hieght", new HeightToggle());
    mElevatorTab.add("Elevator Zero", new ZeroElevator());
    //
    Shuffleboard.update();
  }

  @Override
  public void initDefaultCommand() {
  }

  /* Current Status */

  public double CurrentTicks() {
    return SpoolMaster.getSelectedSensorPosition();
  }
  public double getTargetTicks() {
    return mEncoderTargetTicks;
  }
  /**
   * Returns a string of which gear the Elevator is in
   * 
   * @return "High Gear" or "Low Gear"
   */
  public String getGear() {
    if (mShifter.get() == kHighGear) return "High Gear";
    else return "Low Gear";
  }
  /**
   * Returns a boolean and if True means that it is shifted
   * 
   * @author Cole & Tony
   */
  public boolean isHighGear() {
    if (mShifter.get() == kHighGear) return true;
    else return false;
  }

  /* Sensors */
  /**
   * Sets the SpoolMasters's enocder position to zero
   */
  public void ZeroSensor() {
    SpoolMaster.setSelectedSensorPosition(0);
  }
  
  /* Motor */
  /**
   * Stops both the Master and Slave motors
   */
  public void StopMotors() {
    SpoolMaster.stopMotor();
  }

  public void ElevatorUp(){
    SpoolMaster.set(ControlMode.PercentOutput, Constants.kElevatorMinSpeedUp);
  }

  public void ElevatorDown(){
    SpoolMaster.set(ControlMode.PercentOutput, Constants.kElevatorMinSpeedDown);
  }
  public WPI_TalonSRX getMotor(){return SpoolMaster;}
}