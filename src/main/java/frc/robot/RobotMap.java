package frc.robot; // package declartion

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup; 
import edu.wpi.first.wpilibj.drive.DifferentialDrive; 
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
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

    /* DRIVETRAIN SUBSYSTEM */
    // Motors
    public static WPI_TalonSRX DrivetrainLeftMaster; 
    public static VictorSPX DrivetrainLeftSlave; 
    public static WPI_TalonSRX DrivetrainRightMaster; 
    public static VictorSPX DrivetrainRightSlave; 
    
    // Drive Varible
    public static DifferentialDrive DrivetrainDifferential;
    public static DoubleSolenoid DrivetrainShifter;
    
    // Sensors
    public static AHRS Drivetrain_Gyro;
    
    /* ELEVATOR SUBSYSTEM */
    // Lift Motors
    public static WPI_TalonSRX Right_Lift;
    public static WPI_TalonSRX Left_Lift;
    public static SpeedControllerGroup LiftTrain;
    
    // Spool Motors
    public static WPI_TalonSRX RightSpoolMaster; 
    public static VictorSPX LeftSpoolSlave;

    // Sensors
    public static Encoder  ElevatorEncoder;

    /* INTAKE SUBSYSTEM */
    // Motors
    public static VictorSP IntakeMotor;
    
    // Pistons
    public static DoubleSolenoid IntakeOpenPiston;
    public static DoubleSolenoid IntakeWristPiston;

    public static void init() {
        //#region DriveTrain
        // LEFT
        DrivetrainLeftMaster = new WPI_TalonSRX(Constants.kDrivetrainLeftMasterID);
        DrivetrainLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

        DrivetrainLeftSlave = new VictorSPX(Constants.kDrivetrainLeftSlaveID); // Follows LeftMaster
        DrivetrainLeftSlave.follow(DrivetrainLeftMaster);

        // RIGHT
        DrivetrainRightMaster = new WPI_TalonSRX(Constants.kDrivetrainRightMasterID);
        DrivetrainRightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

        DrivetrainRightSlave = new VictorSPX(Constants.kDrivetrainRightSlaveID); // Follows RightMaster
        DrivetrainRightSlave.follow(DrivetrainRightMaster);

        // A Master is used as a SpeedControllerGroup in this case. This allows us to use
        // the VictorSPX datatype for motors. However, the masters must still be Talons.
        // NOTE: The Masters contain the encoders for the Drivetrain
        DrivetrainDifferential = new DifferentialDrive(DrivetrainLeftMaster, DrivetrainRightMaster);
        DrivetrainShifter = new DoubleSolenoid(0, 1);
        
        Drivetrain_Gyro = new AHRS(SPI.Port.kMXP);
        // #endregion

        //#region Elevator
        RightSpoolMaster = new WPI_TalonSRX(Constants.kRightSpoolMasterMasterID);
        RightSpoolMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        LeftSpoolSlave = new VictorSPX(Constants.kLeftSpoolSlaveID); 
        LeftSpoolSlave.follow(RightSpoolMaster);
        //#endregion

        // #region Intake
        IntakeMotor = new VictorSP(1);
        IntakeOpenPiston = new DoubleSolenoid(0, 1);
        IntakeWristPiston = new DoubleSolenoid(2, 3);
        //#endregion  
    }
}
