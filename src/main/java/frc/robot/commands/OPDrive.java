package frc.robot.commands; // package declaration

// imports
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

/** 
 * OPDrive is the command where the controller is physically bound to the drivetrain. This allows whatever
 * Joystick you want to be able to drive.
 * 
 * 
 * @author He He I Cole Rahne
 */
public class OPDrive extends Command {
  
  // Imported Object Declaration  
  private Joystick stick = Robot.oi.getOpeatorStick(); // The Operator Stick (the one that is used for driving)
  private Drivetrain DT = Robot.sDT; // The DriveTrain subsystem
  
  public OPDrive() {
    requires(DT); // Tells the code to use the drivetrain subsystem in this command
  }

  protected void initialize() {
    DT.teleOpDrive(stick); // Uses the OPDRIVE() method that is created in Drivetrain.java
  }

  protected void execute() {
    DT.teleOpDrive(stick); // Uses the OPDRIVE() method that is created in Drivetrain.java
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
  }

  protected void interrupted() {
    end();
  }
}
