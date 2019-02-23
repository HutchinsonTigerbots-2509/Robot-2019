package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Elevator;

public class ElevatorShift extends InstantCommand {
  private final Elevator mElevator = new Elevator();
  private boolean isShifted;
  public ElevatorShift() {
    super();
    requires(mElevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    isShifted = mElevator.isHighGear();
    mElevator.setHighGear(!isShifted);
  }
}
