package frc.robot; // package declartion

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup; 
import edu.wpi.first.wpilibj.drive.DifferentialDrive; 

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    // varible declarations \/

    // DriveTrain
    public static WPI_TalonSRX DrivetrainLeftMaster; 
    public static VictorSPX DrivetrainLeftSlave; 
    public static WPI_TalonSRX DrivetrainRightMaster; 
    public static VictorSPX DrivetrainRightSlave; 

    public static SpeedControllerGroup DrivetrainLeft; 
    public static SpeedControllerGroup DrivetrainRight; 
    
    public static DifferentialDrive DrivetrainDifferential;
    
    // Sensors
    // public static Encoder DrivetrainLeftEncoder; 
    // public static Encoder DrivetrainRightEncoder;
    public static AHRS Gyro;

public static void init() {
    //#region DriveTrain

    DrivetrainLeftMaster = new WPI_TalonSRX(Constants.kDrivetrainLeftMasterID); // Front Left Motor
    DrivetrainLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

    DrivetrainLeftSlave = new VictorSPX(Constants.kDrivetrainLeftSlaveID);  // Rear Left Motor
    DrivetrainLeftSlave.follow(DrivetrainLeftMaster);

    DrivetrainRightMaster = new WPI_TalonSRX(Constants.kDrivetrainRightMasterID); // Front Right Motor
    DrivetrainRightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

    DrivetrainRightSlave = new VictorSPX(Constants.kDrivetrainRightSlaveID); // Rear Right Motor
    DrivetrainRightSlave.follow(DrivetrainRightMaster);

    DrivetrainDifferential = new DifferentialDrive(DrivetrainLeftMaster, DrivetrainRightMaster); // Drive Varible
    
    // #endregion Drivetrain

    //#region Sensors

    Gyro = new AHRS(SPI.Port.kMXP);
    
    // DrivetrainLeftEncoder = new Encoder(Constants.kDrivetrainEncoderLeftAID, Constants.kDrivetrainEncoderLeftBID); 

    // DrivetrainRightEncoder = new Encoder(Constants.kDrivetrainEncoderRightAID, Constants.kDrivetrianEncoderRightBID); 
    
    //#endregion Sensors
    }
}
