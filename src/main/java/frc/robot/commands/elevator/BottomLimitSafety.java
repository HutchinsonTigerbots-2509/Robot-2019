/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class BottomLimitSafety extends Trigger {

  TalonSRX ElevatorMotor = RobotMap.ElevatorMotorMaster;

  @Override
  public boolean get() {
    return RobotMap.ElevatorBottomLimit.get();
  }
  public void whenActive(){
    if(RobotMap.ElevatorTopLimit.get()){
      //ElevatorMotor.set(ControlMode.PercentOutput, 0);
    }else{
    //ElevatorMotor.set(ControlMode.PercentOutput, 0.4);
    SmartDashboard.putString("This Does Anything", "True!");
    }
  }
}
