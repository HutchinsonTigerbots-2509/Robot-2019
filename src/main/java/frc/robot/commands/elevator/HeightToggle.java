/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;

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
      SmartDashboard.putString("Height Toggle", sElevator.state);
      // new WristMove(-90).start();
    }else{
      oi.setElevatorButtonsHatch();
      SmartDashboard.putString("Height Toggle", sElevator.state);
    }
  }

}
