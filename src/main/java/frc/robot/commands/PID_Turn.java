package frc.robot.commands; // package declaration

// imports

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Drivetrain;

/**
 * Command for a PID Turning Command
 */
public class PID_Turn extends Command {
  // Import Varible Declarations
  private Drivetrain sDT = Robot.sDrivetrain;
  private DifferentialDrive mDrive = sDT.getDrive();
  private AHRS mGyro = RobotMap.Drivetrain_Gyro;
  private double error = 0.0, pre_error = 0.0, targ_angle = 0.0;

  /**
   * PID_Turn Constructor
   * 
   * @param target angle
   */
  public PID_Turn(double p_targ_angle) {
    requires(sDT);
    targ_angle =  p_targ_angle;
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    error = targ_angle - mGyro.getAngle();
    mDrive.arcadeDrive(0.0, sDT.getPIDTurnValue(error, pre_error, targ_angle));
    pre_error = error;
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    error = targ_angle - mGyro.getAngle();
    mDrive.arcadeDrive(0.0, sDT.getPIDTurnValue(error, pre_error, targ_angle));
    pre_error = error;
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
  }
}
