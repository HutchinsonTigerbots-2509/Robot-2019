package frc.robot.subsystems; // package declaration

// imports
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * The DriveTrain Subsystem is where the drivetrain is bound to the code
 * through the motors created in RobotMap, which are stored in a Differential
 * Drive Varible
 * 
 *                               Front
 *<h4>   DriveTrainLeft   |----------------| DriveTrain Right </h4>
 *<h4>    DTLeft1         |----------------| DTRight1 </h4>
 *<h4>                    |----------------| </h4>
 *<h4>                    |----------------| </h4>
 *<h4>                    |----------------| </h4>
 *<h4>    DTLeft2         |----------------| DTRight2 </h4>
 *                               Back
 */
public class Drivetrain extends Subsystem {
 
  // Varible Declarations
  private final double kMaxSpeed;
  DifferentialDrive Drive = RobotMap.Drive;
  
  
  public Drivetrain(){
    kMaxSpeed = Constants.kMaxSpeed;
    //addChild("LeftDriveEncoder",leftDriveEncoder);
  }

  /**
   * OPDrive is the Method for driving. It is called in OPDrive.java in the
   * commands folder. It uses the differential drive varible that was created
   * in RobotMap.java. It will grab the Y-Axis and Z-Axis of the OPStick
   * in OI.java, then drive the robot.
   * 
   * @param Joystick stick
   * @author CRahne
   */
  public void OPDRIVE(Joystick stick)
  {
  	Drive.arcadeDrive(stick.getY(), stick.getZ());
  }

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
