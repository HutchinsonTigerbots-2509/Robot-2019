package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * The climbing subsystem is the subsystem where we climb up
 * onto the habitat ramps. We do this be using a two stage
 * piston system on both sides of the robot, with the lower ones
 * firing first, followed by the second stage
 *
 * <h3> JavaDoc Categories for Functions: </h3>
 * <li> + Climbing Voids - All of the voids related to climbing
 * <li> + General - Voids that interact with all of the subsystem components
 * <li> + Climb Getters - Will return objects and data
 *
 * @author CRahne and Cole
 */
public class Climb extends Subsystem {
  //#region SUBSYSTEM IMPORT VARIBLE DECLARATIONS
  
  // Motors
  private final WPI_TalonSRX Motor = RobotMap.ClimbMotor;
  
  // Pistons
  private final DoubleSolenoid HighPistonSystem = RobotMap.ClimbHighPistons; // 2 Pistons, one on each side
  private final DoubleSolenoid LowPistonSystem = RobotMap.ClimbLowPistons;   // Same for the low ones

  // Shuffleboard Tab
  private final ShuffleboardTab mClimb = Shuffleboard.getTab("Climb");
  
  // #endregion SUBSYSTEM IMPORT VARIBLE DECLARATIONS

  /**
   * Constructor that adds children to the object so
   * we can play with components in test mode
   */
  public Climb(){
    setSubsystem("Climb");
    addChild(HighPistonSystem);
    addChild(LowPistonSystem);
    addChild(Motor);
  }
  // #region Climbing Voids
  
  /**
   * Extends the Higher Pistons on both sides
   * 
   * @author CRahne
   */
  public void ExtendHigherPistons(){
    HighPistonSystem.set(Value.kForward);
  }

  /**
   * Extends the Lower Pistons on both sides
   * 
   * @author CRahne
   */
  public void ExtendLowerPistons(){
    LowPistonSystem.set(Value.kForward);
  }
  
  /**
   * Retracts the Higher Pistons on both sides
   * 
   * @author CRahne
   */
  public void RetractHigherPistons(){
    HighPistonSystem.set(Value.kReverse);
  }

  /**
   * Retracts the Lower Pistons on both sides
   * 
   * @author CRahne
   */
  public void RetractLowerPistons(){
    LowPistonSystem.set(Value.kReverse);
  }

  /**
   * Stops all Pistons
   * 
   * @author CRahne
   */
  public void StopPistons(){
    // Will set both sides' pistons to off
    HighPistonSystem.set(Value.kOff);
    LowPistonSystem.set(Value.kOff);
  }
  
  // #endregion Climbing Voids

  // #region General

  /**
   * Updates the data for shuffleboard
   */
  public void UpdateTelemetry(){
    mClimb.add("Higher Pistons", getHigherPistonsStatus());
    mClimb.add("Lower Pistons", getLowerPistonsStatus());
    mClimb.add("Motor Speed", Motor.get());
  }

  // #endregion General
  // #region Climb Getters

  /**
   * Will return the higher pistons
   * 
   * @return High Pistons
   * @author CRahne
   */
  public DoubleSolenoid getHighPistons() {
    return HighPistonSystem;
  }

  /**
   * Will return the lower pistons
   * 
   * @return Low Left Piston
   * @author CRahne
   */
  public DoubleSolenoid getLowPistons() {
    return LowPistonSystem;
  }

  /**
   * Will return whether the high pistons are extended
   * 
   * @return if(kForward) -> "Extended", if(kReverse) -> "Retracted", else "Null"
   * @author CRahne
   */
  public String getHigherPistonsStatus() {
    if (HighPistonSystem.get() == Value.kForward) {
      return "Extended";
    } else if (HighPistonSystem.get() == Value.kReverse) {
      return "Retracted";
    } else {
      return "Null";
    }
  }

  /**
   * Will return whether the low pistons are Extended or Retracted
   * 
   * @return if(kForward) -> "Extended", if(kReverse) -> "Retracted", else "Null"
   * @author CRahne
   */
  public String getLowerPistonsStatus() {
    if (LowPistonSystem.get() == Value.kForward) {
      return "Extended";
    } else if (LowPistonSystem.get() == Value.kReverse) {
      return "Retracted";
    } else {
      return "Null";
    }
  }

  /**
   * Will say if the high pistons are extended or not
   * 
   * @return if pistons are extened or not
   * @author CRahne
   */
  public Boolean areHighPistonsExtended() {
    String state = getHigherPistonsStatus();
    if(state == "Extended") {
      SmartDashboard.putString("High Climb Piston State", state);
      return true;
    }
    return false;
  }
  /**
   * Will say if the low pistons are extended or not
   * 
   * @return if pistons are extened or not
   * @author CRahne
   */
  public Boolean areLowPistonsExtended() {
    String state = getLowerPistonsStatus();
    if(state == "Extended") {
      SmartDashboard.putString("Low Climb Piston State", state);
      return true;
    }
    return false;
  }

  // #endregion Climb Getters

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
