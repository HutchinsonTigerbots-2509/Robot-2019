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
import frc.robot.Constants;

public class HeightToggle extends Command {

  private double mHatchLow = Constants.kHatchLow;
  private double mHatchMid = Constants.kHatchMid;
  private double mHatchHigh = Constants.kHatchHigh;
  private double mBallLow = Constants.kBallLow;
  private double mBallMid = Constants.kBallMid;
  private double mBallHigh = Constants.kBallHigh;
  private JoystickButton mLowButton;
  private JoystickButton mMidButton;
  private JoystickButton mHighButton;

  public HeightToggle() {
    mHighButton = Robot.oi.mElevatorHigh;
    mMidButton = Robot.oi.mElevatorMid;
    mLowButton = Robot.oi.mElevatorLow;
  }

  @Override
  protected void initialize() {
    Robot.oi.mElevatorHigh.whenPressed(new ElevatorRise(mBallHigh));
    Robot.oi.mElevatorMid.whenPressed(new ElevatorRise(mBallMid));
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
    mHighButton.whenPressed(new ElevatorRise(mHatchHigh));
    mMidButton.whenPressed(new ElevatorRise(mHatchMid));
    mLowButton.whenPressed(new ElevatorRise(mHatchLow));

  }

  @Override
  protected void interrupted() {
    end();
  }
}
