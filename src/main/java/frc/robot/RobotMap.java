/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    //Elevator
    public static WPI_TalonSRX Right_Lift;
    public static WPI_TalonSRX Left_Lift;
    public static Encoder  RightLiftEncoder;
    public static Encoder LeftLiftEncoder;
    public static WPI_TalonSRX RightSpoolMaster; 
    public static VictorSP LeftSpoolSlave; 

    RightSpoolMaster = new WPI_TalonSRX(Constants.kRightSpoolMasterMasterID);
    RightSpoolMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    LeftSpoolSlave = new WPI_TalonSRX(Constants.kLeftSpoolSlaveID); 
    LeftSpoolLeftSlave.follow(RightSpoolMaster);
  
}
