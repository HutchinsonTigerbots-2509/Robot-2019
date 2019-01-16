package frc.robot.subsystems; // package declaration

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
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
  private DifferentialDrive mDrive = RobotMap.DrivetrainDifferential;
  private SpeedControllerGroup mLeft = RobotMap.DrivetrainLeft;
  private SpeedControllerGroup mRight = RobotMap.DrivetrainRight;
  private WPI_TalonSRX mLeftFront = RobotMap.DrivetrainLeftFront;
  private WPI_TalonSRX mLeftRear = RobotMap.DrivetrainLeftRear;
  private WPI_TalonSRX mRightFront = RobotMap.DrivetrainRightFront;
  private WPI_TalonSRX mRightRear = RobotMap.DrivetrainRightRear;

  
  
  public Drivetrain(){
    //addChild("LeftDriveEncoder",leftDriveEncoder);
  }

  /**
   * Will drive forward at 0.95 volts
   */
  public void driveForward()
  {
    mDrive.tankDrive(Constants.kMaxSpeed, Constants.kMaxSpeed);
  }

  /**
   * Will drive forward at 0.65 volts
   */
  public void driveForwardSlow()
  {
    mDrive.tankDrive(Constants.kSlowSpeed, Constants.kSlowSpeed);
  }

  /**
   * Will drive in reverse at 0.95 volts. Highly not reccommened
   */
  public void driveReverse()
  {
    mDrive.tankDrive(-Constants.kMaxSpeed, -Constants.kMaxSpeed);
  }

  /**
   * Will drive in reverse at 0.65 volts. More recomeended than driveReverse()
   */
  public void driveReverseSlow()
  {
    mDrive.tankDrive(-Constants.kSlowSpeed, -Constants.kSlowSpeed);
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
  public void OperatorDrive(Joystick stick)
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
  public WPI_TalonSRX getLeftFront()
  {
    return mLeftFront;
  }

  /**
   * Will return the DriveTrain's Front Rear Motor
   * 
   * @return mDT_LeftRear
   * @author CRahne
   */
  public WPI_TalonSRX getLeftRear()
  {
    return mLeftRear;
  }

  /**
   * Will return the DriveTrain's Right Front Motor
   * 
   * @return mDT_RightFront
   * @author CRahne
   */
  public WPI_TalonSRX getRightFront()
  {
    return mRightFront;
  }

  
  /**
   * Will return the DriveTrain's Right Rear Motor
   * 
   * @return mDT_RightRear
   * @author CRahne
   */
  public WPI_TalonSRX getRightRear()
  {
    return mRightRear;
  }

  public void initDefaultCommand() {
  }
}
