package frc.robot.commands; // package

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Climber;

/**
 * Should extend the low pistons, and then extend the high pistons
 * (stage 1 -> stage 2). There is a one second delay in between.
 * 
 * @author CRahne
 */
public class ClimbExtend extends Command {
  private Climber sClimb = Robot.sClimb;
  private Joystick stick;

  /**
   * The constructor to make a new instance of this command
   */
  public ClimbExtend() {
    requires(sClimb);
    // stick = joystick;
  }

  @Override
  protected void initialize() {
    stick = Robot.oi.getOperatorStick();
    sClimb.StageOneStart();//low
    sClimb.StageTwoStart();//High
  }
  protected void execute(){
    sClimb.setMotorSpeed(-stick.getY()*2);
  }
  protected boolean isFinished(){
    return false;
  }
  protected void end(){
    sClimb.setMotorSpeed(0);
  }
  protected void interrupeted(){
    end();
  }
}
