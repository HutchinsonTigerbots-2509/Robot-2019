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
/**
     * DriveTrain Varibles 
     * Front DriveTrainLeft |----------------| DriveTrain Right
     * kDT_LFront |----------------| kDT_RFront |----------------|
     * |----------------| |----------------| kDT_LRear |----------------| kDT_RRear
     * Back
     */

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

//Elevator
public static WPI_TalonSRX ElevatorMotorMaster; 
public static WPI_VictorSPX ElevatorMotorSlave;
public static DoubleSolenoid ElevatorShifter;
public static DigitalInput ElevatorLeftLimit;
public static DigitalInput ElevatorRightLimit;


// Intake
public static VictorSP IntakeRightMotor = new VictorSP(4);
public static VictorSP IntakeLeftMotor = new VictorSP(5);

public static SpeedControllerGroup IntakeMotors = new SpeedControllerGroup(IntakeRightMotor, IntakeLeftMotor);

public static DoubleSolenoid IntakeLeftPiston;
public static DoubleSolenoid IntakeWristPiston;
public static DoubleSolenoid IntakeOpenPiston; 

// Sensors
public static AHRS Drivetrain_Gyro;

public static void init() {
    //#region DriveTrain
    DrivetrainLeftMaster = new WPI_TalonSRX(Constants.kDrivetrainLeftMasterID); // Both Fronts
    DrivetrainLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

    DrivetrainLeftSlave = new WPI_VictorSPX(Constants.kDrivetrainLeftSlaveID); 
    DrivetrainLeftSlave.follow(DrivetrainLeftMaster);

    DrivetrainRightMaster = new WPI_TalonSRX(Constants.kDrivetrainRightMasterID); // Both Fronts
    DrivetrainRightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

    DrivetrainRightSlave = new WPI_VictorSPX(Constants.kDrivetrainRightSlaveID); 
    DrivetrainRightSlave.follow(DrivetrainRightMaster);

    //DrivetrainLeftEncoder = new Encoder(Constants.kDrivetrainEncoderLeftAID, Constants.kDrivetrainEncoderLeftBID); 
    //DrivetrainRightEncoder = new Encoder(Constants.kDrivetrainEncoderRightAID, Constants.kDrivetrianEncoderRightBID); 

    DrivetrainLeft = new SpeedControllerGroup(DrivetrainLeftMaster, DrivetrainLeftSlave); 
    DrivetrainRight = new SpeedControllerGroup(DrivetrainRightMaster, DrivetrainRightSlave); 

    DrivetrainDifferential = new DifferentialDrive(DrivetrainLeft, DrivetrainRight); 

    Drivetrain_Gyro = new AHRS(SPI.Port.kMXP);
    // #endregion

    //#region Elevator
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

    //#endregion

    // #region Intake
    IntakeRightMotor = new VictorSP(Constants.kIntakeRightMotorID);
    IntakeLeftMotor = new VictorSP(Constants.kIntakeLeftMotorID);

    IntakeMotors = new SpeedControllerGroup(IntakeRightMotor, IntakeLeftMotor); 
    
    IntakeLeftPiston = new DoubleSolenoid(Constants.kIntakeLeftPistonForwardID,Constants.kIntakeLeftPistonReverseID);
    IntakeOpenPiston = new DoubleSolenoid(Constants.kOpenIntakePistonForwardID, Constants.kOpenIntakePistonReverseID);
    IntakeWristPiston = new DoubleSolenoid(Constants.kIntakeWristPistonForwardID, Constants.kIntakeWristPistonReverseID);   
    // #endregion


    }
}
