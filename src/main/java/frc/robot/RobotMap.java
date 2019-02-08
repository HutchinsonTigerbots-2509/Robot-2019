package frc.robot; // package declartion

// imports

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
 * @see Constants.java region for Port #'s
 */
public class RobotMap {
    /* DRIVETRAIN */
    // Motors
    public static WPI_TalonSRX DrivetrainLeftMaster;
    public static WPI_VictorSPX DrivetrainLeftSlave;
    public static WPI_TalonSRX DrivetrainRightMaster;
    public static WPI_VictorSPX DrivetrainRightSlave;
    
    // Motor Groups
    public static SpeedControllerGroup DrivetrainLeft;
    public static SpeedControllerGroup DrivetrainRight;
    public static DifferentialDrive DrivetrainDifferential;
    
    // Shifter
    public static DoubleSolenoid DrivetrainShifter;
    
    // Sensors
    public static AHRS Drivetrain_Gyro;

    /* ELEVATOR */
    // Motors
    public static WPI_TalonSRX ElevatorMotorMaster;
    public static WPI_VictorSPX ElevatorMotorSlave;
    
    // Pneumatics
    public static DoubleSolenoid ElevatorShifter;
    
    // Digital Input Limits
    public static DigitalInput ElevatorLeftLimit;
    public static DigitalInput ElevatorRightLimit;

    /* INTAKE */
    // Motors
    public static VictorSP IntakeRightMotor ;
    public static VictorSP IntakeLeftMotor;
    public static VictorSP IntakeMotor;
    
    // Motor Groups
    public static SpeedControllerGroup IntakeMotors;
    
    // Pneumatics
    public static DoubleSolenoid IntakePushPiston;
    public static DoubleSolenoid IntakeWristPiston;
    public static DoubleSolenoid IntakeGripPiston;
    public static DoubleSolenoid IntakeHatchPiston;
    public static DoubleSolenoid IntakeOpenPiston;

    /* CLIMB */
    // Motors
    public static WPI_TalonSRX ClimbMotor;

    // Pneumatics
    public static DoubleSolenoid ClimbHighPistons;
    public static DoubleSolenoid ClimbLowPistons;

    public static void init() {
        // #region DriveTrain
        // Deadbands: https://en.wikipedia.org/wiki/Deadband
        // Motors
        DrivetrainLeftMaster = new WPI_TalonSRX(Constants.kDrivetrainLeftMasterID); // Front Left Motor
        DrivetrainLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder); // The DT Encoder
        DrivetrainLeftMaster.setInverted(false); // Tell the motor that it isn't inverted (backwards)
        DrivetrainLeftMaster.configNeutralDeadband(Constants.kNeutralDeadband, 0); // Will set the motor's deadband (above)

        DrivetrainLeftSlave = new WPI_VictorSPX(Constants.kDrivetrainLeftSlaveID); // Rear Left Motor
        DrivetrainLeftSlave.follow(DrivetrainLeftMaster); // Follow Your Master (Above)
        DrivetrainLeftSlave.setInverted(InvertType.FollowMaster); // Follow Your Master (Above)
        DrivetrainLeftSlave.configNeutralDeadband(Constants.kNeutralDeadband, 0); // Will set the motor's deadband (above)

        DrivetrainRightMaster = new WPI_TalonSRX(Constants.kDrivetrainRightMasterID); // Front Right Motor
        DrivetrainRightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder); // The Encoder
        DrivetrainRightMaster.setInverted(false); // Inverted is false
        DrivetrainRightMaster.configNeutralDeadband(Constants.kNeutralDeadband, 0); // Sets the motor's deadband (above)

        DrivetrainRightSlave = new WPI_VictorSPX(Constants.kDrivetrainRightSlaveID); // Rear Right Motor
        DrivetrainRightSlave.follow(DrivetrainRightMaster); // Follow Your Master (Above)
        DrivetrainRightSlave.setInverted(InvertType.FollowMaster); // Follow Your Master
        DrivetrainRightSlave.configNeutralDeadband(Constants.kNeutralDeadband, 0); // Sets the motor's deadband (above)

        // A Master is used as a SpeedControllerGroup in this case. This allows us to
        // use the VictorSPX datatype for motors. However, the masters must still be Talons.
        // NOTE: The Master Motor objects have the encoder linked to them
        DrivetrainDifferential = new DifferentialDrive(DrivetrainLeftMaster, DrivetrainRightMaster); // Drive Varible

        // The shifter will be used to switch between high and low gear
        DrivetrainShifter = new DoubleSolenoid(Constants.kDrivetrainShifterForwardID, Constants.kDrivetrainShifterReverseID);

        // The gyro keeps track of our turning movement along the z axis
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

        ElevatorShifter = new DoubleSolenoid(Constants.kElevatorShifterForwardID, Constants.kIntakeWristReverseID);

        ElevatorLeftLimit = new DigitalInput(Constants.kElevatorLeftLimitID);

        ElevatorRightLimit = new DigitalInput(Constants.kElevatorRightLimitID);
        // #endregion

        // #region Intake
        // IntakeRightMotor = new VictorSP(Constants.kIntakeRightMotorID);
        // IntakeLeftMotor = new VictorSP(Constants.kIntakeLeftMotorID);
        // IntakeMotors = new SpeedControllerGroup(IntakeRightMotor, IntakeLeftMotor);
        IntakeMotor = new VictorSP(Constants.kIntakeMotorID);
        IntakeMotor.setSubsystem("Intake");

        IntakeGripPiston = new DoubleSolenoid(Constants.kIntakeGripPistonForwardID, Constants.kIntakeGripPistonReverseID);
        IntakeGripPiston.setSubsystem("Intake");

        IntakeWristPiston = new DoubleSolenoid(Constants.kIntakeWristForwardID, Constants.kIntakeWristReverseID);
        IntakeWristPiston.setSubsystem("Intake");
        
        IntakeHatchPiston = new DoubleSolenoid(Constants.kIntakeHatchPistonForwardID, Constants.kIntakeHatchPistonReverseID); // 2/2/2019
        IntakeHatchPiston.setSubsystem("Intake");
        // #endregion

        // #region Climb
        
        // Motors
        ClimbMotor = new WPI_TalonSRX(Constants.kClimbMotorID);

        // Pneumatics will work like a master-slave system
        ClimbHighPistons = new DoubleSolenoid(Constants.kClimbHighForwardID, Constants.kClimbHighReverseID);
        ClimbLowPistons = new DoubleSolenoid(Constants.kClimbLowForwardID, Constants.kClimbLowReverseID);
        //#endregion Climb

    }
}