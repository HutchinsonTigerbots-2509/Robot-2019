package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Climber;

/**
 * Should retract the high pistons, and then retract the low pistons
 * (stage 2 -> stage 1). There is a one second delay in between.
 * 
 * @author CRahne
 */
public class ClimbRetract extends InstantCommand {
  private Climber sClimb = Robot.sClimb;

  /**
   * The constructor to make a new instance of this command
   */
  public ClimbRetract() {
    requires(sClimb);
  }

  protected void initialize() {
    sClimb.RetractStageTwo();//High
    sClimb.RetractStageOne();//low
  }

}
