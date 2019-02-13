package frc.robot.subsystems; // package declaration

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.VictorSP;

// imports

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  private final DoubleSolenoid mWristPiston = RobotMap.IntakeWristPiston;
  private final DoubleSolenoid mHatchOutPiston = RobotMap.IntakeHatchPiston; // Works as two pistons
  private final ShuffleboardTab mIntakeTab = Shuffleboard.getTab("Intake Tab");

  //#endregion SUBSYSTEM VARIBLE DECLARATIONS

  public Intake(){
    setSubsystem("Intake");
    addChild(mMotor);
    addChild(mWristPiston);
    addChild(mHatchOutPiston);
  }

  // #region Hatch

  /**
   * Will intake a hatch after making sure it doesn't have a 
   * ball in the intake
   * 
   * @category Hatch
   * @author CRahne
   */
  public void IntakeHatch() {
    // if(IntakeHas == IntakeHasThis.Cargo) {
      // SmartDashboard.putString("Intake Is: ", "Releasing Cargo");
      // setGripPiston(Value.kForward);
      // MotorReverse();
      // Timer.delay(10);
      // setGripPiston(Value.kReverse);
      // setWristPiston(Value.kReverse); // IDK
    // }
    SmartDashboard.putString("Intake Is: ", "Intaking Hatch");
    setHatchPistons(Value.kReverse);
    setWristPiston(Value.kForward); // Still IDK
  }

  /**
   * Will eject a hatch after making sure it has a hatch
   * 
   * @category Hatch
   * @author CRahne
   */
  public void EjectHatch() {
    SmartDashboard.putString("Intake Is: ", "Ejecting Hatch");
    setWristPiston(Value.kForward);
    setHatchPistons(Value.kForward);
    setHatchPistons(Value.kReverse);
  }
  /**
   * Will set the gripper piston on the intake subsystem
   * to the value passed in the parameter
   * 
   * <li> <b>To Eject the Pistons: </b> Set the value to Value.kForward
   * <li> <b>To Retract the Pistons: </b> Set the value to Value.kReverse
   * <li> <b>To turn off Pistons: </b> Set the value to Value.kOff
   * 
   * @category Ball
   * @author CRahne
   * @param Value that the piston will be set to
   */
  public void setHatchPistons(Value value) {
    mHatchOutPiston.set(value);
  }
  
  /**
   * Will set the gripper piston on the intake subsystem
   * to the value passed in the parameter
   * 
   * <li> <b>To Lift the Wrist Mechanism: </b> Set the value to Value.kForward
   * <li> <b>To Lower the Wrist Mechanism: </b> Set the value to Value.kReverse
   * <li> <b>To turn off Piston: </b> Set the value to Value.kOff
   * 
   * @category Ball
   * @author CRahne
   * @param value - the value that the piston will be set to
   */
  public void setWristPiston(Value value) {
    mWristPiston.set(value);
  } 

  // #endregion Hatch
  // #region Ball

  /**
   * Intakes a Ball after checking if it might have a hatch
   * 
   * @category Ball
   * @author CRahne
   */
  public void IntakeBall() {
    // if(IntakeHas == IntakeHasThis.Hatch) {
      // SmartDashboard.putString("Intake Is: ", "Releasing the Hatch");
      // setHatchPistons(Value.kForward);
      // setHatchPistons(Value.kReverse);
      // setWristPiston(Value.kReverse); // IS THIS RIGHT?
    // }
    SmartDashboard.putString("Intake Is: ", "Getting A Ball");
    setWristPiston(Value.kReverse); // IS THIS RIGHT?
    MotorIn();
  }

  /**
   * Ejects the ball after making sure the intake subsystem has a ball
   * 
   * @category Ball
   * @author CRahne
   */
  public void EjectBall() {
    SmartDashboard.putString("Intake Is: ", "Ejecting A Ball");
    MotorReverse();
  }

  /**
   * Will stop everything in the ball system
   * 
   * @category Ball
   * @author CRahne
   */
  public void StopBallIntake() {
    MotorStop();
    setWristPiston(Value.kReverse); // Or kForward depending on how it works
    setWristPiston(Value.kOff);
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
  public void MotorReverse() {
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
   * Resets the ball system.
   * 
   * @category Ball
   * @author CRahne
   */
  public void resetBallMechanism() {
    MotorStop();
    setWristPiston(Value.kReverse);
  }

  // #endregion Ball 
  // #region General
  
  /**
   * Will end all components of the subsystem
   * 
   * @category General
   * @author CRahne
   */
  public void EndAll() {
    MotorStop();
    setHatchPistons(Value.kOff);
    setWristPiston(Value.kOff);
  }

  /**
   * If the EndAll() void doesn't work
   * 
   * @category General
   * @author CRahne
   */
  public void RetractAllPistons() {
    setHatchPistons(Value.kReverse);
    MotorStop();
  }

  /**
   * Will update data on the shuffleboard tab for this class
   */
  public void UpdateTelemetry() {
    mIntakeTab.add("Motor Speed", mMotor.get());
    mIntakeTab.add("Hatch Pistons Status", getHatchPistonStatus());
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
  public DoubleSolenoid getHatchPistons() {
    return mHatchOutPiston;
  }

  /** 
   * Will get the Hatch Pistons Status
   * <p> `kReverse` = The pistons are retracted and not in use
   * <p> `kForward` = The pistons are extended and ejecting a hatch panel
   * <p> `kOff` = Pistons are off and not doing anything
   * 
   * @category Intake Getters
   * @author CRahne
   * @return Hatch Pistons Status
   */
  public Value getHatchPistonStatus() {
    return mHatchOutPiston.get();
  }

  /**
   * Will return the status of the grip piston in a string
   * <p> `kReverse` = Piston is set to Reverse and not collecting a hatch panel </p>
   * <p> `kForward` = Piston is set to Forward and collecting a hatch panel </p>
   * <p> `kOff` = Piston is set to Off / default value </p>
   * 
   * @category Intake Getters
   * @return Grip Piston Status
   */
  public Value getWristStatus(){
    return mWristPiston.get();
  }

  // #endregion Intake Getters
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}