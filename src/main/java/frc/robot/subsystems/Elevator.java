/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotMap;


/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {

  public static TalonSRX RightSpoolMaster = RobotMap.RightSpoolMaster;
  public static VictorSPX LeftSpoolSlave = RobotMap.LeftSpoolSlave;
  public static Encoder ElevatorEncoder = RobotMap.ElevatorEncoder;

  private double kPulseNumber = Constants.kPulsesPerRotation;
  private double kMaxHeight = Constants.kMaxElevatorHeight;
  private double kSpoolDiam = Constants.kSpoolDiam;
  private double PGain = Constants.kElevatorPGain;
  private double IGain = Constants.kElevatorPGain;
  private double DGain = Constants.kElevatorPGain;
  private double kMaxSpeed = Constants.kElevatorMaxSpeed;

  double Error;
  double Perpotional;
  double Derivative;
  double Integral;
  double PError;

  @Override
  public void initDefaultCommand() {

  }
  public double TargetHeight(){
    return 0.0;
    // if(stick is active){
    //   return vaule related to stick vaule;
    // }
    // else if(button a b, or c is toggled){
    //   if(a is toggled){
    //     return Highest Point;
    //   }
    //   else if(b is toggled){
    //     return Middle Point;
    //   }
    //   else if(c is toggled){
    //     return lowest point;
    //   }
    //   else{
    //     return 0;
    //   }
    }
  
  public double PIDFinal(){

    Error = TargetHeight() - ElevatorEncoder.get();
    Perpotional = Error *PGain;
    Derivative = (Error - PError) *DGain;
    Integral = 0;
    Integral += (Error * .02);
    PError = Error;

    SmartDashboard.putNumber("ElevatorEncoder", ElevatorEncoder.getDistance());
    SmartDashboard.putNumber("Perpotional", Perpotional);
    SmartDashboard.putNumber("Derivative", Derivative);
    SmartDashboard.putNumber("Integral", Integral);

    return (Perpotional + Derivative + (Integral*IGain));

  }

  public void ChaseTarget() {

    RightSpoolMaster.set(ControlMode.PercentOutput, (1*PIDFinal()));


  }

  public double CurrentHeight(){
  return ElevatorEncoder.get()*((kSpoolDiam*Math.PI)/kPulseNumber);
  }
}
