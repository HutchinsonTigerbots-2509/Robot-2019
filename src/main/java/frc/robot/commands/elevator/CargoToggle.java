package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/** Toggles the Elevator State to "Cargo" */
public class CargoToggle extends InstantCommand {

  public CargoToggle() {
  }

  @Override
  protected void initialize() {
    Robot.sElevator.state = "Cargo";
    Robot.oi.setElevatorButtonsCargo();
    SmartDashboard.putBoolean("State", false);
  }

}
