package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class WristUp extends InstantCommand {
  private Intake sIntake = Robot.sIntake;
  public WristUp() {
    super();
    requires(sIntake);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    sIntake.WristPistonUp();
    Timer.delay(0.1);
    sIntake.StopWristPiston();
  }
}
