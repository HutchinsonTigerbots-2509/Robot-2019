package frc.robot.subsystems; // package declaration

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
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
 *                              Front
 *<h4>   DriveTrainLeft   |----------------| DriveTrain Right </h4>
 *<h4>    DTLeft1         |----------------| DTRight1 </h4>
 *<h4>                    |----------------| </h4>
 *<h4>                    |----------------| </h4>
 *<h4>                    |----------------| </h4>
 *<h4>    DTLeft2         |----------------| DTRight2 </h4>
 *         
 * @author Nate & Rahne                      Back
 */
public class Drivetrain extends Subsystem {
 
  // Varible Declarations
  private final double kMaxSpeed;
  private final DifferentialDrive mDrive;
  private final WPI_TalonSRX mLeftMaster;
  private final WPI_TalonSRX mLeftSlave;
  private final WPI_TalonSRX mRightMaster;
  private final WPI_TalonSRX mRightSlave;
  private final Encoder mLeftEncoder;
  private final Encoder mRightEncoder;

  public Drivetrain(){
    setName("Drivetrain");

    kMaxSpeed = Constants.kMaxSpeed;

    mDrive = RobotMap.RobotDrive;
    addChild(mDrive);

    mLeftMaster = RobotMap.DrivetrainLeftMaster;
    addChild(mLeftMaster);

    mLeftSlave = RobotMap.DrivetrainLeftSlave;
    addChild(mLeftSlave);

    mRightMaster = RobotMap.DrivetrainRightMaster;
    addChild(mRightMaster);

    mRightSlave = RobotMap.DrivetrainRightSlave;
    addChild(mRightSlave);

    mLeftEncoder = RobotMap.DrivetrainLeftEncoder;
    addChild(mLeftEncoder);

    mRightEncoder = RobotMap.DrivetrainRightEncoder;
    addChild(mRightEncoder);
  }

  /**
   * OperatorDrive is the Method for driving. It is called in OperatorDrive.
   * java in the commands folder. It uses the differential drive varible 
   * that was created in RobotMap.java. It will grab the Y-Axis and Z-Axis 
   * of the OPStick in OI.java, then drive the robot.
   * 
   * @param Joystick stick
   */
  public void OperatorDrive(Joystick stick)
  {
  	mDrive.arcadeDrive(stick.getY(), stick.getZ());
  }
  public void StopMotors(){
    mLeftMaster.stopMotor();
    mLeftSlave.stopMotor();
    mRightMaster.stopMotor();
    mRightSlave.stopMotor();
  }

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new OperatorDrive());
  }
}
