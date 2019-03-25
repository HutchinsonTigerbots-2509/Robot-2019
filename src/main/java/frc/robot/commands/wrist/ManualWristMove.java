/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class ManualWristMove extends Command {

  private static Intake sIntake = Robot.sIntake;
  private Joystick stick;

  public ManualWristMove() {

    requires(sIntake);
    
  }

  @Override
  protected void initialize() {

    stick = Robot.oi.getCoOperatorStick();

  }

  @Override
  protected void execute() {

    sIntake.ManualMoveMark2(stick);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {

    sIntake.StopWrist();

  }

  @Override
  protected void interrupted() {

    end();

  }
}