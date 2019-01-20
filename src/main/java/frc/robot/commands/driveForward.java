package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Constants;
import frc.robot.Robot;

/**
 * We use this command in autonomous to drive forward a certain distance. Enter
 * the distance you want the robot to go in () when we call this command.
 * 
 * @author 2018 Code
 */

public class DriveForward extends Command {
  private Drivetrain sDrivetrain = Robot.sDrivetrain;
  public double mTarget = 0;
  
  public DriveForward(double targetDistance) {
      // Use requires() here to declare subsystem dependencies
      // eg. requires(chassis);
    requires(sDrivetrain);
    mTarget = (targetDistance/(Constants.kWheelDiameter*Math.PI))*3*360;
    
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    System.out.println(DriverStation.getInstance().getMatchTime()+" - Driving Forwards");
    Timer.delay(0.1);
    // drive.getDrive().arcadeDrive(0.6, drive.getGyro().getAngle()*(0.1));
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    // drive.getDrive().arcadeDrive(0.6, drive.getGyro().getAngle()*(0.1));
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    // return (drive.getRightEncoder().get()>=target||drive.getLeftEncoder().get()>=target);
    return false;
  }

  // Called once after isFinished returns true
  protected void end() {
    sDrivetrain.getDrive().tankDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
    end();
  }
}