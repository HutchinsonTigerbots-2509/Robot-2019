package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.RobotMap;
import frc.robot.subsystems.Elevator;

public class ElevatorShiftLow extends InstantCommand {
  private final Elevator mElevator = new Elevator();
  public ElevatorShiftLow() {
    super();
    requires(mElevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    //ONLY SHIFTS TO LOW DOESNT SHIFT BACK RIGHT NOW
    RobotMap.ElevatorShifter.set(Value.kReverse);


    // isShifted = mElevator.isHighGear();
    // ElevatorPosition = SpoolMotor.getSelectedSensorPosition();
    // if(isShifted == true){
    //   ElevatorPosition = (ElevatorPosition/274)*860;
    //   SpoolMotor.setSelectedSensorPosition(ElevatorPosition);
    // } else {
    //   ElevatorPosition = (ElevatorPosition/860)*274;
    //   SpoolMotor.setSelectedSensorPosition(ElevatorPosition);
    // }
    // mElevator.setHighGear(!isShifted);

    //mElevator.isHighGear(); this will probably work if the other doesnt
  }
}
