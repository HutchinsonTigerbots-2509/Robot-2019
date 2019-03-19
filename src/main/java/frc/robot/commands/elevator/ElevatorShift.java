package frc.robot.commands.elevator;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Elevator;
import frc.robot.RobotMap;

public class ElevatorShift extends InstantCommand {
  private final Elevator mElevator = new Elevator();
  private WPI_TalonSRX SpoolMotor = RobotMap.ElevatorMotorMaster;
  private int ElevatorPosition;
  private boolean isShifted;
  public ElevatorShift() {
    super();
    requires(mElevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    isShifted = mElevator.isHighGear();
    ElevatorPosition = SpoolMotor.getSelectedSensorPosition();
    // if(isShifted == true){
    //   ElevatorPosition = (ElevatorPosition/274)*860;
    //   SpoolMotor.setSelectedSensorPosition(ElevatorPosition);
    // } else {
    //   ElevatorPosition = (ElevatorPosition/860)*274;
    //   SpoolMotor.setSelectedSensorPosition(ElevatorPosition);
    // }
    mElevator.setHighGear(!isShifted);

    //mElevator.isHighGear(); this will probably work if the other doesnt
  }
}
