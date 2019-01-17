package frc.robot.subsystems; 

import edu.wpi.first.wpilibj.command.PIDSubsystem; 
import frc.robot.Constants; 

/**
 * Add your docs here.
 */
public class Elevator extends PIDSubsystem {
  private double kP = Constants.kElevatorP; 
  private double kI = Constants.kElevatorI; 
  private double kD = Constants.kElevatorD; 

  /**
     * Add your docs here.
     */
  public Elevator() {
  // Intert a subsystem name and PID values here
  super("Elevator", Constants.kElevatorP, Constants.kElevatorI, Constants.kElevatorD); 
  // Use these to get going:
  // setSetpoint() - Sets where the PID controller should move the system
  // to
  // enable() - Enables the PID controller.
  }

  @Override
    public void initDefaultCommand() {
  // Set the default command for a subsystem here.
  // setDefaultCommand(new MySpecialCommand());
  }

  @Override
    protected double returnPIDInput() {
  // Return your input value for the PID loop
  // e.g. a sensor, like a potentiometer:
  // yourPot.getAverageVoltage() / kYourMaxVoltage;
  return 0.0; 
  }

  @Override
    protected void usePIDOutput(double output) {
  // Use output to drive your system, like a motor
  // e.g. yourMotor.set(output);
  }
}
