package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *We use this to turn in autonomous. You set a degree you want to turn (- is left and + is right) 
 *in the () when you call this command. This will set robot to turn that specific distance
 *
 * @author Robot 2018
 */
public class DriveTurn extends Command {
	private Drivetrain sDrivetrain = Robot.sDrivetrain;
	public double mTarget = 0;
	private Boolean mTurnRight;
	private Boolean mTurnLeft;

    public DriveTurn(double targetAngle) {
    	requires(sDrivetrain);
    	mTarget = targetAngle;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println(DriverStation.getInstance().getMatchTime()+" - Turing to "+mTarget);
    	// drive.sensorReset();
    	Timer.delay(0.1);
//     	if(drive.getGyro().getAngle()>target) {
//     		turnRight = true;
//     		turnLeft = false;
//     		drive.getDrive().tankDrive(-Constants.kTurnSpeed,Constants.kTurnSpeed);//0.57575
//     	}else if(drive.getGyro().getAngle()<target){
//     		turnRight = false;
//     		turnLeft = true;
//     		drive.getDrive().tankDrive(Constants.kTurnSpeed, -Constants.kTurnSpeed);//0.57575
// //    	drive.getDrive().arcadeDrive(0, 0.6);
// //    	drive.getDrive().tankDrive(-0.6, 0.6);
//     	}else {
//     		end();
//     	}
      end();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	drive.getDrive().tankDrive(-0.6, 0.6);
    	if(mTurnRight) {
    		sDrivetrain.getDrive().tankDrive(Constants.kTurnSpeed,-Constants.kTurnSpeed);
    	}else if(mTurnLeft) {
    		sDrivetrain.getDrive().tankDrive(-Constants.kTurnSpeed, Constants.kTurnSpeed);
    	}else {
    		end();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// if(turnRight) {
    	// 	return drive.getGyro().getAngle()<(target);
    	// }else if(turnLeft) {
    	// 	return drive.getGyro().getAngle()>(target);
    	// }else {
    	// 	return false;
      // }
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