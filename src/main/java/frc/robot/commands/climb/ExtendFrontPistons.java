/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Climber;

public class ExtendFrontPistons extends Command {

private static Climber sClimb = Robot.sClimb;
private static AHRS gyro = RobotMap.DrivetrainGyro;
private Joystick stick;

  public ExtendFrontPistons() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    gyro.reset();
    sClimb.ExtendFront();
    end();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(gyro.getRoll() < 350 && gyro.getRoll() > 100){//Hopefully gyro counts as we expected
      sClimb.StageTwoStart();
    }
    sClimb.setMotorSpeed(-stick.getRawAxis(5));
    SmartDashboard.putNumber("Gyro Angle", gyro.getRoll());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
