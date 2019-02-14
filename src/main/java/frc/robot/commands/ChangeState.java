/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.State;

/**
 * Moves the robot subsystems to change states
 * from Hatch targeting to cargo or vis versa.
 * @author Nate
 */
public class ChangeState extends InstantCommand {
  private OI mOI = Robot.oi;
  private Elevator sElevator = Robot.sElevator;
  private Intake sIntake = Robot.sIntake;
  private State robotState = Robot.robotState;
  private int mState;
  public ChangeState(int state) {
    super();
    this.mState = state;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }
  public ChangeState(){
    super();
    robotState.toggleState();
    mState = robotState.CurrentState;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    if(mState==State.CargoState){
      mOI.setElevatorButtonsCargo();
      if(sIntake.getWristStatus()!="Down"){
        sIntake.WristPistonDown();
      }
    }
    if(mState==State.HatchState){
      mOI.setElevatorButtonsHatch();
    }
  }

}
