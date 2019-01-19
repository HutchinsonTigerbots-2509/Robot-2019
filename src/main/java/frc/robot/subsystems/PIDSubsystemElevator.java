/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

// package frc.robot.subsystems; 

// import frc.robot.RobotMap;
// import edu.wpi.first.wpilibj.command.PIDSubsystem; 
// import frc.robot.Constants; 
// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.can.TalonSRX;
// import com.ctre.phoenix.motorcontrol.can.VictorSPX;
// import edu.wpi.first.wpilibj.Encoder;

// public class PIDSubsystemElevator extends PIDSubsystem {

//   // private double kP = Constants.kElevatorP; 
//   // private double kI = Constants.kElevatorI; 
//   // private double kD = Constants.kElevatorD; 
//   private double kSpoolD = Constants.kSpoolDiam;
//   private double kPulseNumber = Constants.kPulsesPerRotation;
//   private double kMaxHeight = Constants.kMaxElevatorHeight;

//   // private int kPIDLoop = Constants.kPIDLoopIndx;
//   // private int kTimeout = Constants.kTimeoutMs;

//   public static TalonSRX RightSpoolMaster = RobotMap.RightSpoolMaster;
//   public static VictorSPX LeftSpoolSlave = RobotMap.LeftSpoolSlave;
//   public static Encoder ElevatorEncoder = RobotMap.ElevatorEncoder;
  

//  // public PIDSubsystemElevator(){

//     //super("Elevator", Constants.kElevatorP, Constants.kElevatorI, Constants.kElevatorD);
//     // setAbsoluteTolerance(0.05);
// 		// getPIDController().setContinuous(false);

//     // setName("LiftTrain");
//     // addChild(RightSpoolMaster);
//     // addChild(LeftSpoolSlave);

//     //RightSpoolMaster.configSelectedFeedbackSensor(ElevatorEncoder.CTRE_MagEncoder_Relative,kPIDLoop, kTimeout);
//   }

//   @Override
//   public void initDefaultCommand() {

//   }

//   @Override
//   protected double returnPIDInput() {
//     return 0.0;//pot.getAverageVoltage(); // returns the sensor value that is providing the feedback for the system
//   }

//   @Override
//   protected void usePIDOutput(double output) {
//     //RightSpoolMaster.pidWrite(output); // this is where the computed output value fromthe PIDController is applied to the motor
//   }

//   public void Lift2Max(){
//     while(getCurrentHeight()<kMaxHeight){

//       RightSpoolMaster.set(ControlMode.PercentOutput, 0.5);
//     }
//     RightSpoolMaster.set(ControlMode.PercentOutput, 0);
//   }

//   public double getCurrentHeight(){
//   return ElevatorEncoder.get()*((kSpoolD*Math.PI)/kPulseNumber);
// }
// }
