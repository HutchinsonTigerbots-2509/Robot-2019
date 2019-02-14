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

    public static double kSpoolDiam = 4;//inches we need
    public static double kPulsesPerRotation = 256;
    public static double kElevatorTicksPerInch = 20.3;
    public static double kElevatorMaxSpeed = 0.5;
    public static double kElevatorSensitivity = 0.5;
    public static int kEncoderErrorRange = 2; // 5?
    public static double kMaxElevatorSpeed =0.9;

    public static double kMaxHieght = 72;//All of these are hieghts off the ground inches
    public static double kMidHieght = 36;
    public static double kMinHieght = 10;
    public static double kHomePositionInches = 5;

    public static double kHatchLow = 20.0;
    public static double kHatchMid = 47.5;
    public static double kHatchHigh = 75.0;
    public static double kBallLow = 28.5;
    public static double kBallMid = 56.0;
    public static double kBallHigh = 83.0;

    public static int kElevatorMasterID = 4;
    public static int kElevatorSlaveID = 5;

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


    //#region RobotMap Constants

    // Do not change anything after this line unless you rewire the robot and
    // update the spreadsheet!
    // Port assignments should match up with the spreadsheet here:
    // https://docs.google.com/spreadsheets/d/1FEBEgIgFHLcY4xUZjEkiHl1moupbKuoPvh55APKpakg/edit?usp=sharing

    /* Motor Contoller ID */
    // Drivetrain
    public static int kDrivetrainLeftMasterID = 0;
    public static int kDrivetrainLeftSlaveID = 1;
    public static int kDrivetrainRightMasterID = 2;
    public static int kDrivetrainRightSlaveID = 3;
    // Encoder ID
    public static int kDrivetrainEncoderLeftAID = 0;
    public static int kDrivetrainEncoderLeftBID = 1;
    public static int kDrivetrainEncoderRightAID = 2;
    public static int kDrivetrianEncoderRightBID = 3;

    // Climb
    public static int kClimbMotorID = 6;
    public static int kClimbLeftHigherID = 6;
    public static int kClimbLeftLowerID = 8;
    public static int kClimbRightHigherID = 7;
    public static int kClimbRightLowerID = 9;
    
    // Intake
    // public static int kIntakeRightMotorID = 4;
    // public static int kIntakeLeftMotorID = 5;
    public static int kIntakeMotorID = 4;

    /* DigitalInput ID */
    // public static int kDrivetrainEncoderLeftAID = 0;
    // public static int kDrivetrainEncoderLeftBID = 1;
    // public static int kDrivetrainEncoderRightAID = 2;
    // public static int kDrivetrianEncoderRightBID = 3;
    public static int kElevatorLeftLimitID = 0;
    public static int kElevatorRightLimitID = 1;


    /* Pneumatics ID */
    // Drivetrain
    public static int kDrivetrainShifterForwardID = 0;
    public static int kDrivetrainShifterReverseID = 1;
    
    // Elevator
    public static int kElevatorShifterForwardID = 0;
    public static int kElevatorShifterReverseID = 1;
    
    // Intake
    public static int kIntakeWristForwardID = 2;
    public static int kIntakeWristReverseID = 3;
    public static int kIntakeHatchPistonForwardID = 6;
    public static int kIntakeHatchPistonReverseID = 7;

    // Climb
     // High 
     public static int kClimbHighForwardID = 2;
     public static int kClimbHighReverseID = 3;
     // Low
     public static int kClimbLowForwardID = 4;
     public static int kClimbLowReverseID = 5;
}
