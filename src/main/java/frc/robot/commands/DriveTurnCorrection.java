/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *Corrects the robot if we under turn or over turn.
 *
 * @deprecated
 * @author 2018 code
 */
public class DriveTurnCorrection extends Command {
	private Drivetrain drive = Robot.sDT;
	public double target = 0;
	private double turnSpeed = 0.7;
	private Boolean turnRight;
	private Boolean turnLeft;
    public DriveTurnCorrection(double targetAngle) {
    	requires(drive);
    	target = targetAngle;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println(DriverStation.getInstance().getMatchTime()+" - Turing to "+target);
//     	if(drive.getGyro().getAngle()>target) {
//     		turnRight = true;
//     		turnLeft = false;
//     		drive.getDrive().tankDrive(-0.5,0.5);
//     	}else if(drive.getGyro().getAngle()<target){
//     		turnRight = false;
//     		turnLeft = true;
//     		drive.getDrive().tankDrive(0.5, -0.5);
// //    	drive.getDrive().arcadeDrive(0, 0.5);
// //    	drive.getDrive().tankDrive(-0.5, 0.5);
//     	}else {
    		end();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	drive.getDrive().tankDrive(-0.5, 0.5);
    	if(turnRight) {
    		drive.getDrive().tankDrive(0.5,-0.5);
    	}else if(turnLeft) {
    		drive.getDrive().tankDrive(-0.5, 0.5);
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