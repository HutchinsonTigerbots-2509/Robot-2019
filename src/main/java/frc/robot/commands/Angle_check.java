/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import com.kauailabs.navx.frc.AHRS;
import frc.robot.RobotMap;
import frc.robot.Robot;
import frc.robot.subsystems.Vision;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Angle_check extends Command {
  public double Gyro_angle = 0;
  private Vision sVision = Robot.sVision;
  private double Y;
  private double distance;
  
  
  public Angle_check() {
    
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Y = (sVision.getTargetY());
    Gyro_angle = RobotMap.Drivetrain_Gyro.getYaw();
    distance = sVision.cal_distance(Y);
    sVision.calculateDistanceVariables(Gyro_angle, distance);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("distance", (distance));
    
    
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
