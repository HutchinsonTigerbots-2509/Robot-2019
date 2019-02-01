package frc.robot; // package declartion

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @see Constants.java region for Port #s
 */
public class RobotMap {
    public static WPI_TalonSRX DrivetrainLeftMaster;
    public static WPI_VictorSPX DrivetrainLeftSlave;
    public static WPI_TalonSRX DrivetrainRightMaster;
    public static WPI_VictorSPX DrivetrainRightSlave;
    // public static Encoder DrivetrainLeftEncoder;
    // public static Encoder DrivetrainRightEncoder;
    public static SpeedControllerGroup DrivetrainLeft;
    public static SpeedControllerGroup DrivetrainRight;
    public static DifferentialDrive DrivetrainDifferential;
    public static DoubleSolenoid DrivetrainShifter;

    // Elevator
    public static WPI_TalonSRX ElevatorMotorMaster;
    public static WPI_VictorSPX ElevatorMotorSlave;
    public static DoubleSolenoid ElevatorShifter;
    public static DigitalInput ElevatorLeftLimit;
    public static DigitalInput ElevatorRightLimit;

    // Intake
    public static VictorSP IntakeRightMotor ;
    public static VictorSP IntakeLeftMotor;
    public static VictorSP IntakeMotor;

    public static SpeedControllerGroup IntakeMotors;

    public static DoubleSolenoid IntakePushPiston;
    public static DoubleSolenoid IntakeWristPiston;
    public static DoubleSolenoid IntakeOpenPiston;

    // Sensors
    public static AHRS Drivetrain_Gyro;

    public static void init() {
        // #region DriveTrain
        DrivetrainLeftMaster = new WPI_TalonSRX(Constants.kDrivetrainLeftMasterID); // Front Left Motor
        DrivetrainLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder); // The Encoder
        DrivetrainLeftMaster.setInverted(false);
        DrivetrainLeftMaster.configNeutralDeadband(Constants.kNeutralDeadband, 0);

        DrivetrainLeftSlave = new WPI_VictorSPX(Constants.kDrivetrainLeftSlaveID); // Rear Left Motor
        DrivetrainLeftSlave.follow(DrivetrainLeftMaster); // Follow Your Master (Above)
        DrivetrainLeftSlave.setInverted(InvertType.FollowMaster);
        DrivetrainLeftSlave.configNeutralDeadband(Constants.kNeutralDeadband, 0);

        DrivetrainRightMaster = new WPI_TalonSRX(Constants.kDrivetrainRightMasterID); // Front Right Motor
        DrivetrainRightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder); // The Encoder
        DrivetrainRightMaster.setInverted(false);
        DrivetrainRightMaster.configNeutralDeadband(Constants.kNeutralDeadband, 0);

        DrivetrainRightSlave = new WPI_VictorSPX(Constants.kDrivetrainRightSlaveID); // Rear Right Motor
        DrivetrainRightSlave.follow(DrivetrainRightMaster); // Follow Your Master (Above)
        DrivetrainRightSlave.setInverted(InvertType.FollowMaster);
        DrivetrainRightSlave.configNeutralDeadband(Constants.kNeutralDeadband, 0);

        // A Master is used as a SpeedControllerGroup in this case. This allows us to
        // use
        // the VictorSPX datatype for motors. However, the masters must still be Talons.
        // NOTE: The Masters contain the encoders for the Drivetrain
        DrivetrainDifferential = new DifferentialDrive(DrivetrainLeftMaster, DrivetrainRightMaster); // Drive Varible

        DrivetrainShifter = new DoubleSolenoid(Constants.kDrivetrainShifterForwardID, Constants.kDrivetrainShifterReverseID);

        Drivetrain_Gyro = new AHRS(SPI.Port.kMXP);
        // #endregion

        // #region Elevator
        ElevatorMotorMaster = new WPI_TalonSRX(Constants.kElevatorMasterID);
        ElevatorMotorMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        // ElevatorMotorMaster.setInverted(false);
        ElevatorMotorMaster.setSubsystem("Elevator");

        ElevatorMotorSlave = new WPI_VictorSPX(Constants.kElevatorSlaveID);
        ElevatorMotorSlave.follow(ElevatorMotorMaster);
        ElevatorMotorSlave.setInverted(InvertType.FollowMaster);
        ElevatorMotorSlave.setSubsystem("Elevator");

        ElevatorShifter = new DoubleSolenoid(0, 1);

        ElevatorLeftLimit = new DigitalInput(Constants.kElevatorLeftLimitID);

        ElevatorRightLimit = new DigitalInput(Constants.kElevatorRightLimitID);
        // #endregion

        // #region Intake

        // IntakeRightMotor = new VictorSP(Constants.kIntakeRightMotorID);
        // IntakeLeftMotor = new VictorSP(Constants.kIntakeLeftMotorID);
        // IntakeMotors = new SpeedControllerGroup(IntakeRightMotor, IntakeLeftMotor);
        IntakeMotor = new VictorSP(1);

        IntakePushPiston  = new DoubleSolenoid(Constants.kIntakePushForwardID, Constants.kIntakePushReverseID);
        IntakeOpenPiston = new DoubleSolenoid(Constants.kOpenIntakePistonForwardID, Constants.kOpenIntakePistonReverseID);
        IntakeWristPiston = new DoubleSolenoid(Constants.kIntakeWristForwardID, Constants.kIntakeWristReverseID);
        // #endregion

    }
}
