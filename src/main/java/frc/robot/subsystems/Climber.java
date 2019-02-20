package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
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
public class Climber extends Subsystem {
  private final VictorSP Motor = RobotMap.ClimbMotor;
  private final DoubleSolenoid HighPistonSystem = RobotMap.ClimbUpperPiston; // 2 Pistons, one on each side
  private final DoubleSolenoid LowPistonSystem = RobotMap.ClimbLowerPiston;   // Same for the low ones
  private final ShuffleboardTab mClimbTab = Shuffleboard.getTab("Climb");
  
  /**
   * Constructor that adds children to the object so
   * we can play with components in test mode
   */
  public Climber(){
    setSubsystem("Climb");
    addChild(HighPistonSystem);
    addChild(LowPistonSystem);
    addChild(Motor);
  }
  
  // #region Climbing Voids
  
  /**
   * Extends the Low Pistons (stage 1) on both sides
   */
  public void StageOneStart() {
    LowPistonSystem.set(Value.kForward);
  }

  /**
   * Extends the High Pistons (stage 2) on both sides
   * 
   * @author CRahne
   */
  public void StageTwoStart() {
    HighPistonSystem.set(Value.kForward);
  }
  
  /**
   * Retracts the Higher Pistons (stage 2) on both sides
   * 
   * @author CRahne
   */
  public void RetractStageTwo(){
    HighPistonSystem.set(Value.kReverse);
  }

  /**
   * Retracts the Lower Pistons (stage 1) on both sides
   * 
   * @author CRahne
   */
  public void RetractStageOne(){
    LowPistonSystem.set(Value.kReverse);
  }

  public void setMotorSpeed(double speed){
    if(Math.abs(speed)>1){
      speed /= Math.abs(speed);
    }
    Motor.set(speed);
  }
  /**
   * Stops all Pistons
   * 
   * @author CRahne
   */
  public void PistonsOff(){
    // Will set both sides' pistons to off
    HighPistonSystem.set(Value.kOff);
    LowPistonSystem.set(Value.kOff);
  }

  //#endregion Climbing Voids
  // #region General

  /**
   * Updates the data for shuffleboard
   */
  public void UpdateTelemetry(){
    mClimbTab.add("Higher Pistons", getHigherPistonsStatus());
    mClimbTab.add("Lower Pistons", getLowerPistonsStatus());
    mClimbTab.add("Motor Speed", Motor.get());
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
