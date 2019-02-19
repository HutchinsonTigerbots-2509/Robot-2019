package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

/**
 * Will stop the intake subsystem (whatever it is doing)
 */
public class IntakeStop extends InstantCommand {
  private Intake sIntake = Robot.sIntake;
  public IntakeStop() {
    super();
    requires(sIntake);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    sIntake.MotorStop();
  }
}
