package frc.robot; // package declartion



import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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
     *    kDT_LFront      |----------------| kDT_RFront
     *                    |----------------|
     *                    |----------------|
     *                    |----------------|
     *    kDT_LRear       |----------------| kDT_RRear
     *                           Back
     */
    
    public static WPI_TalonSRX DrivetrainLeftFront;
    public static WPI_TalonSRX DrivetrainLeftRear;
    public static WPI_TalonSRX DrivetrainRightFront;
    public static WPI_TalonSRX DrivetrainRightRear;

    public static SpeedControllerGroup DrivetrainLeft;
    public static SpeedControllerGroup DrivetrainRight;
    
    public static DifferentialDrive  DrivetrainDifferential;

    public static void init()
    {
        //#region DriveTrain

        // https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/blob/master/Java/DifferentialDrive/src/main/java/frc/robot/Robot.java
        
        /**
         * According to the link above, the VictorSPXs can be controlled by using this method.
         * It works like this: You make a Main Motor and a Follower Motor in place of a Speed Controller
         * Group.
         */
        
        DrivetrainLeftFront = new WPI_TalonSRX(Constants.kDrivetrainLeftFrontID); // Both Fronts
        DrivetrainLeftRear = new WPI_TalonSRX(Constants.kDrivetrainLeftRearID);
        //DrivetrainLeftRear.follow(DrivetrainLeftFront);
        DrivetrainRightFront = new WPI_TalonSRX(Constants.kDrivetrainRightFrontID); // Both Fronts
        DrivetrainRightRear = new WPI_TalonSRX(Constants.kDrivetrainRightRearID);
        //DrivetrainRightRear.follow(DrivetrainRightFront);

        DrivetrainLeft = new SpeedControllerGroup(DrivetrainLeftFront,DrivetrainLeftRear);
        DrivetrainRight = new SpeedControllerGroup(DrivetrainRightFront,DrivetrainRightRear);

        DrivetrainDifferential = new DifferentialDrive(DrivetrainLeft, DrivetrainRight);
        //#endregion
    }

}
