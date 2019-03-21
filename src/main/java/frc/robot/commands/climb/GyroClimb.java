/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Climber;

public class GyroClimb extends Command {

  private AHRS gyro = RobotMap.DrivetrainGyro;
  private Climber sClimb = Robot.sClimb;
  double SpeedGain = 0.07;
  double ElevatorSpeed;
  private Joystick stick;

  public GyroClimb() {

  }
  @Override
  protected void initialize() {

    gyro.reset();
    sClimb.StageOneStart();
    sClimb.StageTwoStart();
    stick = Robot.oi.getOperatorStick();

  }
  @Override
  protected void execute() {

    ElevatorSpeed = gyro.getRoll() *SpeedGain;
    
    if(gyro.getRoll() < 30){
      if(ElevatorSpeed > 0.4 || ElevatorSpeed < -0.4){
        RobotMap.ElevatorMotorMaster.set(ControlMode.PercentOutput, ElevatorSpeed);
      }else{
        RobotMap.ElevatorMotorMaster.set(ControlMode.PercentOutput, 0.0);
      }    
    }

    if(stick.getRawAxis(5)> -0.3 || stick.getRawAxis(5)< 0.3){
      sClimb.setMotorSpeed(stick.getRawAxis(5));
    } else {
      sClimb.setMotorSpeed(0);
    }

    // if(gyro.getRoll() < -7){
    //   RobotMap.ElevatorMotorMaster.set(ControlMode.PercentOutput, 0.75);
    // }else if(gyro.getRoll() > 5){
    //   RobotMap.ElevatorMotorMaster.set(ControlMode.PercentOutput, 0.0);
    // }else{
    //   RobotMap.ElevatorMotorMaster.set(ControlMode.PercentOutput, 0.0);
    // }
  }
  @Override
  protected boolean isFinished() {
    return false;
  }
  @Override
  protected void end() {
    sClimb.setMotorSpeed(0);
    RobotMap.ElevatorMotorMaster.set(ControlMode.PercentOutput, 0.0);
    // sClimb.RetractStageOne();
    // sClimb.RetractStageTwo();
  }
  @Override
  protected void interrupted() {
    end();
  }
}
