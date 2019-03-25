/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Creep extends Command {

  private static VictorSP ClimbMotor = RobotMap.ClimbMotor;
  private final Drivetrain drive = Robot.sDrivetrain;
  public static DifferentialDrive Drive = RobotMap.DrivetrainDifferential;

  public Creep() {
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    ClimbMotor .set(0.5);
    drive.getDrive().arcadeDrive(0.6, 0.0);
  }

  @Override   
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    ClimbMotor.set(0.0);
    drive.getDrive().arcadeDrive(0.0, 0.0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
