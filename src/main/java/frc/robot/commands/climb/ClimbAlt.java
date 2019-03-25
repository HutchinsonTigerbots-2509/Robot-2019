/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class ClimbAlt extends Command {
  private Elevator sElevator = Robot.sElevator;
  private Intake sIntake = Robot.sIntake;
  private Climber sClimber = Robot.sClimb;
  private Joystick stick;
  private int mWristAngle = -90;
  private int mElevatorHieght = 12;
  // private boolean EverythingIsReady = (sIntake.getCurrentAngle() == Constants.kWristGroundAngle && sElevator.)

  public ClimbAlt(Joystick joystick) {
    requires(sClimber);
    requires(sElevator);
    requires(sIntake);
    stick = joystick;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(sClimber.PreparedToClimb == true){
    sClimber.StageOneStart();
    sClimber.StageTwoStart();
    } else {
      end();
    }
    //sIntake.WristMove(mWristAngle);
    //sElevator.setHighGear(false);
    //sElevator.setPositionLowGear(mElevatorHieght);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    sClimber.setMotorSpeed(-stick.getRawAxis(1));
    //sIntake.WristMove(mWristAngle);
    //sElevator.setPositionLowGear(mElevatorHieght);
    // SmartDashboard.putNumber("Wrist Volts", RobotMap.WristMotor.getMotorOutputVoltage());
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
    sIntake.StopWrist();
    sElevator.StopMotors();
    RobotMap.WristMotor.config_kD(0, Constants.kElevatorDGain);
    RobotMap.WristMotor.config_kP(0, Constants.kElevatorPGain);
    RobotMap.WristMotor.config_kI(0, Constants.kElevatorIGain);
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
