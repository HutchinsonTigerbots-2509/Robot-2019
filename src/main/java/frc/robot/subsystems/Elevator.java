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
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;


/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {

  public final TalonSRX RightSpoolMaster = RobotMap.RightSpoolMaster;
  public final VictorSPX LeftSpoolSlave = RobotMap.LeftSpoolSlave;
  public final Encoder ElevatorEncoder = RobotMap.ElevatorEncoder; 
  
  private final Joystick CoOpStick = Robot.oi.getCoOperatorStick();

  private final double kPulseNumber = Constants.kPulsesPerRotation;
  private final double kMaxHeight = Constants.kMaxHieght;
  private final double kMidHeight = Constants.kMidHieght;
  private final double kMinHeight = Constants.kMinHieght;
  private final double kSpoolDiam = Constants.kSpoolDiam;
  private final double PGain = Constants.kElevatorPGain;
  private final double IGain = Constants.kElevatorPGain;
  private final double DGain = Constants.kElevatorPGain;
  private final double kMaxSpeed = Constants.kElevatorMaxSpeed;
  private final double ElevatorSensitivity = Constants.kElevatorSensitivity;

  private double Error;
  private double Perpotional;
  private double Derivative;
  private double Integral;
  private double PError;
  private double EncoderTargetHieght;

  @Override
  public void initDefaultCommand() {
  }

  public double TargetHeight(){
    if(CoOpStick.getRawAxis(1) != 0){
      EncoderTargetHieght = EncoderTargetHieght + ((ElevatorSensitivity)*(CoOpStick.getRawAxis(1)*-1));
    }else if(CoOpStick.getRawButton(4)){
      EncoderTargetHieght = (kMaxHeight*((kSpoolDiam*Math.PI)/kPulseNumber));//Max
    }else if(CoOpStick.getRawButton(2)){
      EncoderTargetHieght = (kMidHeight*((kSpoolDiam*Math.PI)/kPulseNumber));//Mid
    }else if(CoOpStick.getRawButton(1)){
      EncoderTargetHieght = (kMinHeight*((kSpoolDiam*Math.PI)/kPulseNumber));//Min
    }
    return EncoderTargetHieght;
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

  public Encoder getLeftEncoder() {
      return ElevatorEncoder;
  }
}
