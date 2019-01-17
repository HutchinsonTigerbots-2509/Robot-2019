/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems; 

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.PIDSubsystem; 
import frc.robot.Constants; 
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Encoder;

public class Elevator extends PIDSubsystem {

  private double kP = Constants.kElevatorP; 
  private double kI = Constants.kElevatorI; 
  private double kD = Constants.kElevatorD; 

  public static TalonSRX RightSpoolMaster = RobotMap.RightSpoolMaster;
  public static VictorSPX LeftSpoolSlave = RobotMap.LeftSpoolSlave;
  public static Encoder ElevatorEncoder = RobotMap.RightLiftEncoder;

  public Elevator(){

    super("Elevator", Constants.kElevatorP, Constants.kElevatorI, Constants.kElevatorD);

    setName("LiftTrain");
    addChild(RightSpoolMaster);
    addChild(LeftSpoolSlave);

  }

  @Override
  public void initDefaultCommand() {

  }

  @Override
  protected double returnPIDInput() {

    return 0.0;
  }

  @Override
  protected void usePIDOutput(double output) {
    
  }

  public void LiftMax(){
    
  }

public double getCurrentHight(){
  
}

public Encoder getLeftEncoder() {
    return ElevatorEncoder;
  }
}
