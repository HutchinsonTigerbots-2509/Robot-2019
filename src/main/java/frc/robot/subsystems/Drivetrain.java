package frc.robot.subsystems; // package declaration

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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
 * @author CRahne and Wayne
 */
public class Drivetrain extends Subsystem {
 
  // Varible Declarations
  private final double kMaxSpeed;
  private final double kSlowSpeed;
  private DifferentialDrive mDrive = RobotMap.kDrive;

  private WPI_TalonSRX mDT_RightFront = RobotMap.kDT_RFront;
  private WPI_TalonSRX mDT_RightRear = RobotMap.kDT_RRear;
  private WPI_TalonSRX mDT_LeftFront = RobotMap.kDT_LFront;
  private WPI_TalonSRX mDT_LeftRear = RobotMap.kDT_LRear;
  
  
  public Drivetrain(){
    kMaxSpeed = Constants.kMaxSpeed;
    kSlowSpeed = Constants.kSlowSpeed;
    //addChild("LeftDriveEncoder",leftDriveEncoder);
  }

  /**
   * Will drive forward at 0.95 volts
   */
  public void driveForward()
  {
    mDrive.tankDrive(kMaxSpeed, kMaxSpeed);
  }

  /**
   * Will drive forward at 0.65 volts
   */
  public void driveForwardSlow()
  {
    mDrive.tankDrive(kSlowSpeed, kSlowSpeed);
  }

  /**
   * Will drive in reverse at 0.95 volts. Highly not reccommened
   */
  public void driveReverse()
  {
    mDrive.tankDrive(-kMaxSpeed, -kMaxSpeed);
  }

  /**
   * Will drive in reverse at 0.65 volts. More recomeended than driveReverse()
   */
  public void driveReverseSlow()
  {
    mDrive.tankDrive(-kSlowSpeed, -kSlowSpeed);
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
  public void teleOpDrive(Joystick stick)
  {
  	mDrive.arcadeDrive(stick.getY(), stick.getZ());
  }

  /**
   * Will return the Drive Varible from RobotMap.java
   * 
   * @author CRahne
   * @return mDrive
   */
  public DifferentialDrive getDrive()
  {
    return mDrive;
  }

  /**
   * Will return the DriveTrain's Front Left Motor
   * 
   * @return mDT_LeftFront
   * @author CRahne
   */
  public WPI_TalonSRX getDT_LFront()
  {
    return mDT_LeftFront;
  }

  /**
   * Will return the DriveTrain's Front Rear Motor
   * 
   * @return mDT_LeftRear
   * @author CRahne
   */
  public WPI_TalonSRX getDT_LRear()
  {
    return mDT_LeftRear;
  }

  /**
   * Will return the DriveTrain's Right Front Motor
   * 
   * @return mDT_RightFront
   * @author CRahne
   */
  public WPI_TalonSRX getDT_RFront()
  {
    return mDT_RightFront;
  }

  
  /**
   * Will return the DriveTrain's Right Rear Motor
   * 
   * @return mDT_RightRear
   * @author CRahne
   */
  public WPI_TalonSRX getDTRRear()
  {
    return mDT_RightRear;
  }

  public void initDefaultCommand() {
  }
}
