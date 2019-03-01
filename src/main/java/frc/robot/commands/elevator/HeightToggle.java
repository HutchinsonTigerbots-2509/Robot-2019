/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;

public class HeightToggle extends InstantCommand {

  public HeightToggle() {
  }

  @Override
  protected void initialize() {
    if(Robot.sElevator.state=="Hatch"){
      Robot.sElevator.state="Cargo";
      Robot.oi.setElevatorButtonsCargo();
      // SmartDashboard.putString("state",Robot.sElevator.state);
    }else if(Robot.sElevator.state=="Cargo"){
      Robot.sElevator.state="Hatch";
      Robot.oi.setElevatorButtonsHatch();
      // SmartDashboard.putString("state",Robot.sElevator.state);
    }
  }

}
