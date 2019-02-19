/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;

public class ClimbAlt extends Command {
  private Elevator sElevator = Robot.sElevator;
  private Intake sIntake = Robot.sIntake;
  private Climber sClimber = Robot.sClimb;
  private Joystick stick;
  private int mWristAngle = -90;
  private int mElevatorHieght = 12;

  public ClimbAlt(Joystick joystick) {
    requires(sClimber);
    requires(sElevator);
    requires(sIntake);
    stick = joystick;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    sClimber.StageOneStart();
    sClimber.StageTwoStart();
    sIntake.WristMove(mWristAngle);
    sElevator.setHighGear(false);
    sElevator.setPositionLowGear(mElevatorHieght);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    sClimber.setMotorSpeed(-stick.getY()*2);
    sIntake.WristMove(mWristAngle);
    sElevator.setPositionLowGear(mElevatorHieght);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    sClimber.setMotorSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    sClimber.setMotorSpeed(0);
    sIntake.StopWrist();
    sElevator.StopMotors();
  }
}
