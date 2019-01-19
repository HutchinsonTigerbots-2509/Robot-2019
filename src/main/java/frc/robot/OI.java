/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.CloseIntake;
import frc.robot.commands.OpenIntake;
import frc.robot.commands.Takein;
import frc.robot.commands.Takeout;
import frc.robot.commands.WristDown;
import frc.robot.commands.WristUp;
import frc.robot.subsystems.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private Vision sVision = Robot.sVision;
  private NetworkTable mLimeTable;

  private Joystick mrSticky = new Joystick(0);

  private JoystickButton mCloseintake = new JoystickButton(mrSticky, 1); // Close intake
  private JoystickButton mOpenintake = new JoystickButton(mrSticky, 2); // Open intake
  private JoystickButton mIntakein = new JoystickButton(mrSticky, 3); // Take in
  private JoystickButton mIntakeout = new JoystickButton(mrSticky, 4); // Take out
  private JoystickButton mWristdown = new JoystickButton(mrSticky, 5); // Wrist down
  private JoystickButton mWristup = new JoystickButton(mrSticky, 6); // Wrist up
  //#region Joystic Button Creation
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
  //#endregion

  public OI(){

    /* Vision & NetworkTables */
    mLimeTable = sVision.getTable();
    SmartDashboard.putNumber("limeLightX", sVision.getTargetX());
    SmartDashboard.putNumber("limeLightY", sVision.getTargetY());
    SmartDashboard.putNumber("limeLightArea", sVision.getTargetArea());

    mCloseintake.whileHeld(new CloseIntake());
    SmartDashboard.putData(new CloseIntake());
    mOpenintake.whileHeld(new OpenIntake());
    SmartDashboard.putData(new OpenIntake());
    mIntakein.whileHeld(new Takein());
    SmartDashboard.putData(new Takein());
    mIntakeout.whileHeld(new Takeout());
    SmartDashboard.putData(new Takeout());
    mWristdown.whileHeld(new WristDown());
    SmartDashboard.putData(new WristDown());
    mWristup.whileHeld(new WristUp());
    SmartDashboard.putData(new WristUp());
  }
}
