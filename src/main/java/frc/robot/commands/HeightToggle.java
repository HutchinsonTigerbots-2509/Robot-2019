/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;
import frc.robot.Constants;
import frc.robot.OI;

public class HeightToggle extends InstantCommand {
  private Elevator sElevator;
  private OI oi;

  public HeightToggle() {
    oi = Robot.oi;
    sElevator = Robot.sElevator;
  }

  @Override
  protected void initialize() {
    if(sElevator.state=="Hatch"){
      oi.setElevatorButtonsCargo();
      new WristMove(-90).start();
    }else{
      oi.setElevatorButtonsHatch();
    }
  }

}
