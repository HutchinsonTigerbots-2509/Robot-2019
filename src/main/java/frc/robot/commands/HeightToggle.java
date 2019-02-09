/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HeightToggle extends Command {

  private double mHatchLow = 20.0;
  private double mHatchMid = 47.5;
  private double mHatchHigh = 75.0;
  private double mBallLow = 28.5;
  private double mBallMid = 56.0;
  private double mBallHigh = 83.0;
  private JoystickButton mLowButton;
  private JoystickButton mMidButton;
  private JoystickButton mHighButton;

  public HeightToggle() {
    mLowButton = Robot.oi.mElevatorLow;
  }

  @Override
  protected void initialize() {
    Robot.oi.mElevatorLow.whenPressed(new ElevatorRise(mBallLow));
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    mLowButton.whenPressed(new ElevatorRise(mHatchLow));

  }

  @Override
  protected void interrupted() {
    end();
  }
}
