package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;

public class Elevator extends PIDSubsystem {

  public static WPI_TalonSRX right_spool = RobotMap.Right_Lift;
  public static WPI_TalonSRX left_spool = RobotMap.Left_Lift;
  public static Encoder RightEncoder = RobotMap.RightLiftEncoder;
  public static Encoder LeftEncoder = RobotMap.LeftLiftEncoder;

  public Elevator() {
    super("SubsystemName", 1, 1, 1);

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
  public void Lift(){
    if(RightEncoder >= 0.6)
    right_spool.set(0.6);
    left_spool.set(0.6);
  }
}
