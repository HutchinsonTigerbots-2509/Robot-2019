package frc.robot; // package declartion

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @see Constants.java region RobotMap Constants
 */
public class RobotMap {
    /* Varible Declarations */
    // Drivetrain
    public static WPI_TalonSRX DrivetrainLeftMaster; 
    public static WPI_TalonSRX DrivetrainLeftSlave; 
    public static WPI_TalonSRX DrivetrainRightMaster; 
    public static WPI_TalonSRX DrivetrainRightSlave; 
    
    public static Encoder DrivetrainLeftEncoder; 
    public static Encoder DrivetrainRightEncoder; 
    
    public static SpeedControllerGroup DrivetrainLeft; 
    public static SpeedControllerGroup DrivetrainRight; 
   
    public static DifferentialDrive DrivetrainDifferential;

    // Intake
    public static VictorSP IntakeRightMotor;
    public static VictorSP IntakeLeftMotor;

    public static SpeedControllerGroup IntakeMotors;
    
    public static DoubleSolenoid IntakeLeftPiston;
    public static DoubleSolenoid IntakeWristPiston;
    public static DoubleSolenoid OpenIntakePiston;

public static void init() {
    //#region DriveTrain
    DrivetrainLeftMaster = new WPI_TalonSRX(Constants.kDrivetrainLeftMasterID); // Both Fronts
    DrivetrainLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

    DrivetrainLeftSlave = new WPI_TalonSRX(Constants.kDrivetrainLeftSlaveID); 
    DrivetrainLeftSlave.follow(DrivetrainLeftMaster);

    DrivetrainRightMaster = new WPI_TalonSRX(Constants.kDrivetrainRightMasterID); // Both Fronts
    DrivetrainRightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

    DrivetrainRightSlave = new WPI_TalonSRX(Constants.kDrivetrainRightSlaveID); 
    DrivetrainRightSlave.follow(DrivetrainRightMaster);

    DrivetrainLeftEncoder = new Encoder(Constants.kDrivetrainEncoderLeftAID, Constants.kDrivetrainEncoderLeftBID); 

    DrivetrainRightEncoder = new Encoder(Constants.kDrivetrainEncoderRightAID, Constants.kDrivetrianEncoderRightBID); 

    DrivetrainLeft = new SpeedControllerGroup(DrivetrainLeftMaster, DrivetrainLeftSlave); 
    DrivetrainRight = new SpeedControllerGroup(DrivetrainRightMaster, DrivetrainRightSlave); 

    DrivetrainDifferential = new DifferentialDrive(DrivetrainLeft, DrivetrainRight); 
    // #endregion

    // #region Intake
    IntakeRightMotor = new VictorSP(Constants.kIntakeRightMotorID);
    IntakeLeftMotor = new VictorSP(Constants.kIntakeLeftMotorID);

    IntakeMotors = new SpeedControllerGroup(IntakeRightMotor, IntakeLeftMotor);
    
    IntakeLeftPiston = new DoubleSolenoid(Constants.kIntakeLeftPistonForwardID , Constants.kIntakeLeftPistonReverseID);
    
    IntakeWristPiston = new DoubleSolenoid(Constants.kIntakeWristPistonForwardID , Constants.kIntakeWristPistonReverseID);
    
    OpenIntakePiston = new DoubleSolenoid(Constants.kOpenIntakePistonForwardID , Constants.kOpenIntakePistonReverseID);
    //#endregion
    
    }
}
