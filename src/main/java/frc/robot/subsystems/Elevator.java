package frc.robot.subsystems;

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
import edu.wpi.first.wpilibj.Joystick;

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
  private final WPI_TalonSRX mSpoolMotor = RobotMap.ElevatorMotorMaster;
  private final DoubleSolenoid mShifter = RobotMap.ElevatorShifter;
  private final DigitalInput mTopLimit = RobotMap.ElevatorTopLimit;
  private final DigitalInput mBottomLimit = RobotMap.ElevatorBottomLimit;
  // private final ShuffleboardTab mElevatorTab = Shuffleboard.getTab("Elevator");
  private Value kHighGear = Value.kReverse;
  private Value kLowGear = Value.kForward;
  private double mEncoderTargetTicks;
  public String state = "Hatch";

  /**
   * Adds children to the object so we can play with components in test mode
   */
  public Elevator() {
    setSubsystem("Elevator");
    addChild(mSpoolMotor);
    addChild(mShifter);
    addChild(mTopLimit);
  }
  
  @Override
  public void initDefaultCommand() {
  }

  /* Elevator Move Functions*/
  
  public void setPositionHighGear(double targetInchesOffGround) {
    double targetDistance = targetInchesOffGround - Constants.kHomePositionInches;
    double TargetTicks = targetDistance * 274.38312189; // 215.811165286 //274.38312189
    mEncoderTargetTicks = TargetTicks;
    // SmartDashboard.putNumber("TargetTicks", TargetTicks);
    // SmartDashboard.putNumber("power", mSpoolMotor.get());
    mSpoolMotor.set(ControlMode.Position, TargetTicks);
    CheckBottomSwitch();
  }

  public void setPositionLowGear(double targetInchesOffGround) {
    double targetDisplacement = targetInchesOffGround - Constants.kHomePositionInches;
    double TargetTicks = targetDisplacement * 860; //831.170774803
    mEncoderTargetTicks = TargetTicks;
    // SmartDashboard.putNumber("TargetTicks", TargetTicks);
    // SmartDashboard.putNumber("power", mSpoolMotor.get());
    mSpoolMotor.set(ControlMode.Position, TargetTicks);
    CheckBottomSwitch();
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

  /**
   * Sets the gear of the elevator
   * @author Cole & Tony
   */
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
    // // Subsystem Status
    // mElevatorTab.add("Encoder", mSpoolMotor.getSelectedSensorPosition());
    // mElevatorTab.add("Height (In)", CurrentTicks());
    // mElevatorTab.add("Top Limit", mTopLimit.get());
    // mElevatorTab.add("Bottom Limit", mBottomLimit.get());
    // mElevatorTab.add("Shifter", getGear());
    // mElevatorTab.add("Perpotional", Constants.kElevatorPGain);
    // mElevatorTab.add("Derivative", Constants.kElevatorIGain);
    // mElevatorTab.add("Integral", Constants.kElevatorDGain);
    // // mElevatorTab.add("State", state);
    // // Subsystem Objects
    // mElevatorTab.add(mSpoolMotor);
    // mElevatorTab.add(mTopLimit);
    // mElevatorTab.add(mBottomLimit);
    // mElevatorTab.add(mShifter);
    // // Subsystem Commands
    // mElevatorTab.add("Elevator Hatch High", new ElevatorMoveHighGear(Constants.kHatchHigh));
    // mElevatorTab.add("Elevator Hatch Mid", new ElevatorMoveHighGear(Constants.kHatchMid));
    // mElevatorTab.add("Elevator Hatch Low", new ElevatorMoveHighGear(Constants.kHatchLow));
    // mElevatorTab.add("Elevator Ball High", new ElevatorMoveHighGear(Constants.kBallHigh));
    // mElevatorTab.add("Elevator Ball Mid", new ElevatorMoveHighGear(Constants.kBallMid));
    // mElevatorTab.add("Elevator Ball Low", new ElevatorMoveHighGear(Constants.kBallLow));
    // mElevatorTab.add("Elevator HAB", new ElevatorMoveLowGear(Constants.kHABHeight));
    // mElevatorTab.add("Elevator 12", new ElevatorMoveHighGear(12));
    // mElevatorTab.add("Elevator Shift", new ElevatorShift());
    // mElevatorTab.add("Elevator Hieght", new HeightToggle());
    // mElevatorTab.add("Elevator Zero", new ZeroElevator());
    //
    Shuffleboard.update();
  }

  /* Current Status */

  public double CurrentTicks() {
    return mSpoolMotor.getSelectedSensorPosition();
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
    mSpoolMotor.setSelectedSensorPosition(0);
  }

  /**
   * Resets the SpoolMaster Encoder position if the Reverse
   * limit switch is triggered
   */
  public void CheckBottomSwitch(){
    if(mSpoolMotor.getSensorCollection().isRevLimitSwitchClosed()){
      ZeroSensor();
    }
  }
  
  /* Motor */
  /**
   * Stops both the motors
   */
  public void StopMotors() {
    mSpoolMotor.stopMotor();
  }

  public void ElevatorUp(){
    mSpoolMotor.set(ControlMode.PercentOutput, Constants.kElevatorMinSpeedUp);
    CheckBottomSwitch();
  }

  public void ElevatorDown(){
    mSpoolMotor.set(ControlMode.PercentOutput, Constants.kElevatorMinSpeedDown);
    CheckBottomSwitch();
  }

  /**
   * Requires Button 10 of the Joystick to be pressed to activate the movements
   * @param stick
   */
  public void ManualMove(Joystick stick){
    if(stick.getRawAxis(1) < -0.2 && stick.getRawButton(10)){
      mSpoolMotor.set(ControlMode.PercentOutput, -stick.getRawAxis(1));
    }else if(stick.getRawAxis(1) > 0.2 && stick.getRawButton(10)){
      mSpoolMotor.set(ControlMode.PercentOutput, -stick.getRawAxis(1));
    }else{
      mSpoolMotor.set(ControlMode.PercentOutput, 0);
    }
    CheckBottomSwitch();
  }
  public void ManualMoveMark2(Joystick stick){
    if(stick.getRawAxis(1) < -0.2){
      mSpoolMotor.set(ControlMode.PercentOutput, 1);
    }else if(stick.getRawAxis(1) > 0.2){
      mSpoolMotor.set(ControlMode.PercentOutput, -1);
    }else{
      mSpoolMotor.set(ControlMode.PercentOutput, 0);
    }
    CheckBottomSwitch();
  }

  public WPI_TalonSRX getMotor(){
    return mSpoolMotor;
  }
}