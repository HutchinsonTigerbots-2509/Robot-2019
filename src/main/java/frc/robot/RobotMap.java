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
    
    public static WPI_TalonSRX kDT_LFront;
    public static WPI_TalonSRX kDT_LRear;
    public static WPI_TalonSRX kDT_RFront;
    public static WPI_TalonSRX kDT_RRear;

    public static SpeedControllerGroup kRightDrivetrain;
    public static SpeedControllerGroup kLeftDrivetrain;
    
    public static DifferentialDrive  kDrive;

    public static void init()
    {
        //#region DriveTrain

        // https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/blob/master/Java/DifferentialDrive/src/main/java/frc/robot/Robot.java
        
        /**
         * According to the link above, the VictorSPXs can be controlled by using this method.
         * It works like this: You make a Main Motor and a Follower Motor in place of a Speed Controller
         * Group.
         */
        
        kDT_LFront = new WPI_TalonSRX(0); // Both Fronts
        kDT_LRear = new WPI_TalonSRX(1);
        kDT_RFront = new WPI_TalonSRX(2); // Both Fronts
        kDT_RRear = new WPI_TalonSRX(3);

        kRightDrivetrain = new SpeedControllerGroup(kDT_RFront,kDT_RRear);
        kLeftDrivetrain = new SpeedControllerGroup(kDT_LFront,kDT_LRear);





        kDrive = new DifferentialDrive(kLeftDrivetrain, kRightDrivetrain);
        //#endregion
    }

}
