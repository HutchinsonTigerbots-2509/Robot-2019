package frc.robot; // package declartion

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Encoder;
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
    // Motors - The encoder is included with in these (look below in init())
    public static WPI_TalonSRX DrivetrainLeftMaster;
    public static VictorSPX DrivetrainLeftSlave;
    public static WPI_TalonSRX DrivetrainRightMaster;
    public static VictorSPX DrivetrainRightSlave;
    // Double Solenoids
    public static DoubleSolenoid DrivetrainShifter;
    // Speed Controller Groups
    public static SpeedControllerGroup DrivetrainLeft;
    public static SpeedControllerGroup DrivetrainRight;
    // Drive Varible - Call this with .tankDrive or .arcadeDrive
    public static DifferentialDrive DrivetrainDifferential;

    // Sensors
    public static AHRS DrivetrainGyro;
    
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

        DrivetrainLeftMaster = new WPI_TalonSRX(Constants.kDrivetrainLeftMasterID); // Front Left Motor
        DrivetrainLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder); // The Encoder
        DrivetrainLeftMaster.setInverted(false);
        DrivetrainLeftMaster.configNeutralDeadband(Constants.kNeutralDeadband, 0);

        DrivetrainLeftSlave = new VictorSPX(Constants.kDrivetrainLeftSlaveID); // Rear Left Motor
        DrivetrainLeftSlave.follow(DrivetrainLeftMaster); // Follow Your Master (Above)
        DrivetrainLeftSlave.setInverted(InvertType.FollowMaster);
        DrivetrainLeftSlave.configNeutralDeadband(Constants.kNeutralDeadband, 0);

        DrivetrainRightMaster = new WPI_TalonSRX(Constants.kDrivetrainRightMasterID); // Front Right Motor
        DrivetrainRightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder); // The Encoder
        DrivetrainRightMaster.setInverted(false);
        DrivetrainRightMaster.configNeutralDeadband(Constants.kNeutralDeadband, 0);

        DrivetrainRightSlave = new VictorSPX(Constants.kDrivetrainRightSlaveID); // Rear Right Motor
        DrivetrainRightSlave.follow(DrivetrainRightMaster); // Follow Your Master (Above)
        DrivetrainRightSlave.setInverted(InvertType.FollowMaster);
        DrivetrainRightSlave.configNeutralDeadband(Constants.kNeutralDeadband, 0);

        // A Master is used as a SpeedControllerGroup in this case. This allows us to use
        // the VictorSPX datatype for motors. However, the masters must still be Talons.
        // NOTE: The Masters contain the encoders for the Drivetrain
        DrivetrainDifferential = new DifferentialDrive(DrivetrainLeftMaster, DrivetrainRightMaster); // Drive Varible

        DrivetrainShifter = new DoubleSolenoid(Constants.kDrivetrainShifterForwardID, Constants.kDrivetrainShifterReverseID);
        
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

        // #region Sensors

        DrivetrainGyro = new AHRS(SPI.Port.kMXP);

        // #endregion Sensors
    }
}
