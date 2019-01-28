package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.command.OperatorDrive;
public class FollowTarget extends Command {
  private Drivetrain sDriveTrain = Robot.sDrivetrain;
  private Vision sVision = Robot.sVision;
  private double ErrorX;
  private double ErrorY;
  public boolean TargetDistanceCheck = false;
  private Command AlignWithTarget;
  public double steering_adjust;
  public double X;
  public double Y;
  public double distance_adjust;
  public double min_distance;
  public double right_speed;
  public double left_speed;
  private Drivetrain sdrivetrain = Robot.sDrivetrain;
  private boolean is_done = false;
  private int pipeline_id;
  public FollowTarget(int pipeline) {
    //ErrorX = -sVision.getTargetX();
    //ErrorY = -sVision.getTargetY();
    // X = sVision.getTargetX();
    // if (pipeline_id == 0){
    //   Y = sVision.getTargetY();
    // }else if(pipeline_id == 2){
    //   Y = -(sVision.getTargetY());
    // }
    //Y = sVision.getTargetY();
    right_speed = 0f;
    left_speed = 0f;
    //sVision.change_vision_pipeline(pipeline);
    pipeline_id = pipeline;
    //Requires(Drivetrain);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //requires(OperatorDrive);
    steering_adjust = 0;
    distance_adjust = 0;
    //sDriveTrain.TargetAligned = false;
    sDriveTrain.TargetDistanceCheck = false;
    sVision.change_vision_pipeline(pipeline_id);
    //sVision.change_vision_pipeline(pipeline_id);
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //sVision.change_vision_pipeline(pipeline_id);
    ErrorX = -sVision.getTargetX();
    ErrorY = -sVision.getTargetY();
    SmartDashboard.putNumber("pipline_id", (pipeline_id));
    X = sVision.getTargetX();
    //Y = sVision.getTargetY();
    // if (pipeline_id == 0){
    //   Y = sVision.getTargetY();
    // }else if(pipeline_id == 2){
    //   Y = -(sVision.getTargetY());
    // }
    //  SmartDashboard.putNumber("ball", 1);
    Y = (sVision.getTargetY());
    min_distance = Constants.min_aim_command;
    distance_adjust = Constants.KpDistance * ErrorY + min_distance;
    //}
    //Y = sVision.getTargetY();Y = sVision.getTargetY();
    is_done = false;

    
    
    
    //if (TargetX < 1 && TargetX > -1) {
      // if (TargetX < -3) {
      //   sDriveTrain.TurnLeft();
      // } else if (TargetX > 3) {
      //   sDriveTrain.TurnRight();
      // } else {
      //   sDriveTrain.StopMotors();
      //   sDriveTrain.TargetAligned = true;
      // }
      // if (TargetX < -3 || TargetX > 3) {
      //   sDriveTrain.TargetAligned = false;
// if (pipeline_id == 0){
//   Y = sVision.getTargetY();
// }else if(pipeline_id == 2){
//   Y = -sVision.getTargetY();
//   SmartDashboard.putNumber("ball", 1);

// }
  if(pipeline_id == 2){
    Y = -Y;
    min_distance = -min_distance;
    distance_adjust = -distance_adjust;
    distance_adjust = Constants.KpDistance * ErrorY + min_distance;
    //ErrorY = -ErrorY;
  }
  if (Y < 0){
    if (X > 1){
        steering_adjust = Constants.KpAim * ErrorX - Constants.min_aim_command;
        
        left_speed = steering_adjust;
        //+ distance_adjust;
        right_speed = steering_adjust;
        // + distance_adjust;
        
        SmartDashboard.putNumber("right_2", right_speed);
        SmartDashboard.putNumber("left_2", left_speed);
        
        sDriveTrain.track_taget(distance_adjust , steering_adjust, pipeline_id);
        SmartDashboard.putNumber("distance_adjust", distance_adjust);
        //TargetDistanceCheck = false;
      } else if (X < 1) {
          //SmartDashboard.putNumber("right", right_speed
          //SmartDashboard.putNumber("left", left_speed;
          steering_adjust = Constants.KpAim * ErrorX + Constants.min_aim_command;
          //sDriveTrain.StopMotors();
          //TargetDistanceCheck = true;
          
          left_speed = steering_adjust; 
          //+ distance_adjust;
          right_speed = steering_adjust; 
          //+ distance_adjust;
          SmartDashboard.putNumber("right", right_speed);
          SmartDashboard.putNumber("left", left_speed);
          //sDriveTrain.track_taget(left_speed , -right_speed);
          SmartDashboard.putNumber("distance_adjust", distance_adjust);
        
          sDriveTrain.track_taget(distance_adjust  , steering_adjust, pipeline_id);
         
      
        
    }
      //distance_adjust = Constants.KpDistance * ErrorY;
      //sDriveTrain.track_taget(0.5 , 0.5);
      //SmartDashboard.putNumber("distance_adjust", distance_adjust);
        
       
    }else{
      is_done = true;
      //SmartDashboard.putBoolean("stop", (is_done));
      //distance_adjust = Constants.KpDistance * ErrorY;
      //sDriveTrain.track_taget(0.5 , 0.5);
      //SmartDashboard.putNumber("distance_adjust", distance_adjust);

    }
    
  }
  

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (is_done) {
      return true;
    } else {
      return false;
    }
    
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    sDriveTrain.StopMotors();
    //sDriveTrain.TargetAligned = false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
