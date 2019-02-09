/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class HeightToggle extends Command {

  double mHatchLow = 20.0;
  double mHatchMid = 47.5;
  double mHatchHigh = 75.0;
  double mBallLow = 28.5;
  double mBallMid = 56.0;
  double mBallHigh = 83.0;

  public static double mtHigh = 75.0;
  public static double mtMid = 56.0;
  public static double mtLow = 28.5;

  public HeightToggle() {

  }

  @Override
  protected void initialize() {

    mtHigh = mBallHigh;
    mtHigh = mBallMid;
    mtLow = mBallLow;

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

    mtHigh = mHatchHigh;
    mtHigh = mHatchMid;
    mtLow = mHatchLow;

  }

  @Override
  protected void interrupted() {
    end();
  }
}
