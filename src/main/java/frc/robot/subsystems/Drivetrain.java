package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Intake;

/**
 * The DriveTrain Subsystem is where the drivetrain is bound to the code through
 * the motors created in RobotMap, which are stored in a Differential Drive
 * Varible.
 * 
 * <h3>JavaDoc Categories for Functions:</h3>
 * <li>+ Basic Drive Methods - Basic Driving Methods like Operator Drive
 * <li>+ Update Voids - Updates something, like data or sensors
 * <li>+ Shifter - Voids controlling the shifter piston
 * <li>+ PID Controller - Control Voids using a PID Equation
 * <li>+ DriveTrain Getters - Will return something, like an object
 * 
 * @author CRahne, Wayne, Tony, Cole, and Nate
 */
public class Drivetrain extends Subsystem {
  private final Intake sIntake = Robot.sIntake;
  // Motors
  private final WPI_TalonSRX mLeftMaster = RobotMap.DrivetrainLeftMaster;
  private final VictorSPX mLeftSlave = RobotMap.DrivetrainLeftSlave;
  private final WPI_TalonSRX mRightMaster = RobotMap.DrivetrainRightMaster;
  private final VictorSPX mRightSlave = RobotMap.DrivetrainRightSlave;
  // The Differential Drive Object object (for mDrive.tankDrive)
  private final DifferentialDrive mDrive = RobotMap.DrivetrainDifferential;
  // Gyro
  private final AHRS mGyro = RobotMap.DrivetrainGyro;
  // Shuffleboard
  private final ShuffleboardTab mDriveTrainTab = Shuffleboard.getTab("Drivetrain");
  // Vision
  public boolean TargetAligned;
  public boolean TargetDistanceCheck;
  /**
   * Adds children to the object so we can play with components in test mode
   */
  public Drivetrain() {
    setName("Drivetrain");
    addChild(mLeftMaster);
    addChild(mLeftSlave);
    addChild(mRightMaster);
    addChild(mRightSlave);
  }

  /**
   * Will stop all motors
   */
  public void StopMotors() {
    mRightMaster.stopMotor();
    mLeftMaster.stopMotor();
  }

  public void OperatorDrive(Joystick stick) {
    SmartDashboard.putNumber("Stick Y", stick.getY());
    if(stick.getY() > Constants.minMovePercent || stick.getZ() > Constants.minMovePercent) {
        mDrive.arcadeDrive(stick.getY() * 0.8, stick.getZ() * 0.8);        
    } else {
      mDrive.arcadeDrive(0, 0);
    }
  }
  
  public void MarioDrive(Joystick stick) {
    double Speed = 0.0;
    if(stick.getRawAxis(3) > 0) {
      Speed = stick.getRawAxis(3) * -0.8;
    } else if(stick.getRawAxis(2) > 0) {
      Speed = stick.getRawAxis(2) * 0.8;
    }

    if(Speed > 0) {
      mDrive.arcadeDrive(Speed, stick.getRawAxis(0) * -0.8);
    }
    else {
      mDrive.arcadeDrive(Speed, stick.getRawAxis(0) * -0.8);
    }
  }
  /**
   * Will update all values that are sent to the Shuffleboard
   * 
   * <li>- Left Encoder, Right Encoder, and Encoder Average Distance
   * <li>- Gyro Angle
   * <li>- Differential Drive Object
   * <li>- Shifter Object
   * <li>- isShifted Boolean
   */
  public void UpdateTelemetry() {
    mDriveTrainTab.add("Left Encoder", mLeftMaster.getSelectedSensorPosition());
    mDriveTrainTab.add("Right Encoder", mRightMaster.getSelectedSensorPosition());
    mDriveTrainTab.add("Gyro", mGyro.getAngle());
    Shuffleboard.update();
  }

  @Override
  public void initDefaultCommand() {
  }
}