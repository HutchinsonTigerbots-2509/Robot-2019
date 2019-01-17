package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.Constants;

public class Elevator extends PIDSubsystem {

  private double kP = Constants.kElevatorP; 
  private double kI = Constants.kElevatorI; 
  private double kD = Constants.kElevatorD; 

  public static WPI_TalonSRX RightSpoolMaster = RobotMap.Right_Lift;
  public static VictorSP LeftSpoolSlave = RobotMap.LeftSpoolSlave;
  public static Encoder ElevatorEncoder = RobotMap.RightLiftEncoder;

  public Elevator(){

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
}
