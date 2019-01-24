package frc.robot; // package declartion

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup; 
import edu.wpi.first.wpilibj.drive.DifferentialDrive; 

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    /* DRIVETRAIN SUBSYSTEM */
    // Motors - The encoder is included with in these (look below in init())
    public static WPI_TalonSRX DrivetrainLeftMaster; 
    public static VictorSPX DrivetrainLeftSlave; 
    public static WPI_TalonSRX DrivetrainRightMaster; 
    public static VictorSPX DrivetrainRightSlave; 

    // Speed Controller Groups
    public static SpeedControllerGroup DrivetrainLeft; 
    public static SpeedControllerGroup DrivetrainRight; 
    
    // Drive Varible - Call this with .tankDrive or .arcadeDrive
    public static DifferentialDrive DrivetrainDifferential;
    
    // Sensors
    public static AHRS Drivetrain_Gyro;

public static void init() {
    //#region DriveTrain

    DrivetrainLeftMaster = new WPI_TalonSRX(Constants.kDrivetrainLeftMasterID); // Front Left Motor
    DrivetrainLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder); // The Encoder

    DrivetrainLeftSlave = new VictorSPX(Constants.kDrivetrainLeftSlaveID);  // Rear Left Motor
    DrivetrainLeftSlave.follow(DrivetrainLeftMaster); // Follow Your Master (Above)

    DrivetrainRightMaster = new WPI_TalonSRX(Constants.kDrivetrainRightMasterID); // Front Right Motor
    DrivetrainRightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder); // The Encoder

    DrivetrainRightSlave = new VictorSPX(Constants.kDrivetrainRightSlaveID); // Rear Right Motor
    DrivetrainRightSlave.follow(DrivetrainRightMaster); // Follow Your Master (Above)

    DrivetrainDifferential = new DifferentialDrive(DrivetrainLeftMaster, DrivetrainRightMaster); // Drive Varible
    
    // #endregion Drivetrain

    //#region Sensors

    Drivetrain_Gyro = new AHRS(SPI.Port.kMXP);
    
    //#endregion Sensors
    }
}
