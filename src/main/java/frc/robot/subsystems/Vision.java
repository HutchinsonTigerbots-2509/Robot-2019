package frc.robot.subsystems; // package declaration



// imports
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.*;
import frc.robot.Constants;


/**
 * The Vision Subsystem is the limelight camera thing -- Nate you finish pls
 * 
 * @author Nate C
 */
public class Vision extends Subsystem {
  private NetworkTable mTable = NetworkTableInstance.getDefault().getTable(Constants.kLimelightNetworkID);
  private NetworkTableEntry mTableX = mTable.getEntry(Constants.kLimelightTargetXID);
  private NetworkTableEntry mTableY = mTable.getEntry(Constants.kLimelightTargetYID);
  private NetworkTableEntry mTableS = mTable.getEntry(Constants.kLimelightTargetSkewID);
  private NetworkTableEntry mTableArea = mTable.getEntry(Constants.kLimelightTargetAreaID);
  private NetworkTableEntry mTablevert = mTable.getEntry(Constants.kLimelightTargetvert);
  private NetworkTableEntry mTablehor;
  private NetworkTableEntry mTableCorners = mTable.getEntry("tcornx");
  private double mTargetX = 0;
  private double mTargetY = 0;
  private double mTargetArea = 0;
  private double mTargetvert = 0;
  private double distance = 0;
  private double mTargetSkew = 0;
  private double mTargetHor = 0;
  public double DistanceOne;
  public double DistanceTwo;
  public double DistanceToDrive;
  public double AngleOne;
  public double Turn_angle = 0;
  
 


  /**
   * Returns the NetworkTable for the Limelight Camera
   * 
   * @return mTable
   */
  public NetworkTable getTable() {
    return mTable;
  }

  /**
   * Returns the 'X' value from the Limelight. If no value is detected will return
   * 0.0
   * 
   * @return targetX
   */
  public double getTargetX() {
    mTableX = mTable.getEntry(Constants.kLimelightTargetXID);
    mTargetX = mTableX.getDouble(0.0);
    return mTargetX;
  }
  public double getTargetVert() {
    mTablevert = mTable.getEntry(Constants.kLimelightTargetvert);
    mTargetvert = mTablevert.getDouble(0.0);
    return mTargetvert;
  }
  public double getTargethor() {
    mTablehor = mTable.getEntry(Constants.kLimelightTargethorID);
    mTargetHor = mTablehor.getDouble(0.0);
    return mTargetHor;
  }
 

  /**
   * Returns the 'Y' value from the Limelight. If no value is detected will return
   * 0.0
   * 
   * @return targetY
   */
  public double getTargetY() {
    mTableY = mTable.getEntry(Constants.kLimelightTargetYID);
    mTargetY = mTableY.getDouble(0.0);
    return mTargetY;
  }

  /**
   * Returns the 'Area' value from the Limelight. If no value is detected will
   * return 0.0
   * 
   * @return targetArea
   */
  public double getTargetArea() {
    mTableArea = mTable.getEntry(Constants.kLimelightTargetAreaID);
    mTargetArea = mTableArea.getDouble(0.0);
    return mTargetArea;
  }
  public double getTargetSkew() {
    mTableS = mTable.getEntry(Constants.kLimelightTargetAreaID);
    mTargetSkew = mTableS.getDouble(0.0);
    return mTargetSkew;
  }
  public void change_vision_pipeline(int pipeline){
    mTable.getEntry("pipeline").setNumber(pipeline);

  }
  public double cal_distance(){
    distance = (86.9 * Math.pow(mTableArea.getDouble(0.0), -0.483));
    return distance;
  }
  public double calculateDistanceVariables(double thetaAngle,  double distance){
    DistanceOne = (Math.cos(Math.toRadians(thetaAngle)) * distance);
    DistanceTwo = Math.sqrt(Math.pow(distance, 2) - Math.pow(DistanceOne, 2));
    DistanceToDrive = Math.sqrt(Math.pow((DistanceOne - Constants.kTargetDistanceFromTarget), 2) + Math.pow(DistanceTwo, 2));
    AngleOne = Math.atan((DistanceOne - Constants.kTargetDistanceFromTarget)/(DistanceTwo));
    Turn_angle = (90 - thetaAngle + AngleOne);

    return Math.toDegrees(Turn_angle);



  }
  /**
   * Updateds the Limelight camera settings via the NetworkTable.
   */
  public void UpdateLimelightSettings() {
    mTable.getEntry("ledMode").setNumber(Constants.kLimelightLED);
    mTable.getEntry("camMode").setNumber(Constants.kLimelightMode);
    mTable.getEntry("stream").setNumber(Constants.kLimelightStream);
    //mTable.getEntry("pipeline").setNumber(0);
  }

  @Override
  public void initDefaultCommand() {
  }

  @Deprecated
  private void PullFromTable() {
    mTableX = mTable.getEntry(Constants.kLimelightTargetXID);
    mTableY = mTable.getEntry(Constants.kLimelightTargetYID);
    mTableArea = mTable.getEntry(Constants.kLimelightTargetAreaID);
  }

  @Deprecated
  private void UpdateValuesFromTable() {
    PullFromTable();
    mTargetX = mTableX.getDouble(0.0);
    mTargetY = mTableY.getDouble(0.0);
    mTargetArea = mTableArea.getDouble(0.0);
  }
}
