package frc.robot.commands; // package

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Climb;

/**
 * Should extend the low pistons, and then extend the high pistons
 * (stage 1 -> stage 2). There is a one second delay in between.
 * 
 * @author CRahne
 */
public class ClimbExtend extends InstantCommand {
  private Climb sClimb = Robot.sClimb;

  /**
   * The constructor to make a new instance of this command
   */
  public ClimbExtend() {
    requires(sClimb);
  }

  protected void initialize() {
    sClimb.StageOneStart();
    Timer.delay(1); // Need to tune/get rid of if needed
    sClimb.StageTwoStart();
  }

}
