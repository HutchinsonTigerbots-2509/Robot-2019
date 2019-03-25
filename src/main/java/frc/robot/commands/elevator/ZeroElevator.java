/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;
import frc.robot.RobotMap;
import frc.robot.Constants;

public class ZeroElevator extends Command {
  private final Elevator sElevator = Robot.sElevator;
  private boolean IsFinished = false;
  private long ConversionVariable;
  public ZeroElevator() {
    requires(sElevator);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    RobotMap.ElevatorMotorMaster.set(ControlMode.PercentOutput, -0.3);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(RobotMap.ElevatorBottomLimit.get() == false && RobotMap.ElevatorTopLimit.get() == false){
      RobotMap.ElevatorMotorMaster.set(ControlMode.PercentOutput, -0.3);
    } else if(RobotMap.ElevatorBottomLimit.get() == false && RobotMap.ElevatorTopLimit.get() == true){
      if(sElevator.isHighGear() == true){
        ConversionVariable = Math.round(Constants.kTopLimitDistanceFromGround*Constants.kElevatorLowGearTicksPerInch);
        RobotMap.ElevatorMotorMaster.setSelectedSensorPosition((int)ConversionVariable);
        IsFinished = true;
      } else {
        ConversionVariable = Math.round(Constants.kTopLimitDistanceFromGround*Constants.kElevatorHighGearTicksPerInch);
        RobotMap.ElevatorMotorMaster.setSelectedSensorPosition((int)ConversionVariable);
        IsFinished = true;
      }
    } else if(RobotMap.ElevatorBottomLimit.get() == true && RobotMap.ElevatorTopLimit.get() == false){
      if(sElevator.isHighGear() == true){
        ConversionVariable = Math.round(Constants.kBottomLimitDistanceFromGround*Constants.kElevatorHighGearTicksPerInch);
        RobotMap.ElevatorMotorMaster.setSelectedSensorPosition((int)ConversionVariable);
        IsFinished = true;
      } else {
        ConversionVariable = Math.round(Constants.kBottomLimitDistanceFromGround*Constants.kElevatorLowGearTicksPerInch);
        RobotMap.ElevatorMotorMaster.setSelectedSensorPosition((int)ConversionVariable);
        IsFinished = true;
      }
    } else{
      IsFinished = true;
    }

    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(IsFinished == true){
      return true;
    } else {
    return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    sElevator.StopMotors();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
