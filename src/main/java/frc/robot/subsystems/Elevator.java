package frc.robot.subsystems; // package declaration

// imports

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.ElevatorMoveHighGear;
import frc.robot.commands.ElevatorMoveLowGear;
import frc.robot.commands.ElevatorShift;
import frc.robot.commands.HeightToggle;
import frc.robot.Robot;
import frc.robot.commands.ManualElevatorMove;
import frc.robot.commands.ZeroElevator;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import frc.robot.Robot;

/**
 * The Elevator Subsystem is where code that uses the lift mechanism
 * is stored and can be accessed and used throughout the project
 * 
 * <h3> JavaDoc Categories for Functions: </h3>
 * <li> + Lift Methods - Will use the lift mechanism
 * <li> + Shifter - Will have something to do with the shifter for the spool masters
 * <li> + Update Voids - Updates something, like data or sensors
 * <li> + Elevator Getters - Will return a value or an object
 * 
 * @author DJ, Tony, Cole G, and Nate
 */
public class Elevator extends Subsystem {
  // RobotMap Objects
  private final WPI_TalonSRX SpoolMaster = RobotMap.ElevatorMotorMaster;
  private final DoubleSolenoid mShifter = RobotMap.ElevatorShifter;
  private final DigitalInput mTopLimit = RobotMap.ElevatorTopLimit;
  private final DigitalInput mBottomLimit = RobotMap.ElevatorBottomLimit;
  private final ShuffleboardTab mElevatorTab = Shuffleboard.getTab("Elevator");
  public String state;
  private Value kHighGear = Value.kForward;

  /**
   * Adds children to the object so we can play with components
   * in test mode
   */
  public Elevator() {
    setSubsystem("Elevator");
    addChild(SpoolMaster);
    // addChild(SpoolSlave);
    addChild(mShifter);
    addChild(mTopLimit);
    addChild(mBottomLimit);
  }

  /**
   * Stops both the Master and Slave motors
   */
  public void StopMotors() {
    // SpoolMaster.set(ControlMode.PercentOutput, 0.0);
    SpoolMaster.set(0);
    SpoolMaster.stopMotor();
    // SpoolSlave.stopMotor();
  }

  /**
   * Gets height we want the arm to move to in encoder counts
   */

  /**
   * Calculates PID Speed to send to the master
   */

  /**
   * Trys to follow goal height, by sending PID speeds to motors
   */

  /**
   * Changes gear when arm is going down Smith wanted but not currently used
   */

  /**
   * Shifts the Gear to Low
   * 
   * @author Cole
   * @author Tony
   */
  public void setHighGear() {
    if (isHighGear()) {
      mShifter.set(Value.kForward);
    } else {
      mShifter.set(Value.kReverse);
    }
  }

  /**
   * Returns a boolean and if True means that it is shifted
   * @author Cole
   * @author Tony
   */
  public boolean isHighGear() {
    if (mShifter.get() == kHighGear) {
      // mElevatorTab.add("Elevator Shifter", getGear());
      // SmartDashboard.putString("Elevator Shifter", "High Gear");
      return true;
    } else {
      // mElevatorTab.add("Elevator Shifter", getGear());
      // SmartDashboard.putString("Elevator Shifter", "Low Gear");
      return false;
    }
  }

  /**
   * Returns a string of which gear the Elevator is in
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
   * Sets the SpoolMasters's enocder position to zero
   */
  public void ZeroSensor() {
    SpoolMaster.setSelectedSensorPosition(0);
  }

  /**
   * Updates the telemetry in the Elevator Subsystems to the Shuffleboard. Option
   * for the smartdashboard has been removed.
   */
  public void UpdateTelemetry() {
    // Subsystem Status
    mElevatorTab.add("Encoder", SpoolMaster.getSelectedSensorPosition());
    mElevatorTab.add("Height (In)", CurrentHeight());
    mElevatorTab.add("Top Limit", mTopLimit.get());
    mElevatorTab.add("Bottom Limit", mBottomLimit.get());
    mElevatorTab.add("Shifter", getGear());
    mElevatorTab.add("Perpotional", mPerpotional);
    mElevatorTab.add("Derivative", mDerivative);
    mElevatorTab.add("Integral", mIntegral);
    mElevatorTab.add("State", state);
    // Subsystem Objects
    mElevatorTab.add(SpoolMaster);
    mElevatorTab.add(mTopLimit);
    mElevatorTab.add(mBottomLimit);
    mElevatorTab.add(mShifter);
    //Subsystem Commands
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

  public void Down() {
    SpoolMaster.set(ControlMode.PercentOutput, 0.5);
  }

  @Override
  public void initDefaultCommand() {
  }
}