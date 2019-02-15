package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * @see Constants.java region for Port #'s
 */
public class RobotMap {
    /* DRIVETRAIN */
    // Motors
    public static WPI_TalonSRX DrivetrainLeftMaster;
    public static WPI_VictorSPX DrivetrainLeftSlave;
    public static WPI_TalonSRX DrivetrainRightMaster;
    public static WPI_VictorSPX DrivetrainRightSlave;
    // Drivetrain Group
    public static DifferentialDrive DrivetrainDifferential;
    // Shifter
    public static DoubleSolenoid DrivetrainShifter;
    // Sensors
    public static AHRS DrivetrainGyro;

    /* ELEVATOR */
    // Motors
    public static WPI_TalonSRX ElevatorMotor;
    // Pneumatics
    public static DoubleSolenoid ElevatorShifter;
    // Digital Input
    public static DigitalInput ElevatorLeftLimit;
    public static DigitalInput ElevatorRightLimit;
    public static Ultrasonic ElevatorSonic;

    /* INTAKE */
    // Motors
    public static VictorSP IntakeRightMotor;
    public static VictorSP IntakeLeftMotor;
    public static VictorSP IntakeMotor;
    // Motor Groups
    public static SpeedControllerGroup IntakeMotors;
    // Pneumatics
    public static DoubleSolenoid IntakeWristPiston;
    public static DoubleSolenoid IntakeHatchPiston;

    /* CLIMB */
    // Motors
    public static WPI_TalonSRX ClimbMotor;
    // Pneumatics
    public static DoubleSolenoid ClimbHighPistons;
    public static DoubleSolenoid ClimbLowPistons;

    public static void init() {
        DrivetrainLeftMaster = new WPI_TalonSRX(Constants.kDrivetrainLeftMasterID); // Front Left Motor
        DrivetrainLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder); // The DT Encoder
        DrivetrainLeftMaster.setInverted(false); // Tell the motor that it isn't inverted (backwards)
        DrivetrainLeftMaster.configNeutralDeadband(Constants.kNeutralDeadband, 0);
        DrivetrainLeftMaster.configPeakOutputForward(Constants.kMaxSpeed);
        DrivetrainLeftMaster.configPeakOutputReverse(-Constants.kMaxSpeed);
        DrivetrainLeftMaster.setSubsystem("Drivetrain");

        DrivetrainLeftSlave = new WPI_VictorSPX(Constants.kDrivetrainLeftSlaveID); // Rear Left Motor
        DrivetrainLeftSlave.follow(DrivetrainLeftMaster); // Follow Your Master (Above)
        DrivetrainLeftSlave.setInverted(InvertType.FollowMaster); // Follow Your Master (Above)
        DrivetrainLeftSlave.configNeutralDeadband(Constants.kNeutralDeadband, 0);
        DrivetrainLeftSlave.configPeakOutputForward(Constants.kMaxSpeed);
        DrivetrainLeftSlave.configPeakOutputReverse(-Constants.kMaxSpeed);
        DrivetrainLeftSlave.setSubsystem("Drivetrain");

        DrivetrainRightMaster = new WPI_TalonSRX(Constants.kDrivetrainRightMasterID); // Front Right Motor
        DrivetrainRightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder); // The Encoder
        DrivetrainRightMaster.setInverted(false); // Inverted is false
        DrivetrainRightMaster.configNeutralDeadband(Constants.kNeutralDeadband, 0); // Sets the motor's deadband (above)
        DrivetrainRightMaster.configPeakOutputForward(Constants.kMaxSpeed);
        DrivetrainRightMaster.configPeakOutputReverse(-Constants.kMaxSpeed);
        DrivetrainRightMaster.setSubsystem("Drivetrain");

        DrivetrainRightSlave = new WPI_VictorSPX(Constants.kDrivetrainRightSlaveID); // Rear Right Motor
        DrivetrainRightSlave.follow(DrivetrainRightMaster); // Follow Your Master (Above)
        DrivetrainRightSlave.setInverted(InvertType.FollowMaster); // Follow Your Master
        DrivetrainRightSlave.configNeutralDeadband(Constants.kNeutralDeadband, 0); // Sets the motor's deadband (above)
        DrivetrainRightSlave.configPeakOutputForward(Constants.kMaxSpeed);
        DrivetrainRightSlave.configPeakOutputReverse(-Constants.kMaxSpeed);
        DrivetrainRightSlave.setSubsystem("Drivetrain");

        DrivetrainDifferential = new DifferentialDrive(DrivetrainLeftMaster, DrivetrainRightMaster); // Drive Varible
        DrivetrainDifferential.setDeadband(Constants.kNeutralDeadband);
        DrivetrainDifferential.setMaxOutput(Constants.kMaxSpeed);
        DrivetrainDifferential.setSubsystem("Drivetrain");

        DrivetrainShifter = new DoubleSolenoid(Constants.kDrivetrainShifterForwardID,Constants.kDrivetrainShifterReverseID);
        DrivetrainShifter.setSubsystem("Drivetrain");

        DrivetrainGyro = new AHRS(SPI.Port.kMXP);
        DrivetrainGyro.setSubsystem("Drivetrain");
        // #endregion

        // #region Elevator
        ElevatorMotor = new WPI_TalonSRX(Constants.kElevatorMasterID);
        ElevatorMotor.setSubsystem("Elevator");
        ElevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        // ElevatorMotor.setInverted(false);
        ElevatorMotor.configPeakOutputForward(Constants.kMaxElevatorSpeed);
        ElevatorMotor.configPeakOutputReverse(-Constants.kMaxElevatorSpeed);

        ElevatorShifter = new DoubleSolenoid(1, Constants.kElevatorShifterForwardID, Constants.kElevatorShifterReverseID);
        ElevatorShifter.setSubsystem("Elevator");

        ElevatorLeftLimit = new DigitalInput(Constants.kElevatorLeftLimitID);
        ElevatorLeftLimit.setSubsystem("Elevator");

        ElevatorRightLimit = new DigitalInput(Constants.kElevatorRightLimitID);
        ElevatorRightLimit.setSubsystem("Elevator");

        ElevatorSonic = new Ultrasonic(8, 9);
        ElevatorSonic.setAutomaticMode(true);
        ElevatorSonic.setDistanceUnits(Unit.kInches);
        ElevatorSonic.setEnabled(true);
        ElevatorSonic.setSubsystem("Elevator");
        // #endregion

        // #region Intake
        IntakeMotor = new VictorSP(Constants.kIntakeMotorID);
        IntakeMotor.setSubsystem("Intake");

        IntakeWristPiston = new DoubleSolenoid(Constants.kIntakeWristForwardID, Constants.kIntakeWristReverseID);
        IntakeWristPiston.setSubsystem("Intake");

        IntakeHatchPiston = new DoubleSolenoid(Constants.kIntakeHatchPistonForwardID, Constants.kIntakeHatchPistonReverseID);
        IntakeHatchPiston.setSubsystem("Intake");
        // #endregion

        // #region Climb
        ClimbMotor = new WPI_TalonSRX(Constants.kClimbMotorID);
        ClimbMotor.setSubsystem("Climb");

        ClimbHighPistons = new DoubleSolenoid(1, Constants.kClimbHighForwardID, Constants.kClimbHighReverseID);
        ClimbHighPistons.setSubsystem("Climb");

        ClimbLowPistons = new DoubleSolenoid(1, Constants.kClimbLowForwardID, Constants.kClimbLowReverseID);
        ClimbLowPistons.setSubsystem("Climb");
        // #endregion Climb

    }
}