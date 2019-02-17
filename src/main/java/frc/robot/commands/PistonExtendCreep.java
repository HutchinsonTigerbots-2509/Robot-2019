/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Drivetrain;

public class PistonExtendCreep extends Command {

    private Climb sClimb = Robot.sClimb;
    private static VictorSP ClimbMotor = RobotMap.ClimbMotor;
    private Joystick mStick; // The Operator Stick (the one that is used for driving)
   
  public PistonExtendCreep() {
    requires(sClimb);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    mStick = Robot.oi.getOperatorStick();
    sClimb.StageOneStart();
    sClimb.StageTwoStart();
    if(!Robot.cOpDrive.isRunning())Robot.cOpDrive.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    ClimbMotor.set(mStick.getRawAxis(1));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    ClimbMotor.set(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
