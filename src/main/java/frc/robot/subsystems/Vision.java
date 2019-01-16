package frc.robot.subsystems; // package declaration

// imports
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.*;

/**
 * The Vision Subsystem is the limelight camera thing -- Nate you finish pls
 * 
 * @author Nate C
 */
public class Vision extends Subsystem {
  private NetworkTable mTable = NetworkTableInstance.getDefault().getTable(Constants.kLimelightNetworkID);
  private NetworkTableEntry mTableX = mTable.getEntry(Constants.kLimelightTargetXID);
  private NetworkTableEntry mTableY = mTable.getEntry(Constants.kLimelightTargetYID);
  private NetworkTableEntry mTableArea = mTable.getEntry(Constants.kLimelightTargetAreaID);
  private double mTargetX = 0;
  private double mTargetY = 0;
  private double mTargetArea = 0;

  /** 
   * Returns the NetworkTable for the Limelight Camera
   * @return mTable
   */
  public NetworkTable getTable(){
    return mTable;
  }

  /**
   * Returns the 'X' value from the Limelight.
   * If no value is detected will return 0.0
   * @return targetX
   */
  public double getTargetX(){
    mTableX = mTable.getEntry(Constants.kLimelightTargetXID);
    mTargetX = mTableX.getDouble(0.0);
    return mTargetX;
  }
  /**
   * Returns the 'Y' value from the Limelight.
   * If no value is detected will return 0.0
   * @return targetY
   */
  public double getTargetY(){
    mTableY = mTable.getEntry(Constants.kLimelightTargetYID);
    mTargetY = mTableX.getDouble(0.0);
    return mTargetY;
  }
  /**
   * Returns the 'Area' value from the Limelight.
   * If no value is detected will return 0.0
   * @return targetArea
   */
  public double getTargetArea(){
    mTableArea = mTable.getEntry(Constants.kLimelightTargetAreaID);
    mTargetArea = mTableX.getDouble(0.0);
    return mTargetArea;
  }

  /**
   * Updateds the Limelight camera settings via the NetworkTable.
   */
  public void UpdateLimelightSettings(){
    mTable.getEntry("ledMode").setNumber(Constants.kLimelightLED);
    mTable.getEntry("camMode").setNumber(Constants.kLimelightMode);
    mTable.getEntry("stream").setNumber(Constants.kLimelightStream);
  }


  @Override
  public void initDefaultCommand() {
  }

  @Deprecated
  private void PullFromTable(){
    mTableX = mTable.getEntry(Constants.kLimelightTargetXID);
    mTableY = mTable.getEntry(Constants.kLimelightTargetYID);
    mTableArea = mTable.getEntry(Constants.kLimelightTargetAreaID);
  }

  @Deprecated
  private void UpdateValuesFromTable(){
    PullFromTable();
    mTargetX = mTableX.getDouble(0.0);
    mTargetY = mTableY.getDouble(0.0);
    mTargetArea = mTableArea.getDouble(0.0);
  }
}
