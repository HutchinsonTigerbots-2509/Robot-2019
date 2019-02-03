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
<<<<<<< HEAD
  /* Subsystem Varible Declarations */
  private final VictorSP mMotor = RobotMap.IntakeMotor;
  private final DoubleSolenoid mGrip = RobotMap.IntakeOpenPiston;
  private final DoubleSolenoid mWrist = RobotMap.IntakeWristPiston;
  private final ShuffleboardTab mIntakeTab = Shuffleboard.getTab("Intake");

  public Intake(){
    setSubsystem("Intake");
    addChild(mMotor);
    addChild(mGrip);
    addChild(mWrist);
  }
=======
  // #region SUBSYSTEM VARIBLE DECLARATIONS
  private final VictorSP mMotor = RobotMap.IntakeRightMotor;
  private final DoubleSolenoid mOpenPiston = RobotMap.IntakeOpenPiston;
  private final DoubleSolenoid mWristPiston = RobotMap.IntakeWristPiston;
  private final DoubleSolenoid mHatchOutPistonL = RobotMap.IntakeHatchOutPistonLeft; // 2/2/2019
  private final DoubleSolenoid mHatchOutPistonR = RobotMap.IntakeHatchOutPistonRight; // 2/2/2019
  //#endregion SUBSYSTEM VARIBLE DECLARATIONS
>>>>>>> Drivetrain

  // #region Hatch
  /**
   * Will start the hatch pickup process
   * 
   * @category Hatch
   * @author CRahne
   */
<<<<<<< HEAD
  public void MotorsIn() {
    mMotor.set(Constants.kMaxSpeed);
    UpdateTelemetry();
=======
  public void HatchStart() { // 2/2/2019
    MotorStop();
    CloseArms();
    WristPistonDown();
>>>>>>> Drivetrain
  }

  /**
   * Will end the hatch pick up process
   * 
   * @category Hatch
   * @author CRahne
   */
<<<<<<< HEAD
  public void MotorsOut() {
    mMotor.set(Constants.kSlowSpeed);
    UpdateTelemetry();
=======
  public void HatchEnd() { // 2/2/2019
    WristPistonUp();
    RetractHatchOutPistons();
>>>>>>> Drivetrain
  }

  /**
   * Will detach the hatch from the subsystem
   * for a score
   * 
   * @category Hatch
   * @author CRahne
   */
<<<<<<< HEAD
  public void MotorsStop() {
    mMotor.set(0);
    UpdateTelemetry();
=======
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
    mHatchOutPistonR.set(Value.kReverse);
    mHatchOutPistonL.set(Value.kReverse);
  }

  /**
   * Will extend the Hatch Out Pistons
   * 
   * @category Hatch
   * @author CRahne
   */
  public void ExtendHatchOutPistons() { // 2/2/2019
    mHatchOutPistonL.set(Value.kForward);
    mHatchOutPistonR.set(Value.kForward);
  }

  /**
   * Will stop the hatch Pistons
   * 
   * @category Hatch
   * @author CRahne
   */
  public void StopHatchOutPistons() { // 2/2/2019
    mHatchOutPistonL.set(Value.kOff);
    mHatchOutPistonR.set(Value.kOff);
>>>>>>> Drivetrain
  }

  /**
   * Moves the wrist of the intake arms up
   * 
   * @category Hatch
   * @author CRahne
   */
<<<<<<< HEAD
  public void Up() {
    mWrist.set(Value.kForward);
    UpdateTelemetry();
=======
  public void WristPistonUp() {
    mWristPiston.set(Value.kForward);
>>>>>>> Drivetrain
  }

  /**
   * Moves the Wrist down
   * 
   * @category Hatch
   * @author CRahne
   */
<<<<<<< HEAD
  public void Down() {
    mWrist.set(Value.kReverse);
    UpdateTelemetry();
=======
  public void WristPistonDown() {
    mWristPiston.set(Value.kReverse);
>>>>>>> Drivetrain
  }

  /**
   * Stops the intake wrist
   * 
   * @category Hatch
   * @author Cole
   * @author Tony
   */
<<<<<<< HEAD
  public void StopWrist() {
    mWrist.set(Value.kOff);
    UpdateTelemetry();
=======
  public void StopWristPiston() {
    mWristPiston.set(Value.kOff);
>>>>>>> Drivetrain
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
<<<<<<< HEAD
  public void Open() {
    mGrip.set(Value.kForward);
    UpdateTelemetry();
=======
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
>>>>>>> Drivetrain
  }

  /**
   * Stops the intake motors
   * 
   * @category Ball
   * @author Cole
   * @author Tony
   */
<<<<<<< HEAD
  public void Close() {
    mGrip.set(Value.kReverse);
    UpdateTelemetry();
=======
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
    mOpenPiston.set(Value.kForward);
  }

  /**
   * Closes the Intake Arms
   * 
   * @category Ball
   * @author CRahne
   */
  public void CloseArms() {
    mOpenPiston.set(Value.kReverse);
>>>>>>> Drivetrain
  }

  /**
   * Stops the Intake Arms from opening or closing
   * 
   * @category Ball
   * @author CRahne
   */
  public void StopArmPiston() {
    mOpenPiston.set(Value.kOff);
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
    return mOpenPiston;
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
   * Will return the Left Side Hatch Ejection
   * Piston
   * 
   * @category Intake Getters
   * @author CRahne
   * @return Left Side Hatch Eject Piston
   */
  public DoubleSolenoid getHatchOutLeftPiston() {
    return mHatchOutPistonL;
  }

  /**
   * Will return the right side Hatch ejection
   * Piston
   * 
   * @category Intake Getters
   * @author CRahne
   * @return Right Side Hatch Eject Piston
   */
<<<<<<< HEAD
  public void Stop() {
    mGrip.set(Value.kOff);
    UpdateTelemetry();
=======
  public DoubleSolenoid getHatchOutRightPiston() {
    return mHatchOutPistonR;
>>>>>>> Drivetrain
  }

  // #endregion Intake Getters
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
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
    if (mWrist.get() == Value.kForward){
      return "Up";
    }
    else if (mWrist.get() == Value.kForward){
      return "Down";
    }
    else{
      return "Null";
    }
  }

  public void UpdateTelemetry() {
    mIntakeTab.add("Motor Speed", mMotor.get());
    mIntakeTab.add("Grip Status", getGripStatus());
    mIntakeTab.add("Wrist Status", getWristStatus());
    Shuffleboard.update();
  }
}
