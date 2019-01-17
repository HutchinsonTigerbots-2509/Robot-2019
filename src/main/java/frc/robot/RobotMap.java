package frc.robot; // package declartion

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
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
/**
     * DriveTrain Varibles 
     * Front DriveTrainLeft |----------------| DriveTrain Right
     * kDT_LFront |----------------| kDT_RFront |----------------|
     * |----------------| |----------------| kDT_LRear |----------------| kDT_RRear
     * Back
     */

public static WPI_TalonSRX DrivetrainLeftMaster; 
public static WPI_TalonSRX DrivetrainLeftSlave; 
public static WPI_TalonSRX DrivetrainRightMaster; 
public static WPI_TalonSRX DrivetrainRightSlave; 
public static Encoder DrivetrainLeftEncoder; 
public static Encoder DrivetrainRightEncoder; 
public static SpeedControllerGroup DrivetrainLeft; 
public static SpeedControllerGroup DrivetrainRight; 
public static DifferentialDrive DrivetrainDifferential; 

public static void init() {
    //#region DriveTrain
    
    // https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/blob/master/Java/DifferentialDrive/src/main/java/frc/robot/Robot.java
    /**
     * According to the link above, the VictorSPXs can be controlled by using this
     * method. It works like this: You make a Main Motor and a Follower Motor in
     * place of a Speed Controller Group.
     */

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
    }
}
