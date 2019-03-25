package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.VictorSP;
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

    /* CLIMB */
    // Motors
    public static VictorSP ClimbMotor;
    // Pneumatics
    public static DoubleSolenoid ClimbUpperPiston;
    public static DoubleSolenoid ClimbLowerPiston;
    public static DoubleSolenoid ClimbFrontPistons;
    public static DoubleSolenoid WristLockPistons;


    public static void init() {

        DrivetrainLeftMaster = new WPI_TalonSRX(Constants.kDrivetrainLeftMasterID); // Front Left Motor
        DrivetrainLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder); // The DT Encoder
        DrivetrainLeftMaster.setInverted(false); // Tell the motor that it isn't inverted (backwards)
        DrivetrainLeftMaster.configNeutralDeadband(Constants.kNeutralDeadband, 0);
        DrivetrainLeftMaster.configPeakOutputForward(Constants.kMaxSpeed);
        DrivetrainLeftMaster.configPeakOutputReverse(-Constants.kMaxSpeed);
        DrivetrainLeftMaster.setSubsystem("Drivetrain");
        DrivetrainLeftMaster.setNeutralMode(NeutralMode.Brake);

        DrivetrainLeftSlave = new WPI_VictorSPX(Constants.kDrivetrainLeftSlaveID); // Rear Left Motor
        // DrivetrainLeftSlave.follow(DrivetrainLeftMaster); // Follow Your Master (Above)
        DrivetrainLeftSlave.setInverted(InvertType.FollowMaster); // Follow Your Master (Above)
        DrivetrainLeftSlave.configNeutralDeadband(Constants.kNeutralDeadband, 0);
        DrivetrainLeftSlave.configPeakOutputForward(Constants.kMaxSpeed);
        DrivetrainLeftSlave.configPeakOutputReverse(-Constants.kMaxSpeed);
        DrivetrainLeftSlave.setSubsystem("Drivetrain");
        DrivetrainLeftSlave.setNeutralMode(NeutralMode.Brake);

        DrivetrainRightMaster = new WPI_TalonSRX(Constants.kDrivetrainRightMasterID); // Front Right Motor
        DrivetrainRightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder); // The Encoder
        DrivetrainRightMaster.setInverted(false); // Inverted is false
        DrivetrainRightMaster.configNeutralDeadband(Constants.kNeutralDeadband, 0); // Sets the motor's deadband (above)
        DrivetrainRightMaster.configPeakOutputForward(Constants.kMaxSpeed);
        DrivetrainRightMaster.configPeakOutputReverse(-Constants.kMaxSpeed);
        DrivetrainRightMaster.setNeutralMode(NeutralMode.Brake);
        DrivetrainRightMaster.setSubsystem("Drivetrain");

        DrivetrainRightSlave = new WPI_VictorSPX(Constants.kDrivetrainRightSlaveID); // Rear Right Motor
        // DrivetrainRightSlave.follow(DrivetrainRightMaster); // Follow Your Master (Above)
        DrivetrainRightSlave.setInverted(InvertType.FollowMaster); // Follow Your Master
        DrivetrainRightSlave.configNeutralDeadband(Constants.kNeutralDeadband, 0); // Sets the motor's deadband (above)
        DrivetrainRightSlave.configPeakOutputForward(Constants.kMaxSpeed);
        DrivetrainRightSlave.configPeakOutputReverse(-Constants.kMaxSpeed);
        DrivetrainRightSlave.setNeutralMode(NeutralMode.Brake);
        DrivetrainRightSlave.setSubsystem("Drivetrain");

        SpeedControllerGroup leftDrive = new SpeedControllerGroup(DrivetrainLeftMaster, DrivetrainLeftSlave);
        SpeedControllerGroup rightDrive = new SpeedControllerGroup(DrivetrainRightMaster, DrivetrainRightSlave);

        DrivetrainDifferential = new DifferentialDrive(leftDrive, rightDrive); // Drive Varible
        DrivetrainDifferential.setDeadband(Constants.kNeutralDeadband);
        DrivetrainDifferential.setMaxOutput(Constants.kMaxSpeed);
        DrivetrainDifferential.setSubsystem("Drivetrain");

        DrivetrainShifter = new DoubleSolenoid(0,Constants.kDrivetrainShifterForwardID,Constants.kDrivetrainShifterReverseID);
        DrivetrainShifter.setSubsystem("Drivetrain");

        DrivetrainGyro = new AHRS(SPI.Port.kMXP);
        DrivetrainGyro.setSubsystem("Drivetrain");
        // #endregion

        // #region Elevator
        ElevatorMotorMaster = new WPI_TalonSRX(Constants.kElevatorMasterID);
        ElevatorMotorMaster.setSubsystem("Elevator");
        ElevatorMotorMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        ElevatorMotorMaster.setInverted(true);//True //Possibly Differnt between comp and practice
        ElevatorMotorMaster.configPeakOutputForward(Constants.kElevatorMaxSpeed);
        ElevatorMotorMaster.configPeakOutputReverse(-Constants.kElevatorMaxSpeed);
        ElevatorMotorMaster.configNominalOutputForward(Constants.kElevatorMinSpeedUp);
        ElevatorMotorMaster.configNominalOutputReverse(Constants.kElevatorMinSpeedDown);
        ElevatorMotorMaster.setSensorPhase(false);
        ElevatorMotorMaster.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        ElevatorMotorMaster.config_kD(0, Constants.kElevatorDGain);
        ElevatorMotorMaster.config_kP(0, Constants.kElevatorPGain);
        ElevatorMotorMaster.config_kI(0, Constants.kElevatorIGain);
        ElevatorMotorMaster.setSelectedSensorPosition(0);
        // ElevatorMotorMaster.setSelectedSensorPosition(Constants.kElevatorStartingHeightTicks);
       
        ElevatorShifter = new DoubleSolenoid(0, Constants.kElevatorShifterForwardID, Constants.kElevatorShifterReverseID);
        ElevatorShifter.setSubsystem("Elevator");

        ElevatorTopLimit = new DigitalInput(Constants.kElevatorTopLimitID);
        ElevatorTopLimit.setSubsystem("Elevator");

        // ElevatorBottomLimit = new DigitalInput(Constants.kElevatorBottomLimitID);
        // ElevatorBottomLimit.setSubsystem("Elevator");
        // #endregion

        // #region Intake
        IntakeMotor = new VictorSP(Constants.kIntakeMotorID);
        IntakeMotor.setSubsystem("Intake");
        
        WristMotor = new WPI_TalonSRX(Constants.kWristMotorID);
        WristMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        WristMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);
        WristMotor.setSensorPhase(true);
        WristMotor.configPeakOutputForward(0.95);
        WristMotor.configPeakOutputReverse(-0.95);
        WristMotor.configNominalOutputForward(0.05);
        WristMotor.configNominalOutputReverse(-0.05);
        WristMotor.setSelectedSensorPosition(0);
        WristMotor.setSubsystem("Intake");
        WristMotor.config_kD(0, Constants.kWristDGain);
        WristMotor.config_kP(0, Constants.kWristPGain);
        WristMotor.config_kI(0, Constants.kWristIGain);
        WristMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
        WristMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
        WristMotor.setNeutralMode(NeutralMode.Brake);
        // #endregion

        // #region Climb
        ClimbMotor = new VictorSP(Constants.kClimbMotorID);
        ClimbMotor.setInverted(true);
        ClimbMotor.setSubsystem("Climb");

        ClimbLowerPiston = new DoubleSolenoid(0,Constants.kClimbLowerForwardID, Constants.kClimbLowerReverseID);
        ClimbLowerPiston.setSubsystem("Climb");

        // Stage 2 - Should fire Second Constants.kClimbUpperForwardID 
        ClimbUpperPiston = new DoubleSolenoid(0,Constants.kClimbUpperForwardID, Constants.kClimbUpperReverseID);
        ClimbUpperPiston.setSubsystem("Climb");
        
        ClimbFrontPistons = new DoubleSolenoid(1, Constants.kClimbFrontForwardID,Constants.kClimbFrontReverseID);
        ClimbFrontPistons.setSubsystem("Climb");

        WristLockPistons = new DoubleSolenoid(1, Constants.kWristLockPistonForwardID,Constants.kWristLockPistonReverseID);
        WristLockPistons.setSubsystem("Intake");
        // #endregion Climb
    }
}