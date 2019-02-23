package frc.robot.commands; // package declaration

// imports
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

/** 
 * OPDrive is the command where the controller is physically bound to the drivetrain. This allows whatever
 * Joystick you want to be able to drive.
 * 
 * @author CRahne
 */
public class OperatorDrive extends Command {
  
  // Imported Objects Declaration
  private final Joystick mStick = Robot.oi.getOperatorStick(); // The Operator Stick (the one that is used for driving)
  private final Joystick mCoOpStick = Robot.oi.getCoOperatorStick();
  private final Intake sIntake = Robot.sIntake;
  private final Drivetrain sDrivetrain = Robot.sDrivetrain; // The DriveTrain subsystem
  
  public OperatorDrive() {
    requires(sDrivetrain); // Tells the code to use the drivetrain subsystem in this command
  }

  protected void initialize() {
    sDrivetrain.MarioDrive(mStick); // Uses the OPDRIVE() method that is created in Drivetrain.java
  }

  protected void execute() {
    sDrivetrain.MarioDrive(mStick); // Uses the OPDRIVE() method that is created in Drivetrain.java

    // if(mCoOpStick.getRawAxis(3) == 1){
    //   sIntake.In();
    // }else if(mCoOpStick.getRawAxis(2) == 1){
    //   sIntake.Out();
    // }else{
    //   sIntake.CargoMotorStop();
    // }
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
