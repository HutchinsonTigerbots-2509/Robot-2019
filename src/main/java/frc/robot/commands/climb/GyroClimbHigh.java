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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.subsystems.Climber;
import frc.robot.commands.climb.ManualCreep;

public class GyroClimbHigh extends Command {

  private AHRS gyro = RobotMap.DrivetrainGyro;
  private ManualCreep cManualCreep = new ManualCreep();
  private Climber sClimb = Robot.sClimb;
  double SpeedGain = 0.0;
  double ElevatorSpeed;
  private Joystick stick;

  public GyroClimbHigh() {

  }
  @Override
  protected void initialize() {

    gyro.reset();
    sClimb.StageOneStart();
    sClimb.StageTwoStart();
    stick = Robot.oi.getOperatorStick();
    cManualCreep.start();

    if(RobotMap.ElevatorShifter.get() == Value.kForward){ //Allows us to change the gain depending on the elevator gear
      SpeedGain = 0.07;
    } else {
       SpeedGain = 0.067;
    }
  }
  @Override
  protected void execute() {

    // SmartDashboard.sen(gyro, gyro.getRoll());
    
    ElevatorSpeed = (gyro.getRoll()) * SpeedGain;
    
    if(gyro.getRoll() < 30 && RobotMap.ElevatorMotorMaster.getSelectedSensorPosition() < 20500){
      if(ElevatorSpeed > 0.4 || ElevatorSpeed < -0.4){
        RobotMap.ElevatorMotorMaster.set(ControlMode.PercentOutput, ElevatorSpeed);
      }else{
        RobotMap.ElevatorMotorMaster.set(ControlMode.PercentOutput, 0.0);
      }    
    }

    if(stick.getRawAxis(5)>= 0.5 || stick.getRawAxis(5)<= -.5){
      sClimb.setMotorSpeed(stick.getRawAxis(5));
    } else {
      sClimb.setMotorSpeed(0);
    }

    // if(gyro.getRoll() > 3){
    //   if(ElevatorSpeed > .4){
    //     RobotMap.ElevatorMotorMaster.set(ControlMode.PercentOutput, .4);
    //   }else{
    //     RobotMap.ElevatorMotorMaster.set(ControlMode.PercentOutput, ElevatorSpeed);
    //   }
    // }else if(gyro.getRoll() < -5){
    //   if(ElevatorSpeed < -.4){
    //     RobotMap.ElevatorMotorMaster.set(ControlMode.PercentOutput, -.4);
    //   }else{
    //     RobotMap.ElevatorMotorMaster.set(ControlMode.PercentOutput, ElevatorSpeed);
    //   }
    // }else{
    //   RobotMap.ElevatorMotorMaster.set(ControlMode.PercentOutput, 0.0);
    // }



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
