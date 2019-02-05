package frc.robot.subsystems; // package declaration

// imports

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * The intake subsystem is the main scoring subsystem of the
 * robot. It can pick up balls and hatches, and then release
 * them into a scoring zone, such as the cargo ship or rocket
 * 
 * <h3> JavaDoc Categories for Functions: </h3>
 * <li> + Hatch - Will pertain to intaking the hatch
 * <li> + Ball - Will pertain to intaking the ball
 * <li> + General - General (Misc.) Voids that don't fit anywhere else
 * <li> + Intake Getters - Will return a value or an object
 * 
 * @author CRahne, Tony, and Cole G
 */
public class Intake extends Subsystem {
  // #region SUBSYSTEM VARIBLE DECLARATIONS
  private final VictorSP mMotor = RobotMap.IntakeRightMotor;
  private final DoubleSolenoid mGrip = RobotMap.IntakeOpenPiston;
  private final DoubleSolenoid mWristPiston = RobotMap.IntakeWristPiston;
  private final ShuffleboardTab mIntakeTab = Shuffleboard.getTab("Intake Tab");
  //#endregion SUBSYSTEM VARIBLE DECLARATIONS

  // #region Hatch
  
  /**
   * Moves the wrist of the intake arms up
   * 
   * @author Cole
   * @author Tony
   */
  public void Up() {
    mWristPiston.set(Value.kForward);
  }

  /**
   * Moves the Wrist down
   * 
   * @author Cole
   * @author Tony
   */
  public void Down() {
    mWristPiston.set(Value.kReverse);
  }

  /**
   * @author Cole
   * @author Tony Stops the up and down movement of the intake wrist
   */
  public void StopWrist() {
    mWristPiston.set(Value.kOff);
  }

  // #endregion Hatch
  // #region Ball
  
  /**
   * Sets the Intake motors to take in.
   * 
   * @author Cole
   * @author Tony
   */
  public void MotorsIn() {
    mMotor.set(Constants.kMaxSpeed);
  }

  /**
   * Sets the Intake motors to reverse and push out
   * 
   * @author Cole
   * @author Tony
   */
  public void MotorsOut() {
    mMotor.set(Constants.kSlowSpeed);
  }

  /**
   * Stops the intake motors
   * 
   * @author Cole
   * @author Tony
   */
  public void MotorsStop() {
    mMotor.set(0);
  }

  /**
   * Opens the Intake Arms
   * 
   * @author Cole
   * @author Tony
   */
  public void Open() {
    mGrip.set(Value.kForward);
  }

  /**
   * Closes the Intake Arms
   * 
   * @author Cole
   * @author Tony
   */
  public void Close() {
    mGrip.set(Value.kReverse);
  }

  /**
   * Stops the Intake Arms from opening or closing
   * 
   * @author Cole
   * @author Tony
   */
  public void Stop() {
    mGrip.set(Value.kOff);
  }

  // #endregion Ball 
  // #region General
  /**
   * Will update data on the shuffleboard tab for this class
   * 
   * @category General
   */
  public void UpdateTelemetry() {
    mIntakeTab.add("Motor Speed", mMotor.get());
    mIntakeTab.add("Grip Status", getGripStatus());
    mIntakeTab.add("Wrist Status", getWristStatus());
    Shuffleboard.update();
  }

  //#endregion General
  // #region Intake Getters
  
  /**
   * Will return the intake motor
   * 
   * @category Intake Getters
   * @author CRahne
   * @return Intake Motor
   */
  public VictorSP getIntakeMotor() {
    return mMotor;
  }
  
  /**
   * Will return the opening piston that opens
   * and closes the arms of the intake
   * 
   * @category Intake Getters
   * @author CRahne
   * @return Open Piston
   */
  public DoubleSolenoid getOpenerPiston() {
    return mGrip;
  }

  /**
   * Will return the wrist piston that moves
   * the intake subsystem up and down (for hatch)
   * 
   * @category Intake Getters
   * @author CRahne
   * @return Wrist Piston
   */
  public DoubleSolenoid getWristPiston() {
    return mWristPiston;
  }

  public String getGripStatus(){
    if (mGrip.get() == Value.kForward){
      return "Open";
    }
    else if (mGrip.get() == Value.kForward){
      return "Close";
    }
    else{
      return "Null";
    }
  }

  public String getWristStatus(){
    if (mWristPiston.get() == Value.kForward){
      return "Up";
    }
    else if (mWristPiston.get() == Value.kForward){
      return "Down";
    }
    else{
      return "Null";
    }
  }

  // #endregion Intake Getters
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}