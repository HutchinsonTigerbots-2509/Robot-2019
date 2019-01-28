package frc.robot; // package declartion

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
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
    // Double Solenoids
    public static DoubleSolenoid DrivetrainShifter;
    // Speed Controller Groups
    public static SpeedControllerGroup DrivetrainLeft;
    public static SpeedControllerGroup DrivetrainRight;
    // Drive Varible - Call this with .tankDrive or .arcadeDrive
    public static DifferentialDrive DrivetrainDifferential;

    // Sensors
    public static AHRS Drivetrain_Gyro;

    public static void init() {
        // #region DriveTrain

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

        DrivetrainDifferential = new DifferentialDrive(DrivetrainLeftMaster, DrivetrainRightMaster); // Drive Varible

        DrivetrainShifter = new DoubleSolenoid(forwardChannel, reverseChannel)

        // #endregion Drivetrain

        // #region Sensors

        Drivetrain_Gyro = new AHRS(SPI.Port.kMXP);

        // #endregion Sensors
    }
}
