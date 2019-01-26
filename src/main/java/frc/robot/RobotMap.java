package frc.robot; // package declartion

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.kauailabs.navx.frc.AHRS;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @see Constants.java region RobotMap Constants
 */
public class RobotMap {

    /* DRIVETRAIN SUBSYSTEM */
    
    // Motors - The encoder is included with in these (look below in init())
    public static WPI_TalonSRX DrivetrainLeftMaster; 
    public static VictorSPX DrivetrainLeftSlave; 
    public static WPI_TalonSRX DrivetrainRightMaster; 
    public static VictorSPX DrivetrainRightSlave; 
    
    // Drive Varible - It uses master motors instead of SpeedControllerGroups
    public static DifferentialDrive DrivetrainDifferential;
    
    // Sensors
    public static AHRS Drivetrain_Gyro;
    
    //Elevator
    public static SpeedControllerGroup LiftTrain;
    public static WPI_TalonSRX Right_Lift;
    public static WPI_TalonSRX Left_Lift;
    public static Encoder  ElevatorEncoder;
    public static WPI_TalonSRX RightSpoolMaster; 
    public static VictorSPX LeftSpoolSlave; 

    // Intake
    public static VictorSP IntakeMotor;
    public static DoubleSolenoid IntakeGrip;
    public static DoubleSolenoid IntakeWristPiston;

    public static void init() {
        //#region DriveTrain
        DrivetrainLeftMaster = new WPI_TalonSRX(Constants.kDrivetrainLeftMasterID);
        DrivetrainLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

        DrivetrainLeftSlave = new VictorSPX(Constants.kDrivetrainLeftSlaveID); // Follows LeftMaster
        DrivetrainLeftSlave.follow(DrivetrainLeftMaster);

        DrivetrainRightMaster = new WPI_TalonSRX(Constants.kDrivetrainRightMasterID);
        DrivetrainRightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

        DrivetrainRightSlave = new VictorSPX(Constants.kDrivetrainRightSlaveID); // Follows RightMaster
        DrivetrainRightSlave.follow(DrivetrainRightMaster);

        DrivetrainDifferential = new DifferentialDrive(DrivetrainLeftMaster, DrivetrainRightMaster); 
        // #endregion

        //#region Elevator
        RightSpoolMaster = new WPI_TalonSRX(Constants.kRightSpoolMasterMasterID);
        RightSpoolMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        LeftSpoolSlave = new VictorSPX(Constants.kLeftSpoolSlaveID); 
        LeftSpoolSlave.follow(RightSpoolMaster);
        //#endregion

        // #region Intake
        IntakeMotor = new VictorSP(1);
        IntakeGrip = new DoubleSolenoid(0, 1);
        IntakeWristPiston = new DoubleSolenoid(2, 3);
        //#endregion  
    }
}
