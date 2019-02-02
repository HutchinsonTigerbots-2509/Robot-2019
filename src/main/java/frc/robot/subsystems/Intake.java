package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * The intake subsystem is the subsystem where all of the
 */
public class Intake extends Subsystem {
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

  /**
   * Sets the Intake motors to take in.
   * 
   * @author Cole
   * @author Tony
   */
  public void MotorsIn() {
    mMotor.set(Constants.kMaxSpeed);
    UpdateTelemetry();
  }

  /**
   * Sets the Intake motors to reverse and push out
   * 
   * @author Cole
   * @author Tony
   */
  public void MotorsOut() {
    mMotor.set(Constants.kSlowSpeed);
    UpdateTelemetry();
  }

  /**
   * Stops the intake motors
   * 
   * @author Cole
   * @author Tony
   */
  public void MotorsStop() {
    mMotor.set(0);
    UpdateTelemetry();
  }

  /**
   * Moves the wrist of the intake arms up
   * 
   * @author Cole
   * @author Tony
   */
  public void Up() {
    mWrist.set(Value.kForward);
    UpdateTelemetry();
  }

  /**
   * Moves the Wrist down
   * 
   * @author Cole
   * @author Tony
   */
  public void Down() {
    mWrist.set(Value.kReverse);
    UpdateTelemetry();
  }

  /**
   * @author Cole
   * @author Tony Stops the up and down movement of the intake wrist
   */
  public void StopWrist() {
    mWrist.set(Value.kOff);
    UpdateTelemetry();
  }

  /**
   * Opens the Intake Arms
   * 
   * @author Cole
   * @author Tony
   */
  public void Open() {
    mGrip.set(Value.kForward);
    UpdateTelemetry();
  }

  /**
   * Closes the Intake Arms
   * 
   * @author Cole
   * @author Tony
   */
  public void Close() {
    mGrip.set(Value.kReverse);
    UpdateTelemetry();
  }

  /**
   * Stops the Intake Arms from opening or closing
   * 
   * @author Cole
   * @author Tony
   */
  public void Stop() {
    mGrip.set(Value.kOff);
    UpdateTelemetry();
  }

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
