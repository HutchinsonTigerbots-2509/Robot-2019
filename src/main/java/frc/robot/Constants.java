package frc.robot;// package declaration
/**
 * Constants is the class where the varibles that we use accross code are
 * stored. An example of this would be a max speed. You would store the max
 * speed double here, and then use it in the subsystem/command that it needs to
 * be called.
 * 
 * @author Nate C
 */
public final class Constants {
    public Constants() {
    }

    public static double kDriveSpeedMulti = 0.25;
    
    //#region Constant Values
    /* Motors */
    public static double kMaxSpeed = 0.95;
    public static double kSlowSpeed = 0.45; // IDK but sure
    public static double kReverseFastSpeed = -kMaxSpeed;
    public static double kReverseSlowSpeed = -kSlowSpeed;
    public static double minMoveSpeed = 0.1; // For OperatorDrive void in Drivetrain.java
    public static double kTurnSpeed = 0.20;
    public static double kTargetFollowSpeed = 0.2;
    public static double kNeutralDeadband = 0.04;
    /* Encoders */
    public static double kPulsesPerRevoultuion = 256;

    /* Drivetrain */    
    // Wheel Info
    public static double kWheelDiameter = 6;
    public static double kEncoderRatio = 3;

    // OP Drive Constants
    public static double minMovePercent = 0.1; // min input from joystick
    public static double PostiveInputMulti = 0.8; // multi of joystick input
    public static double NegativeInputMulti = -0.8;
    // PID
    public static double kDrivetrainAimToTargetP = 1;
    public static double kDrivetrainAimToTargetI = 0;
    public static double kDrivetrainAimToTargetD = 0;

    public static double kDriveTrainDistanceP = 0.3;
    public static double kDriveTrainDistanceI = 0.0;
    public static double kDriveTrainDistanceD = 0.0;

    public static double kDriveTrainGyroTurnP = 0.13;
    public static double kDriveTrainGyroTurnD = 0.275;

    /* Elevator */
    public static double kElevatorPGain = 0.15;
    public static double kElevatorIGain = 0.0;
    public static double kElevatorDGain = 4.0;

    public static double kWristPGain = 10;
    public static double kWristIGain = 0.0;
    public static double kWristDGain = 4.0;

    public static double kBottomLimitDistanceFromGround;
    public static double kTopLimitDistanceFromGround;

    public static double kSpoolDiam = 2;
    public static double kPulsesPerRotation = 256;
    public static double kElevatorHighGearTicksPerInch =    61.76330098;//215
    public static double kElevatorLowGearTicksPerInch =     48.35555555;
    public static double kElevatorMaxSpeed = 1;
    public static double kElevatorSensitivity = 0.5;
    public static double kElevatorMinSpeedUp = 1;
    public static double kElevatorMinSpeedDown = -1;
    public static int kEncoderErrorRange = 2; // 5?

    public static double kMaxHeight = 78;//All of these are heights off the ground inches
    public static double kMinHeight = 7;
    public static int kElevatorStartingHeight = 19;
    public static double kHomePositionInches = 7.5;
    public static double kHomePostionFromLowestPostion = 2.5;
    public static long kElevatorStartingHeightConversion = Math.round(kElevatorHighGearTicksPerInch*(kElevatorStartingHeight-kHomePositionInches));
    public static int kElevatorStartingHeightTicks = (int)kElevatorStartingHeightConversion;

    public static double kHatchLow = 14.0; //0
    public static double kHatchMid = 42.0;
    
    public static double kHatchHigh = 68.0;
    public static double kBallLow = 25.0;
    public static double kBallMid = 51.0;
    public static double kBallHigh = 78;
    public static double kHABHeight = 28;
    public static double kCargoShipBall = 40.0;

    /* Vision */
    // Physical
    public static double kCameraHeight = 4;
    //public static double kCameraAngle = -28.23744554;
    public static double kCameraAngle = -31.47286489;
    public static double kTargetHeight = 31.5;
    public static double KpAim = -0.02;
    public static double KpDistance = -0.05;
    public static double min_aim_command = -0.5;
    public static double distance_command = -0.5;
    public static double kTargetDistanceFromTarget = 24;
    /* Limelight */
    // Network
    public static String kLimelightIP = "10.25.9.11";
    public static String kLimelightNetworkID = "limelight";
    // Settings
    public static int kLimelightLED = 0;
    public static int kLimelightMode = 0;
    public static int kLimelightStream = 0;
    // Table IDs
    public static String kLimelightLatencyID = "tl";
    public static String kLimelightTargetID = "tv";
    public static String kLimelightTargetXID = "tx";
    public static String kLimelightTargetYID = "ty";
    public static String kLimelightTargetAreaID = "ta";
    public static String kLimelightTargetSkewID = "ts";
    public static String kLimelightTargetvert = "tvert";
    public static String kLimelightTargethorID = "thor";
    //#endregion
    
    /* Intake */
    public static double kWristTicksPerDegree  = 22.755555555;
    public static int kWristStartingAngle = 20;
    public static int kWristGroundAngle = -87;//-90
    public static int kWristHatchAngle = -45;
    public static int kWristCargoAngle = -75;


    //#region RobotMap Constants

    // Do not change anything after this line unless you rewire the robot and
    // update the spreadsheet!ifer
    // Port assignments should match up with the spreadsheet here:
    // https://docs.google.com/spreadsheets/d/1FEBEgIgFHLcY4xUZjEkiHl1moupbKuoPvh55APKpakg/edit?usp=sharing

    /* Motor Contoller ID */
    // Drivetrain
    public static int kDrivetrainLeftMasterID = 0;
    public static int kDrivetrainLeftSlaveID = 1;
    public static int kDrivetrainRightMasterID = 2;
    public static int kDrivetrainRightSlaveID = 3;
    // Elevator
    public static int kElevatorMasterID = 4;
    // Climb
    public static int kClimbMotorID = 0;
    
    // Intake
    public static int kIntakeMotorID = 1;
    public static int kWristMotorID = 5;

    /* DigitalInput ID */
    public static int kElevatorTopLimitID = 0;
    // public static int kElevatorBottomLimitID = 1;

    /* Pneumatics ID */
    // Drivetrain
    public static int kDrivetrainShifterForwardID = 0;
    public static int kDrivetrainShifterReverseID = 1;
    // Elevator
    public static int kElevatorShifterForwardID = 2;
    public static int kElevatorShifterReverseID = 3;
    // Climb
    public static int kClimbUpperForwardID = 4;
    public static int kClimbUpperReverseID = 5;
    public static int kClimbLowerForwardID = 7;
    public static int kClimbLowerReverseID = 6;
}
