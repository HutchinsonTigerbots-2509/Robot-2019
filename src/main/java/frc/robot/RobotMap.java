package frc.robot; // package declartion


// importsS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
    /**
     * DriveTrain Varibles
     *                           Front 
     *   DriveTrainLeft   |----------------| DriveTrain Right
     *    DTLeft1         |----------------| DTRight1
     *                    |----------------|
     *                    |----------------|
     *                    |----------------|
     *    DTLeft2         |----------------| DTRight2
     *                           Back
     */
    
    public static WPI_TalonSRX DrivetrainLeftMaster;
    public static WPI_TalonSRX DrivetrainLeftSlave;
    public static WPI_TalonSRX DrivetrainRightMaster;
    public static WPI_TalonSRX DrivetrainRightSlave;
    public static SpeedControllerGroup RightDrivetrain;
    public static SpeedControllerGroup LeftDriveTrain;
    public static DifferentialDrive RobotDrive;
    public static Encoder DrivetrainLeftEncoder;
    public static Encoder DrivetrainRightEncoder;

    public static void init()
    {
        //#region DriveTrain
        DrivetrainLeftMaster = new WPI_TalonSRX(Constants.kDrivetrainLeftMasterID);

        DrivetrainLeftSlave = new WPI_TalonSRX(Constants.kDrivetrainLeftSlaveID);
        //DrivetrainLeftSlave.follow(DrivetrainLeftMaster);

        DrivetrainRightMaster = new WPI_TalonSRX(Constants.kDrivetrainRightMasterID);

        DrivetrainRightSlave = new WPI_TalonSRX(Constants.kDrivetrainRightSlave);
        //DrivetrainRightSlave.follow(DrivetrainRightMaster);

        RightDrivetrain = new SpeedControllerGroup(DrivetrainLeftMaster, DrivetrainRightSlave);

        LeftDriveTrain = new SpeedControllerGroup(DrivetrainLeftMaster, DrivetrainLeftSlave);

        RobotDrive = new DifferentialDrive(LeftDriveTrain, RightDrivetrain);

        DrivetrainLeftEncoder = new Encoder(
            Constants.kDrivetrainEncoderLeftAID, Constants.kDrivetrainEncoderLeftBID);

        DrivetrainRightEncoder = new Encoder(
            Constants.kDrivetrainEncoderRightAID, Constants.kDrivetrianEncoderRightBID);
        
        //#endregion
    }

}
