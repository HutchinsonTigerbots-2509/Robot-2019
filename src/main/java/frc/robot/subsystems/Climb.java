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
  private final DoubleSolenoid HigherLeftPiston = RobotMap.ClimbLeftHigherPiston;
  private final DoubleSolenoid LowerLeftPiston = RobotMap.ClimbLeftLowerPiston;
  private final DoubleSolenoid HigherRightPiston = RobotMap.ClimbRightHigherPiston;
  private final DoubleSolenoid LowerRightPiston = RobotMap.ClimbRightLowerPiston;

  // Shuffleboard Tab
  private final ShuffleboardTab mClimb = Shuffleboard.getTab("Climb");
  
  // #endregion SUBSYSTEM IMPORT VARIBLE DECLARATIONS

  /**
   * Constructor that adds children to the object so
   * we can play with components in test mode
   */
  public Climb(){
    setSubsystem("Climb");
    addChild(HigherLeftPiston);
    addChild(LowerLeftPiston);
    addChild(HigherRightPiston);
    addChild(LowerRightPiston);
    addChild(Motor);
  }
  // #region Climbing Voids
  
  /**
   * Extends the Higher Pistons on both sides
   * 
   * @author CRahne
   */
  public void ExtendHigherPistons(){
    HigherLeftPiston.set(Value.kForward);
    HigherRightPiston.set(Value.kForward);
  }

  /**
   * Extends the Lower Pistons on both sides
   * 
   * @author CRahne
   */
  public void ExtendLowerPistons(){
    LowerLeftPiston.set(Value.kForward);
    LowerRightPiston.set(Value.kForward);
  }
  
  /**
   * Retracts the Higher Pistons on both sides
   * 
   * @author CRahne
   */
  public void RetractHigherPistons(){
    HigherLeftPiston.set(Value.kReverse);
    HigherRightPiston.set(Value.kReverse);
  }

  /**
   * Retracts the Lower Pistons on both sides
   * 
   * @author CRahne
   */
  public void RetractLowerPistons(){
    LowerLeftPiston.set(Value.kReverse);
    LowerRightPiston.set(Value.kReverse);
  }

  /**
   * Stops all Pistons
   * 
   * @author CRahne
   */
  public void StopPistons(){
    // Left
    HigherLeftPiston.set(Value.kOff);
    LowerLeftPiston.set(Value.kOff);
    // Right
    HigherRightPiston.set(Value.kOff);
    LowerRightPiston.set(Value.kOff);
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
   * Will return the higher piston on the left side
   * 
   * @return High Left Piston
   * @author CRahne
   */
  public DoubleSolenoid getLeftHighPiston() {
    return HigherLeftPiston;
  }

  /**
   * Will return the lower piston on the left side
   * 
   * @return Low Left Piston
   * @author CRahne
   */
  public DoubleSolenoid getLeftLowPiston() {
    return LowerLeftPiston;
  }

  /**
   * Will return the higher piston on the right side
   * 
   * @return High Right Piston
   * @author CRahne
   */
  public DoubleSolenoid getRightHighPiston() {
    return HigherRightPiston;
  }

  /**
   * Will return the lower piston on the right side
   * 
   * @return Right Low Piston
   * @author CRahne
   */
  public DoubleSolenoid getRightLowPiston() {
    return getRightLowPiston();
  }

  /**
   * Will return whether the high pistons are extended
   * 
   * @return if(kForward) -> "Extended", if(kReverse) -> "Retracted", else "Null"
   * @author CRahne
   */
  public String getHigherPistonsStatus() {
    if (HigherLeftPiston.get() == Value.kForward && HigherRightPiston.get() == Value.kForward){
      return "Extended";
    } else if (HigherLeftPiston.get() == Value.kReverse && HigherRightPiston.get() == Value.kReverse){
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
    if (LowerLeftPiston.get() == Value.kForward && LowerRightPiston.get() == Value.kForward){
      return "Extended";
    } else if (LowerLeftPiston.get() == Value.kReverse && LowerRightPiston.get() == Value.kReverse){
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
