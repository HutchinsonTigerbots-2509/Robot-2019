package frc.robot.subsystems; // package declaration

// imports

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
  // #region IMPORT VARIBLES
  
  // RobotMap Objects
  private final WPI_TalonSRX SpoolMaster = RobotMap.ElevatorMotorMaster;
  private final WPI_VictorSPX SpoolSlave = RobotMap.ElevatorMotorSlave;
  private final DoubleSolenoid mShifter = RobotMap.ElevatorShifter;
  private final DigitalInput mLeftLimit = RobotMap.ElevatorLeftLimit;
  private final DigitalInput mRightLimit = RobotMap.ElevatorRightLimit;

  // ShuffleBoard Tab
  private final ShuffleboardTab mElevatorTab = Shuffleboard.getTab("Elevator");
  
  // OI Joystick
  private final Joystick CoOpStick = Robot.oi.getCoOperatorStick();

  // Constants
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

  // Pneumatics Values
  private final Value kReverse = Value.kReverse;
  private final Value kForward = Value.kForward;
  private final Value kHighGear = Value.kReverse;
  private final Value kLowGear = Value.kForward;
  
  // #endregion IMPORT VARIBLES

  // #region PRIVATE VARIBLE DECLARATIONS
  
  // Private PID Varibles
  private double mError;
  private double mPerpotional;
  private double mDerivative;
  private double mIntegral = 0;
  private double mPerviousError;
  private double mEncoderTargetHieght;
  
  // #endregion PRIVATE VARIBLE DECLARATIONS

  // #endregion IMPORT VARIBLES

  /**
   * Adds children to the object so we can play with components
   * in test mode
   */
  public Elevator() {
    setSubsystem("Elevator");
    addChild(SpoolMaster);
    addChild(SpoolSlave);
    addChild(mShifter);
    addChild(mLeftLimit);
    addChild(mRightLimit);
  }

  // #region Lift Methods

  /**
   * Stops both the Master and Slave motors
   * 
   * @category Lift Methods
   */
  public void StopMotors() {
    SpoolMaster.stopMotor();
    SpoolSlave.stopMotor();
  }

  /**
   * Gets height we want the arm to move to in encoder counts
   * 
   * @category Lift Methods
   */
  public double TargetHeight() {
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

  /**
   * Calculates PID Speed to send to the master
   * 
   * @category Lift Methods
   */
  public double PIDFinal() {
    mError = TargetHeight() - CurrentHeight();
    mPerpotional = mError * PGain;
    mDerivative = (mError - mPerviousError) * DGain;
    mIntegral += (mError * .02);
    mPerviousError = mError;
    UpdateTelemetry();
    return (mPerpotional + mDerivative + (mIntegral * IGain));

  }

  /**
   * Trys to follow goal height, by sending PID speeds to motors
   * 
   * @category Lift Methods
   */
  public void ChaseTarget() {
    SpoolMaster.set(ControlMode.PercentOutput, (Math.min(1 * PIDFinal(), Constants.kMaxElevatorSpeed)));
  }

  /**
   * Changes gear when arm is going down Smith wanted but not currently used
   * 
   * @category Lift Methods
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

  // #endregion Lift Method
  // #region Shifter
  
  /**
   * Shifts the Gear to High
   * 
   * @category Shifter
   * @author Cole
   * @author Tony
   */
  public void ShiftHighGear() {
    mShifter.set(kHighGear);
  }

  /**
   * Shifts the Gear to Low
   * 
   * @category Shifter
   * @author Cole
   * @author Tony
   */
  public void ShiftLowGear() {
    mShifter.set(kLowGear);
  }

  /**
   * Returns a boolean and if True means that it is shifted
   * 
   * @category Shifter
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
   * @category Shifter
   * @return "High Gear" || "Low Gear"
   */
  public String getGear() {
    if (mShifter.get() == kHighGear) {
      return "High Gear";
    } else {
      return "Low Gear";
    }
  }

  // #endregion Shifter
  // #region Update Voids

  /**
   * Sets the SpoolMasters's enocder position to zero
  /**
   * Sets the SpoolMasters's enocder position to zero
   * 
   * @category Update Voids
   */
  public void ZeroSensor() {
    SpoolMaster.setSelectedSensorPosition(0);
  }

  /**
   * Updates the telemetry in the Elevator Subsystems to the Shuffleboard. Option
   * for the smartdashboard has been removed.
   * 
   * @category Update Voids
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

  // #endregion Update Voids
  // #region Elevator Getters and Setters

  public double getTargetHeight(){
    return mEncoderTargetHieght;
  }

  public void setTargetHeight(double height){
    mEncoderTargetHieght = height;
  }

  //#endregion Update Voids
  // #region Elevator Getters
  /**
   * Gets Current Height in encoder counts
   * 
   * @category Elevator Getters
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
   * This will return `true` if either the left or right limit switches return
   * true. Or simply are triggered.
   * 
   * @category Elevator Getters
   * @return Left Limit Or Right Limit
   */
  public boolean getLimitsValue() {
    return (mLeftLimit.get() || mRightLimit.get());
  }

  /**
   * * Will return the inches off the ground that the elevator is
   * 
   * @category Elevator Getters
   * @author Nate
   * @return Current Height in Inches
   */
  public double getInchesOffGround() {
    double currentRawPosition = SpoolMaster.getSelectedSensorPosition();
    return (currentRawPosition / kTicksPerInch) + kHomePositionInches;
  }

  // #endregion Elevator Getters
  
  @Override
  public void initDefaultCommand() {
  }
}