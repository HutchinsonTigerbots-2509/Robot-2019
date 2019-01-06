/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;

/**
 * @author Nate C
 */
public class Vision extends Subsystem {
  private NetworkTable table = NetworkTableInstance.getDefault().getTable(Constants.kLimelightNetworkID);
  private NetworkTableEntry tableX = table.getEntry(Constants.kLimelightTargetXID);
  private NetworkTableEntry tableY = table.getEntry(Constants.kLimelightTargetYID);
  private NetworkTableEntry tableArea = table.getEntry(Constants.kLimelightTargetAreaID);
  private double targetX = 0;
  private double targetY = 0;
  private double targetArea = 0;
  
  public NetworkTable getTable(){
    return table;
  }

  /**
   * Returns the 'X' value from the Limelight.
   * If no value is detected will return 0.0
   * @return targetX
   */
  public double getTargetX(){
    tableX = table.getEntry(Constants.kLimelightTargetXID);
    targetX = tableX.getDouble(0.0);
    return targetX;
  }
  /**
   * Returns the 'Y' value from the Limelight.
   * If no value is detected will return 0.0
   * @return targetY
   */
  public double getTargetY(){
    tableY = table.getEntry(Constants.kLimelightTargetYID);
    targetY = tableX.getDouble(0.0);
    return targetY;
  }
  /**
   * Returns the 'Area' value from the Limelight.
   * If no value is detected will return 0.0
   * @return targetArea
   */
  public double getTargetArea(){
    tableArea = table.getEntry(Constants.kLimelightTargetAreaID);
    targetArea = tableX.getDouble(0.0);
    return targetArea;
  }


  @Override
  public void initDefaultCommand() {
  }
  @Deprecated
  private void PullFromTable(){
    tableX = table.getEntry(Constants.kLimelightTargetXID);
    tableY = table.getEntry(Constants.kLimelightTargetYID);
    tableArea = table.getEntry(Constants.kLimelightTargetAreaID);
  }
  @Deprecated
  private void UpdateValuesFromTable(){
    PullFromTable();
    targetX = tableX.getDouble(0.0);
    targetY = tableY.getDouble(0.0);
    targetArea = tableArea.getDouble(0.0);
  }
}
