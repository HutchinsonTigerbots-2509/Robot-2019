package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/** Toggles the Elevator State to "Hatch" */
public class HatchToggle extends InstantCommand {
  /**
   * Add your docs here.
   */
  public HatchToggle() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.sElevator.state = "Hatch";
    Robot.oi.setElevatorButtonsCargo();
    SmartDashboard.putBoolean("State", true);
  }

}
