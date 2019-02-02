package frc.robot.commands;

//import com.sun.org.apache.xpath.internal.operations.And;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;

public class AlignWithTarget extends Command {
  private Drivetrain sDriveTrain = Robot.sDrivetrain;
  private Vision sVision = Robot.sVision;
  private double TargetX;
  private double TargetY;
  
  public AlignWithTarget() {
    TargetX = sVision.getTargetX();
    TargetY = sVision.getTargetY();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
   
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    sDriveTrain.TargetAligned = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    TargetX = sVision.getTargetX();
    if (TargetX < - 0.5) {
      sDriveTrain.TargetAligned = false;
      sDriveTrain.TurnLeft();
      
    } else if (TargetX > 0.5) {
        sDriveTrain.TurnRight();
    } else {
        sDriveTrain.TargetAligned = true;
        sDriveTrain.StopMotors();
        end(); 
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (TargetY < 0) {
      return TargetY < 0;
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
