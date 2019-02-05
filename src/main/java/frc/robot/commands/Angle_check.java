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
  private double Distance_X;
  private double Distance_Y;
  private double Distance_offsetted;
  private double Angle_To_Turn;
  private double Distance_To_Drive;
  private double Gyro_angle_raw;
  private double Gyro_angle_absolute;
  private double Gyro_angle_adjusted;
  private double Angle_off;
  private double Pi_over_2;
   
  private double atan_hf; 
  public Angle_check() {
    
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Y = (sVision.getTargetY());
    Gyro_angle = RobotMap.Drivetrain_Gyro.getYaw();
    distance = sVision.cal_distance();
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //SmartDashboard.putNumber("distance", (distance));
    //Gyro_angle = RobotMap.Drivetrain_Gyro.getYaw();
    //Gyro_angle_absolute = Math.abs(Gyro_angle);
    Gyro_angle_raw = RobotMap.Drivetrain_Gyro.getYaw();
    Gyro_angle_adjusted = Math.abs(Math.toRadians(((Math.round(Gyro_angle_raw/90)*90))));
    Angle_off = Math.toRadians(Gyro_angle_raw - Gyro_angle_adjusted);
    distance = sVision.cal_distance();
    Distance_X = Math.abs(distance * Math.sin(Angle_off));
    Distance_Y = distance * Math.cos(Angle_off);
    Distance_offsetted = Distance_Y - 36;
    Pi_over_2 = (Math.PI / 2);
    atan_hf = Math.atan(Distance_offsetted/Distance_X);
    Angle_To_Turn = Math.abs(Pi_over_2 - atan_hf - (Math.abs(Angle_off)));
    
    
    //Angle_To_Turn = Math.toRadians(90) - (Gyro_angle + Math.atan(Distance_offsetted/Distance_X));
    Distance_To_Drive = Math.sqrt(Math.pow(Distance_X, 2) + Math.pow(Distance_offsetted, 2));
    Angle_To_Turn = Math.toDegrees(Angle_To_Turn);
    if(Angle_off < 0){
      Angle_To_Turn = Angle_To_Turn * -1;
    }
    
    SmartDashboard.putNumber("Angle_To_Turn", Angle_To_Turn);
    SmartDashboard.putNumber("Distance_To_drive", Distance_To_Drive);
  
    
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
