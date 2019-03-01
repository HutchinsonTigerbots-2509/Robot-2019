package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;

//import frc.robot.command.OperatorDrive;
public class FollowTarget extends Command {
  private Drivetrain sDriveTrain = Robot.sDrivetrain;
  private Vision sVision = Robot.sVision;
  private double ErrorX;
  private double ErrorY;
  public boolean TargetDistanceCheck = false;
  public double steering_adjust;
  public double X;
  public double Y;
  public double distance_adjust;
  public double TargetX;
  public double min_distance;
  public double right_speed;
  public double left_speed;
  public double kPdistance;
  public double kpAim;
  private Drivetrain sDrivetrain = Robot.sDrivetrain;
  private boolean is_done = false;
  private int pipeline_id;
  private int isChanged = 0;

  public FollowTarget(int pipeline, double kkPdistance, double kkPangle) {

    // ErrorX = -sVision.getTargetX();
    // ErrorY = -sVision.getTargetY();
    // X = sVision.getTargetX();
    // if (pipeline_id == 0){
    // Y = sVision.getTargetY();
    // }else if(pipeline_id == 2){
    // Y = -(sVision.getTargetY());
    // }
    // Y = sVision.getTargetY();
    // right_speed = 0f;
    // left_speed = 0f;
    // sVision.change_vision_pipeline(pipeline);
    pipeline_id = pipeline;
    kPdistance = kkPdistance;
    kpAim = kkPangle;
    // Requires(Drivetrain);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    steering_adjust = 0;
    distance_adjust = 0;
    sDriveTrain.TargetDistanceCheck = false;
    sVision.change_vision_pipeline(pipeline_id);
    Timer.delay(0.5);
    isChanged = 1;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (isChanged == 1) {
      // sVision.change_vision_pipeline(pipeline_id);

      // sVision.change_vision_pipeline(pipeline_id);
      // TargetX = (1 + (sDriveTrain.getGyro().getYaw()*0.7));
      TargetX = 1;
      ErrorX = -sVision.getTargetX();
      ErrorY = sVision.getTargetY();
      SmartDashboard.putNumber("pipline_id", (pipeline_id));
      min_distance = Constants.distance_command;

      // if (Y > -1.5){
      // TargetX = 1;
      // }
      // SmartDashboard.putNumber("Target_x", TargetX);
      SmartDashboard.putNumber("Target_x", TargetX);
      // X = sVision.getTargetX();
      // Y = sVision.getTargetY();
      // if (pipeline_id == 0){
      // Y = sVision.getTargetY();
      // }else if(pipeline_id == 2){
      // Y = -(sVision.getTargetY());
      // }
      // SmartDashboard.putNumber("ball", 1);
      X = (sVision.getTargetX());
      Y = (sVision.getTargetY());
      min_distance = Constants.distance_command;
      distance_adjust = kPdistance * ErrorY + min_distance;
      // }
      // Y = sVision.getTargetY();Y = sVision.getTargetY();
      is_done = false;

      // if (TargetX < 1 && TargetX > -1) {
      // if (TargetX < -3) {
      // sDriveTrain.TurnLeft();
      // } else if (TargetX > 3) {
      // sDriveTrain.TurnRight();
      // } else {
      // sDriveTrain.StopMotors();
      // sDriveTrain.TargetAligned = true;
      // }
      // if (TargetX < -3 || TargetX > 3) {
      // sDriveTrain.TargetAligned = false;
      // if (pipeline_id == 0){
      // Y = sVision.getTargetY();
      // }else if(pipeline_id == 2){
      // Y = -sVision.getTargetY();
      // SmartDashboard.putNumber("ball", 1);

      // }
      if (pipeline_id == 2 || pipeline_id == 0 || pipeline_id == 1 || pipeline_id == 4) {
        Y = -Y;
        min_distance = -min_distance;
        // distance_adjust = Constants.KpDistance * ErrorY + min_distance;
        distance_adjust = distance_adjust;

        // ErrorY = -ErrorY;
      }

      if (Y < 0) {
        if (X > TargetX) {
          steering_adjust = kpAim * ErrorX - Constants.min_aim_command;

          left_speed = steering_adjust;
          // + distance_adjust;
          right_speed = steering_adjust;
          // + distance_adjust;

          SmartDashboard.putNumber("right_2", right_speed);
          SmartDashboard.putNumber("left_2", left_speed);

          sDriveTrain.track_taget(distance_adjust, steering_adjust, pipeline_id);
          SmartDashboard.putNumber("distance_adjust", distance_adjust);
          // TargetDistanceCheck = false;
        } else if (X < TargetX) {
          // SmartDashboard.putNumber("right", right_speed
          // SmartDashboard.putNumber("left", left_speed;
          steering_adjust = kpAim * ErrorX + Constants.min_aim_command;
          // sDriveTrain.StopMotors();
          // TargetDistanceCheck = true;

          left_speed = steering_adjust;
          // + distance_adjust;
          right_speed = steering_adjust;
          // + distance_adjust;
          SmartDashboard.putNumber("right", right_speed);
          SmartDashboard.putNumber("left", left_speed);
          // sDriveTrain.track_taget(left_speed , -right_speed);
          SmartDashboard.putNumber("distance_adjust", distance_adjust);

          sDriveTrain.track_taget(distance_adjust, steering_adjust, pipeline_id);

        }
        // distance_adjust = Constants.KpDistance * ErrorY;
        // sDriveTrain.track_taget(0.5 , 0.5);
        // SmartDashboard.putNumber("distance_adjust", distance_adjust);

      } else {
        is_done = true;
        // SmartDashboard.putBoolean("stop", (is_done));
        // distance_adjust = Constants.KpDistance * ErrorY;
        // sDriveTrain.track_taget(0.5 , 0.5);
        // SmartDashboard.putNumber("distance_adjust", distance_adjust);

      }
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
    // sDriveTrain.TargetAligned = false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
