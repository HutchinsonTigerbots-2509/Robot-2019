package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * The intake subsystem is the subsystem where
 * all of the 
 */
public class Intake extends Subsystem {
  /* Subsystem Varible Declarations */
  private final DoubleSolenoid mLeftPush = RobotMap.IntakeLeftPiston;
  private final DoubleSolenoid mWrist = RobotMap.IntakeWristPiston;
  private final DoubleSolenoid mOpen = RobotMap.OpenIntakePiston;
  private final SpeedController mIntakeMotors = RobotMap.IntakeMotors;
  private final double kMaxSpeed =  Constants.kMaxSpeed;
  
  /**
   * Controls the intake motors and allows you to take in
   * 
   * @author Cole and Tony
   */
  public void In(){
    mIntakeMotors.set(kMaxSpeed);      
  }
  /**
   * Controls the intake motors and allows you to reverse intake motors
   * 
   * @author Cole and Tony
   */
  public void Out(){
    mIntakeMotors.set(kMaxSpeed);
  }
  /**
   * Controls the intake motors and stops them
   * 
   * @author Cole and Tony
   */
  public void motorsStop(){
    mIntakeMotors.stopMotor();
  }
/**
   * Moves the intake arm up
   * 
   * @author Cole and Tony
   */
  public void Up(){
    mWrist.set(Value.kForward);
  }
  /**
   * Moves the intake arm down
   * 
   * @author Cole and Tony
   */  
  public void Down(){
    mWrist.set(Value.kReverse);
  }
  /**
   * Stops the wrist movement
   * 
   * @author Cole and Tony
   */
  public void StopWrist(){
    mWrist.set(Value.kOff);
  } 
/**
   * Controls the intake arms and allows you to open them up
   * 
   * @author Cole and Tony
   */
  public void Open(){
    mOpen.set(Value.kForward);
  }
  /**
   * Controls the intake arms and allows you to close them
   * 
   * @author Cole and Tony
   */
  public void Close(){
    mOpen.set(Value.kReverse);
  }
  /**
   * Controls the intake arms and stops the open or close
   * 
   * @author Cole and Tony
   */
  public void Stop(){
    mOpen.set(Value.kOff);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
