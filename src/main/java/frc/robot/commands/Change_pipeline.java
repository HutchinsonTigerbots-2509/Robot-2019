/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import java.util.concurrent.TimeUnit;
import edu.wpi.first.wpilibj.Timer;


import java.util.concurrent.TimeUnit;
//private double Pipeline_id;
import frc.robot.subsystems.Vision;
public class Change_pipeline extends Command {
  private int Pipeline_id;
  private Vision sVision = Robot.sVision;
  private int done;
  public Change_pipeline(int Wanted_Pipeline_id) {
  //private int done;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    Pipeline_id = Wanted_Pipeline_id;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    sVision.change_vision_pipeline(Pipeline_id);
    Timer.delay(0.5);
    done = 1;
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
   if(done == 1){
    return true;
   }else{
    return false;
   }
     
    
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    done = 0;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
