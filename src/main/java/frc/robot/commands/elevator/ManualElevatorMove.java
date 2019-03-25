/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;

public class ManualElevatorMove extends Command {

  private static Elevator sElevator = Robot.sElevator;
  private Joystick stick;

  public ManualElevatorMove() {

    requires(sElevator);
    
  }

  @Override
  protected void initialize() {

    stick = Robot.oi.getCoOperatorStick();

  }

  @Override
  protected void execute() {

    sElevator.ManualMoveMark2(stick);

  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {

    sElevator.StopMotors();

  }

  @Override
  protected void interrupted() {

    end();

  }
}
