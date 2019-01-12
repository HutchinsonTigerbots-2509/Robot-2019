/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  public static WPI_TalonSRX DriveTrainRightOne;
  public static WPI_TalonSRX DriveTrainRightTwo;
  public static WPI_TalonSRX DriveTrainLeftOne;
  public static WPI_TalonSRX DriveTrainLeftTwo;
  public static SpeedControllerGroup DriveTrainLeft;
  public static SpeedControllerGroup DriveTrainRight;
  public static DifferentialDrive Drive;

  public static void Init (){
      DriveTrainRightOne = new WPI_TalonSRX(0);
      DriveTrainRightTwo = new WPI_TalonSRX(1);
      DriveTrainLeftOne = new WPI_TalonSRX(2);
      DriveTrainLeftTwo = new WPI_TalonSRX(3);
      DriveTrainLeft = new SpeedControllerGroup(DriveTrainLeftOne, DriveTrainLeftTwo);
      DriveTrainRight = new SpeedControllerGroup(DriveTrainRightOne, DriveTrainRightTwo);
      Drive = new DifferentialDrive(DriveTrainLeft, DriveTrainRight);
  }

}
