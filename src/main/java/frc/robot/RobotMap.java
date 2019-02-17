package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;
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
    public static WPI_TalonSRX ElevatorMotorMaster;
    // public static WPI_VictorSPX ElevatorMotorSlave;
    // Pneumatics
    public static DoubleSolenoid ElevatorShifter;
    // Digital Input
    public static DigitalInput ElevatorTopLimit;
    public static DigitalInput ElevatorBottomLimit;
    public static Ultrasonic ElevatorSonic;

    /* INTAKE */
    // Motors
    public static VictorSP IntakeMotor;
    public static WPI_TalonSRX WristMotor;
    // Pneumatics
    // public static DoubleSolenoid IntakeWristPiston;
    // public static DoubleSolenoid IntakeHatchPiston;

    /* CLIMB */
    // Motors
    public static VictorSP ClimbMotor;
    // Pneumatics
    public static DoubleSolenoid ClimbUpperPiston;
    public static DoubleSolenoid ClimbLowerPiston;

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
        ElevatorMotorMaster = new WPI_TalonSRX(Constants.kElevatorMasterID);
        ElevatorMotorMaster.setSubsystem("Elevator");
        ElevatorMotorMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        ElevatorMotorMaster.setInverted(false);
        ElevatorMotorMaster.configPeakOutputForward(Constants.kElevatorMaxSpeed);
        ElevatorMotorMaster.configPeakOutputReverse(-Constants.kElevatorMaxSpeed);
        ElevatorMotorMaster.configNominalOutputForward(Constants.kElevatorMinSpeedUp);
        ElevatorMotorMaster.configNominalOutputReverse(Constants.kElevatorMinSpeedDown);
        ElevatorMotorMaster.setSensorPhase(true);
        ElevatorMotorMaster.setSelectedSensorPosition(0);
        // ElevatorMotorMaster.config_kD(0, Constants.kElevatorDGain);
        // ElevatorMotorMaster.config_kP(0, Constants.kElevatorPGain);
        // ElevatorMotorMaster.config_kI(0, Constants.kElevatorIGain);

        // ElevatorMotorSlave = new WPI_VictorSPX(Constants.kElevatorSlaveID);
        // ElevatorMotorSlave.setSubsystem("Elevator");
        // ElevatorMotorSlave.follow(ElevatorMotorMaster);
        // ElevatorMotorSlave.setInverted(InvertType.FollowMaster);
        // ElevatorMotorSlave.configPeakOutputForward(Constants.kMaxElevatorSpeed);
        // ElevatorMotorSlave.configPeakOutputReverse(-Constants.kMaxElevatorSpeed);

        ElevatorShifter = new DoubleSolenoid(1, Constants.kElevatorShifterForwardID, Constants.kElevatorShifterReverseID);
        ElevatorShifter.setSubsystem("Elevator");

        ElevatorTopLimit = new DigitalInput(Constants.kElevatorTopLimitID);
        ElevatorTopLimit.setSubsystem("Elevator");

        ElevatorBottomLimit = new DigitalInput(Constants.kElevatorBottomLimitID);
        ElevatorBottomLimit.setSubsystem("Elevator");

        ElevatorSonic = new Ultrasonic(8, 9);
        ElevatorSonic.setAutomaticMode(true);
        ElevatorSonic.setDistanceUnits(Unit.kInches);
        ElevatorSonic.setEnabled(true);
        ElevatorSonic.setSubsystem("Elevator");
        // #endregion

        // #region Intake
        IntakeMotor = new VictorSP(Constants.kIntakeMotorID);
        IntakeMotor.setSubsystem("Intake");
        WristMotor = new WPI_TalonSRX(Constants.kWristMotorID);
        WristMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        // IntakeWristPiston = new DoubleSolenoid(Constants.kIntakeWristForwardID, Constants.kIntakeWristReverseID);
        // IntakeWristPiston.setSubsystem("Intake");

        // IntakeHatchPiston = new DoubleSolenoid(Constants.kIntakeHatchPistonForwardID, Constants.kIntakeHatchPistonReverseID);
        // IntakeHatchPiston.setSubsystem("Intake");
        // #endregion

        // #region Climb
        
        ClimbMotor = new VictorSP(Constants.kClimbMotorID);
        // ClimbMotor.setInverted(true);
        ClimbMotor.setSubsystem("Climb");

        // Stage 1 - Should fire first
        ClimbLowerPiston = new DoubleSolenoid(Constants.kClimbLowerForwardID, Constants.kClimbLowerReverseID);
        ClimbLowerPiston.setSubsystem("Climb");

        // Stage 2 - Should fire Second
        ClimbUpperPiston = new DoubleSolenoid(Constants.kClimbUpperForwardID, Constants.kClimbUpperReverseID);
        ClimbUpperPiston.setSubsystem("Climb");
        
        // #endregion Climb
    }
}