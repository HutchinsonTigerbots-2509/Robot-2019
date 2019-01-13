package frc.robot;// package
/**
 * Constants is the class where the varibles that we use accross 
 * code are stored. An example of this would be a max speed.
 * You would store the max speed double here, and then use it
 * in the subsystem/command that it needs to be called.
 * 
 * @author Nate C
 */
public final class Constants{
    public Constants(){}
    /*Motors*/
    public static double kMaxSpeed = 0.95;
    public static double kSlowSpeed = 0.65; // IDK but sure
    public static double kTurnSpeed = 0.57575;
    public static double kWheelDiameter = 6;

    /*Limelight*/
    //Network
   public static String kLimelightIP = "10.25.9.11";
   public static String kLimelightNetworkID = "limelight";
    //Physical 
   public static int kLimelightLED = 0;
   public static int kLimelightMode = 0;
   public static int kLimelightStream = 0;
    //Table IDs
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
    public static int kDrivetrainLeftFrontID = 0;
    public static int kDrivetrainLeftRearID = 1;
    public static int kDrivetrainRightFrontID = 2;
    public static int kDrivetrainRightRearID = 3;
}