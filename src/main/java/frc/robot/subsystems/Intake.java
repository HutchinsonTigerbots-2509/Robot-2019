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
  private final DoubleSolenoid mGripPiston = RobotMap.IntakeGripPiston;
  private final DoubleSolenoid mWristPiston = RobotMap.IntakeWristPiston;
  private final DoubleSolenoid mHatchOutPiston = RobotMap.IntakeHatchPiston; // Works as two pistons
  private final ShuffleboardTab mIntakeTab = Shuffleboard.getTab("Intake Tab");
  //#endregion SUBSYSTEM VARIBLE DECLARATIONS

  public Intake(){
    setSubsystem("Intake");
    addChild(mMotor);
    addChild(mGripPiston);
    addChild(mWristPiston);
    addChild(mHatchOutPiston);
  }

  // #region Hatch
  /**
   * Will start the hatch pickup process
   * 
   * @category Hatch
   * @author CRahne
   */
  public void HatchStart() { // 2/2/2019
    MotorStop();
    CloseArms();
    WristPistonDown();
  }

  /**
   * Will end the hatch pick up process
   * 
   * @category Hatch
   * @author CRahne
   */
  public void HatchEnd() { // 2/2/2019
    WristPistonUp();
    RetractHatchOutPistons();
  }

  /**
   * Will detach the hatch from the subsystem
   * for a score
   * 
   * @category Hatch
   * @author CRahne
   */
  public void HatchEject() { // 2/2/2019
    ExtendHatchOutPistons();
  }

  /**
   * Retract the `hatch out` pistons
   * 
   * @category Hatch
   * @author CRahne
   */
  public void RetractHatchOutPistons() { // 2/2/2019
    mHatchOutPiston.set(Value.kReverse);
  }

  /**
   * Will extend the Hatch Out Pistons
   * 
   * @category Hatch
   * @author CRahne
   */
  public void ExtendHatchOutPistons() { // 2/2/2019
    mHatchOutPiston.set(Value.kForward);
  }

  /**
   * Will stop the hatch Pistons
   * 
   * @category Hatch
   * @author CRahne
   */
  public void StopHatchOutPistons() { // 2/2/2019
    mHatchOutPiston.set(Value.kOff);
  }

  /**
   * Moves the wrist of the intake arms up
   * 
   * @category Hatch
   * @author CRahne
   */
  public void WristPistonUp() {
    mWristPiston.set(Value.kForward);
  }

  /**
   * Moves the Wrist down
   * 
   * @category Hatch
   * @author CRahne
   */
  public void WristPistonDown() {
    mWristPiston.set(Value.kReverse);
  }

  /**
   * Stops the intake wrist
   * 
   * @category Hatch
   * @author Cole
   * @author Tony
   */
  public void StopWristPiston() {
    mWristPiston.set(Value.kOff);
  }

  // #endregion Hatch
  // #region Ball
  
  /**
   * Will take a ball in
   * 
   * @category Ball
   * @author CRahne
   */
  public void In() { // 2/2/2019
    OpenArms();
    MotorIn();
  }

  /**
   * Will shoot a ball out
   * 
   * @category Ball
   * @author CRahne
   */
  public void Close() { // 2/2/2019
    MotorStop();
    CloseArms();
  }

  /**
   * Will stop everything in the ball system
   * 
   * @category Ball
   * @author CRahne
   */
  public void StopAllBallSystem() { // 2/2/2019
    MotorStop();
    StopArmPiston();
    StopWristPiston();
  }

  /**
   * Sets the Intake motors to take in.
   * 
   * @category Ball
   * @author Cole
   * @author Tony
   */
  public void MotorIn() {
    mMotor.set(Constants.kMaxSpeed);
  }

  /**
   * Will shoot the ball out
   * 
   * @category Ball
   * @author CRahne
   */
  public void MotorOut() {
    mMotor.set(Constants.kReverseFastSpeed);
  }

  /**
   * Stops the intake motors
   * 
   * @category Ball
   * @author Cole
   * @author Tony
   */
  public void MotorStop() {
    mMotor.set(0);
  }

  /**
   * Opens the Intake Arms
   * 
   * @category Ball
   * @author CRahne
   */
  public void OpenArms() {
    mGripPiston.set(Value.kForward);
  }

  /**
   * Closes the Intake Arms
   * 
   * @category Ball
   * @author CRahne
   */
  public void CloseArms() {
    mGripPiston.set(Value.kReverse);
  }

  /**
   * Stops the Intake Arms from opening or closing
   * 
   * @category Ball
   * @author CRahne
   */
  public void StopArmPiston() {
    mGripPiston.set(Value.kOff);
  }

  // #endregion Ball 
  // #region General
  
  /**
   * Will end all components of the subsystem
   * 
   * @category General
   * @author CRahne
   */
  public void EndAll() { // 2/2/2019
    MotorStop();
    RetractHatchOutPistons();
    CloseArms();
    WristPistonUp();
  }
  /**
   * Will update data on the shuffleboard tab for this class
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
   * Will return the gripper piston that opens
   * and closes the arms of the intake
   * 
   * @category Intake Getters
   * @author CRahne
   * @return Grip Piston
   */
  public DoubleSolenoid getGripperPiston() {
    return mGripPiston;
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

  /**
   * Will return the Hatch Ejection Piston
   * 
   * @category Intake Getters
   * @author CRahne
   * @return Hatch Eject Piston
   */
  public DoubleSolenoid getHatchOutLeftPiston() {
    return mHatchOutPiston;
  }

  /**
   * Will return the status of the grip piston in a string
   * <p> `Open` = Piston is set to Forward </p>
   * <p> `Close` = Piston is set to Reverse </p>
   * <p> `Null` = Piston is set to Off / default value </p>
   * 
   * @category Intake Getters
   * @return Grip Piston Status
   */
  public String getGripStatus(){
    if (mGripPiston.get() == Value.kForward){
      return "Open";
    }
    else if (mGripPiston.get() == Value.kForward){
      return "Close";
    }
    else{
      return "Null";
    }
  }

  /**
   * Will return the status of the grip piston in a string
   * <p> `Up` = Piston is set to Reverse and not collecting a hatch panel </p>
   * <p> `Down` = Piston is set to Forward and collecting a hatch panel </p>
   * <p> `Null` = Piston is set to Off / default value </p>
   * 
   * @category Intake Getters
   * @return Grip Piston Status
   */
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
