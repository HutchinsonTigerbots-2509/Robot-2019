package frc.robot.subsystems; // package declaration

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.VictorSP;

// imports

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * The intake subsystem is the main scoring subsystem of the robot. It can pick
 * up balls and hatches, and then release them into a scoring zone, such as the
 * cargo ship or rocket
 * 
 * @author CRahne, Tony, and Cole G
 */
public class Intake extends Subsystem {
  private final VictorSP mMotor = RobotMap.IntakeMotor;
  private final WPI_TalonSRX mWrist = RobotMap.WristMotor;
  private final ShuffleboardTab mIntakeTab = Shuffleboard.getTab("Intake Tab");
  private final int kWristTicksPerDegree = Constants.kWristTicksPerDegree;

  public Intake() {
    setSubsystem("Intake");
    addChild(mMotor);
    addChild(mWrist);
    // addChild(mWristPiston);
    // addChild(mHatchOutPiston);
  }

  /**
   * Will start the hatch pickup process
   * 
   * @author CRahne
   */
  // public void HatchStart() { // 2/2/2019
  //   MotorStop();
    // CloseArms();
    // WristPistonDown();
  // }

  /**
   * Will end the hatch pick up process
   * 
   * @author CRahne
   */
  // public void HatchEnd() { // 2/2/2019
  // WristPistonUp();
  // RetractHatchOutPistons();
  // }

  /**
   * Will detach the hatch from the subsystem for a score
   * 
   * @author CRahne
   */
  // public void IntakeHatch() {
    // if(IntakeHas == IntakeHasThis.Cargo) {
    // SmartDashboard.putString("Intake Is: ", "Releasing Cargo");
    // setGripPiston(Value.kForward);
    // MotorReverse();
    // Timer.delay(10);
    // setGripPiston(Value.kReverse);
    // setWristPiston(Value.kReverse); // IDK
    // }
    // setHatchPistons(Value.kReverse);
    // setWristPiston(Value.kForward); // Still IDK
    // }

    /**
     * Will eject a hatch after making sure it has a hatch
     * 
     * @author CRahne
     */
    // public void EjectHatch() {
    // setWristPiston(Value.kForward);
    // setHatchPistons(Value.kForward);
    // setHatchPistons(Value.kReverse);
  // }

  /**
   * Retract the `hatch out` pistons
   * 
   * @author CRahne
   */
  // public void RetractHatchOutPistons() {
  // mHatchOutPiston.set(Value.kReverse);
  // }

  /**
   * Will extend the Hatch Out Pistons
   * 
   * @author CRahne
   */
  // public void ExtendHatchOutPistons() {
  // mHatchOutPiston.set(Value.kForward);
  // }

  /**
   * Will stop the hatch Pistons
   * 
   * @author CRahne
   * @param Value that the piston will be set to
   */
  // public void setHatchPistons(Value value) {
  // mHatchOutPiston.set(value);
  // }

  /**
   * Will set the gripper piston on the intake subsystem to the value passed in
   * the parameter
   * 
   * <li><b>To Lift the Wrist Mechanism: </b> Set the value to Value.kForward
   * <li><b>To Lower the Wrist Mechanism: </b> Set the value to Value.kReverse
   * <li><b>To turn off Piston: </b> Set the value to Value.kOff / public void
   * StopHatchOutPistons() { mHatchOutPiston.set(Value.kOff); }
   * 
   * /** Sets the Intake motors to take in.
   * 
   * @author CRahne
   */
  // public void WristPistonUp() {
  // mWristPiston.set(Value.kForward);
  // }

  /**
   * Sets the Intake motors to reverse and push out
   * 
   * @author CRahne
   * @param value - the value that the piston will be set to
   */
  // public void setWristPiston(Value value) {
  // mWristPiston.set(value);
  // }

  // #endregion Hatch
  // #region Ball

  /**
   * Intakes a Ball after checking if it might have a hatch
   * 
   * @author CRahne
   */
  // public void IntakeBall() {
    // if(IntakeHas == IntakeHasThis.Hatch) {
    // SmartDashboard.putString("Intake Is: ", "Releasing the Hatch");
    // setHatchPistons(Value.kForward);
    // setHatchPistons(Value.kReverse);
    // setWristPiston(Value.kReverse); // IS THIS RIGHT?
    // }
    // setWristPiston(Value.kReverse); // IS THIS RIGHT?
  // }

  /**
   * Stops the intake wrist
   * 
   * @author Cole
   * @author Tony
   */
  // public void StopWristPiston() {
  // mWristPiston.set(Value.kOff);
  // }
  /**
   * moves the intake wrist up
   * 
   * @author Tony
   */
  public void Up() {
    mWrist.set(ControlMode.PercentOutput,0.9);
  }
  /**
   * moves the intake wrist down
   * @author Tony
   */
  public void Down(){
    mWrist.set(ControlMode.PercentOutput,-0.9);
  }
  /**
   * stops the movement of the intake wrist
   * @author Tony
   */
  public void StopWrist(){
    mWrist.set(ControlMode.PercentOutput,0);
  }
  public void WristMove(double angle){
    double rawTargetAngle = angle* kWristTicksPerDegree;
    mWrist.set(ControlMode.Position,rawTargetAngle);
  }
  /**
   * Will take a ball in
   * @author CRahne
   */
  public void In() { // 2/2/2019
    // OpenArms();
    MotorIn();
  }

  /**
   * Ejects the ball after making sure the intake subsystem has a ball
   * @author CRahne
   */
  public void EjectBall() {
    MotorReverse();
  }
  /**
   * Will shoot a ball out
   * @author CRahne
   */
  public void Close() { // 2/2/2019
    MotorStop();
    // CloseArms();
  }

  /**
   * Will stop everything in the ball system
   * @author CRahne
   */
  public void StopBallIntake() {
    MotorStop();
  //  setWristPiston(Value.kReverse); // Or kForward depending on how it works
  //  setWristPiston(Value.kOff);
    // StopArmPiston();
    // StopWristPiston();
  }

  /**
   * Sets the Intake motors to take in.
   * @author Cole
   * @author Tony
   */
  public void MotorIn() {
    mMotor.set(Constants.kMaxSpeed);
  }

  /**
   * Will shoot the ball out
   * @author CRahne
   */
  public void MotorReverse() {
    mMotor.set(Constants.kReverseFastSpeed);
  }

  /**
   * Stops the intake motors
   * @author Tony
   */
  public void MotorStop() {
    mMotor.set(0);
  }
  
  /**
   * Will end all components of the subsystem
   * @author CRahne
   */
  public void EndAll() {
    MotorStop();
  }

  /** 
   * Will update data on the shuffleboard tab for this class
   */
  public void UpdateTelemetry() {
    mIntakeTab.add("Motor Speed", mMotor.get());
    mIntakeTab.add("Wrist Position", mWrist.getSelectedSensorPosition());
    mIntakeTab.add(mMotor);
    mIntakeTab.add(mWrist);
    Shuffleboard.update();
  }
  
  /**
   * Will return the intake motor
   * @return Intake Motor
   */
  public VictorSP getIntakeMotor() {
    return mMotor;
  }
  
  /**
   * Retruns the wrist motor;
   * @return Wrist MotorS
   */
  public WPI_TalonSRX getWristMotor(){
    return mWrist;
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}