package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *We call this during autonomous so we can drive backward a set distance.
 *
 * @deprecated
 * @author 2018 code
 */
public class DriveBackwards extends Command {
	private Drivetrain drive = Robot.sDT;
	public double target = 0;
	private double wheelDiameter = 6;
    public DriveBackwards(double targetDistance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drive);
    	target = (targetDistance/(wheelDiameter*Math.PI))*3*360;
    	
    }
    public DriveBackwards() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println(DriverStation.getInstance().getMatchTime()+" - Driving Backwards");
    	// drive.sensorReset();
    	Timer.delay(0.1);
    	// drive.getDrive().arcadeDrive(-0.6, drive.getGyro().getAngle()*(0.1));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// drive.getDrive().arcadeDrive(-0.6, drive.getGyro().getAngle()*(0.1));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // return (-1*((drive.getRightEncoder().get()+drive.getLeftEncoder().get())/2))>=target;
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.getDrive().tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}