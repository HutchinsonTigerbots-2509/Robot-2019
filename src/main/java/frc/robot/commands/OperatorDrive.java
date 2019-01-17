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
 * @author CRahne
 */
public class OperatorDrive extends Command {
  
  // Imported Object Declaration  
  private final Joystick mStick = Robot.oi.getOperatorStick(); // The Operator Stick (the one that is used for driving)
  private final Drivetrain sDrivetrain = Robot.sDrivetrain; // The DriveTrain subsystem
  
  public OperatorDrive() {
    requires(sDrivetrain); // Tells the code to use the drivetrain subsystem in this command
  }

  protected void initialize() {
    sDrivetrain.OperatorDrive(mStick); // Uses the OPDRIVE() method that is created in Drivetrain.java
  }

  protected void execute() {
    sDrivetrain.OperatorDrive(mStick); // Uses the OPDRIVE() method that is created in Drivetrain.java
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