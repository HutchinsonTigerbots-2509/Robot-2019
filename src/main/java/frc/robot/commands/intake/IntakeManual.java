/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.Robot;

public class IntakeManual extends Command {

  private final Joystick mStick = Robot.oi.getCoOperatorStick();
  private final Intake sIntake = Robot.sIntake;

  public IntakeManual() {

    requires(sIntake); // Tells the code to use the drivetrain subsystem in this command

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //sDrivetrain.MarioDrive(mStick); // Uses the OPDRIVE() method that is created in Drivetrain.java
    sIntake.IntakeInManual(mStick);
  }

  protected void execute() {
    //sDrivetrain.MarioDrive(mStick); // Uses the MarioDrive method that is created in Drivetrain.java
    sIntake.IntakeInManual(mStick);
  
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
  }

  protected void interrupted() {
    end();
  }
}
