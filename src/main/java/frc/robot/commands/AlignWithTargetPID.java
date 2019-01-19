package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;

/**
 * @author Nate
 */
public class AlignWithTargetPID extends Command {
  private Drivetrain sDrive = Robot.sDrivetrain;
  private Vision sVision = Robot.sVision;
  private int mSetpoint = 0;
  private double mTargetX =0;
  private double mDegreeOfTolerance = 1;
  public AlignWithTargetPID() {
    requires(sDrive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    sDrive.setSetpoint(mSetpoint);
    mTargetX = sVision.getTargetX();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    mTargetX = sVision.getTargetX();
    sDrive.AimToTargetPID(mTargetX);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    mTargetX = sVision.getTargetX();
    return Math.abs(mTargetX) <= mDegreeOfTolerance;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    sDrive.StopMotors();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    sDrive.StopMotors();
  }
}
