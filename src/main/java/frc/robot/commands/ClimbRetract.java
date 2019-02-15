package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Climb;

/**
 * Should retract the high pistons, and then retract the low pistons
 * (stage 2 -> stage 1). There is a one second delay in between.
 * 
 * @author CRahne
 */
public class ClimbRetract extends InstantCommand {
  private Climb sClimb = Robot.sClimb;

  /**
   * The constructor to make a new instance of this command
   */
  public ClimbRetract() {
    requires(sClimb);
  }

  protected void initialize() {
    sClimb.RetractStageTwo();
    Timer.delay(1); // Need to tune/get rid of if needed
    sClimb.RetractStageTwo();
  }

}
