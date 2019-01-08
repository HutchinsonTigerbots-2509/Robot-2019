package frc.robot; // package declartion


// importsS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
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
    
    public static SpeedController DriveTrainLeft1;
    public static SpeedController DriveTrainLeft2;
    public static SpeedController DriveTrainRight1;
    public static WPI_TalonSRX DriveTrainRight2;

    public static SpeedControllerGroup RightDriveTrain;
    public static SpeedControllerGroup LeftDriveTrain;
    
    public static DifferentialDrive Drive;

    public static void init()
    {
        //#region DriveTrain

        // DriveTrainLeft1 = new SpeedController();
        // DriveTrainLeft2 = new SpeedController(1);
        // DriveTrainRight1 = new SpeedController(2);
        // DriveTrainRight2 = new SpeedController(3);

        RightDriveTrain = new SpeedControllerGroup(DriveTrainRight1, DriveTrainRight2);
        LeftDriveTrain = new SpeedControllerGroup(DriveTrainLeft1, DriveTrainLeft2);

        Drive = new DifferentialDrive(LeftDriveTrain, RightDriveTrain);
        //#endregion
    }

}
