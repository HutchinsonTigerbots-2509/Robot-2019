package frc.robot;// package

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

    /* Motors */
    public static double kMaxSpeed = 0.95;
    public static double kSlowSpeed = 0.45; // IDK but sure
    public static double kTurnSpeed = 0.20;
    public static double kWheelDiameter = 6;
    public static double kTargetFollowSpeed = 0.2;
    public static double kDrivetrainP = 1;
    public static double kDrivetrainI = 0;
    public static double kDrivetrainD = 0;

    /* Elevator */
    public static double kElevatorP = 1;
    public static double kElevatorI = 0;
    public static double kElevatorD = 0;

    /*V ision */
    // Physical
    public static double kCameraHeight = 24;
    public static double kCameraAngle = 1.7;
    public static double kTargetHeight = 31.5;
    public static double KpAim = -0.008;
    public static double KpDistance = -0.07;
    public static double min_aim_command = -0.5;
    public static double distance_command = -0.3;
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

    // Do not change anything after this line unless you rewire the robot and
    // update the spreadsheet!
    // Port assignments should match up with the spreadsheet here:
    // https://docs.google.com/spreadsheets/d/1FEBEgIgFHLcY4xUZjEkiHl1moupbKuoPvh55APKpakg/edit?usp=sharing

    // Motor Contoller ID
    public static int kDrivetrainLeftMasterID = 4;
    public static int kDrivetrainLeftSlaveID = 5;
    public static int kDrivetrainRightMasterID = 2;
    public static int kDrivetrainRightSlaveID = 1;
    // Encoder ID
    public static int kDrivetrainEncoderLeftAID = 0;
    public static int kDrivetrainEncoderLeftBID = 1;
    public static int kDrivetrainEncoderRightAID = 2;
    public static int kDrivetrianEncoderRightBID = 3;
}