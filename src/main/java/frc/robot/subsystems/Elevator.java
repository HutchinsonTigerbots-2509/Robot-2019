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
  private final WPI_TalonSRX Wrist = RobotMap.WristMotor;
  // private final WPI_VictorSPX SpoolSlave = RobotMap.ElevatorMotorSlave;
  private final DoubleSolenoid mShifter = RobotMap.ElevatorShifter;
  private final DigitalInput mTopLimit = RobotMap.ElevatorTopLimit;
  private final DigitalInput mBottomLimit = RobotMap.ElevatorBottomLimit;
  // ShuffleBoard Tab
  private final ShuffleboardTab mElevatorTab = Shuffleboard.getTab("Elevator");
  // Constants
  private final double kPulseNumber = Constants.kPulsesPerRotation;
  private final double kMaxHeight = Constants.kMaxHeight;
  private final double kMinHeight = Constants.kMinHeight;
  private final double kHomePositionInches = Constants.kHomePositionInches;
  private final double kSpoolDiam = Constants.kSpoolDiam;
  private final double PGain = Constants.kElevatorPGain;
  private final double IGain = Constants.kElevatorPGain;
  private final double DGain = Constants.kElevatorPGain;
  private final double kMaxSpeed = Constants.kElevatorMaxSpeed;
  private final double ElevatorSensitivity = Constants.kElevatorSensitivity;
  private final double kTicksPerInch = Constants.kElevatorHighGearTicksPerInch;
  // Pneumatics Values
  private final Value kReverse = Value.kReverse;
  private final Value kForward = Value.kForward;
  private final Value kHighGear = Value.kReverse;
  private final Value kLowGear = Value.kForward;
  // Private PID Varibles
  private double mError;
  private double mPerpotional;
  private double mDerivative;
  private double mIntegral = 0;
  private double mPerviousError;
  private double mEncoderTargetHieght;
  public String state;

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
    SpoolMaster.stopMotor();
    // SpoolSlave.stopMotor();
  }

  public void Up() {
    SpoolMaster.set(ControlMode.PercentOutput, 0.5);
  }

  public void Down() {
    SpoolMaster.set(ControlMode.PercentOutput, 0.5);
  }

  @Override
  public void initDefaultCommand() {
  }
}