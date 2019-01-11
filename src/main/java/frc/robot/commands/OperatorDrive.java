package frc.robot.commands; // package declaration

// imports
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.EventImportance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

/** 
 * OPDrive is the command where the controller is physically bound to the drivetrain. This allows whatever
 * Joystick you want to be able to drive.
 * 
 * 
 * @author CRahne
 */
public class OperatorDrive extends Command {
  // Imported Object Declaration  
  private Joystick mStick = Robot.oi.opstick; // The Operator Stick (the one that is used for driving)
  private Drivetrain sDrivetrain = Robot.sDrivetrain; // The DriveTrain subsystem
  
  public OperatorDrive() {
    // Tells the code to use the drivetrain subsystem in this command
    requires(sDrivetrain); 
  }

  protected void initialize() {
    // Uses the OperatorDrive() method that is created in Drivetrain.java
    sDrivetrain.OperatorDrive(mStick); 
    Shuffleboard.addEventMarker("Initialized OperatorDrive", EventImportance.kTrivial);
  }

  protected void execute() {
    // Uses the OperatorDrive() method that is created in Drivetrain.java
    sDrivetrain.OperatorDrive(mStick); 
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
