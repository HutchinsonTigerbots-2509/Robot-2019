package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * The intake subsystem is the subsystem where all of the
 */
public class Intake extends Subsystem {
  /* Subsystem Varible Declarations */
  private final VictorSP mMotor = RobotMap.IntakeRightMotor;
  private final VictorSP mLeftMotor = RobotMap.IntakeLeftMotor;
  private final DoubleSolenoid mGrip = RobotMap.IntakeOpenPiston;
  private final DoubleSolenoid mWristPiston = RobotMap.IntakeWristPiston;

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

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
