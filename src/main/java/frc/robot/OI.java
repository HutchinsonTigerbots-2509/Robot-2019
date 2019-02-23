package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ElevatorDown;
import frc.robot.commands.ElevatorUp;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakeOut;
import frc.robot.commands.WristDown;
import frc.robot.commands.WristUp;
import frc.robot.subsystems.Intake;
import frc.robot.commands.ClimbEnd;
import frc.robot.commands.ClimbHab2;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public static Intake sIntake;

  /* JOYSTICK DECLARATIONS */
  private Joystick mOpStick; // The main joystick. Used for driving and driving related commands
  private Joystick mCoOpStick; // Everything else is used here

  /* BUTTON DECLARATIONS */
  private JoystickButton IntakeIn;
  private JoystickButton IntakeOut;
  private JoystickButton WristDown;
  private JoystickButton WristUp;
  private JoystickButton ElevatorUp;
  private JoystickButton ElevatorDown;

  // #region Joystic Button Creation
  // CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  // joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  // TRIGGERING COMMANDS WITH BUTTONS
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
  // #endregion

  /**
   * OI class constructor that will create an object with access to all of the
   * buttons and joysticks
   */
  public OI() {
    /* Joysticks & Buttons */
    mOpStick = new Joystick(0);
    mCoOpStick = new Joystick(1);
    sIntake = new Intake();

    WristUp = new JoystickButton(mCoOpStick, 1);
    WristUp.whileHeld(new WristUp());

    WristDown = new JoystickButton(mCoOpStick, 2);
    WristDown.whileHeld(new WristDown());

    IntakeIn = new JoystickButton(mCoOpStick, 3);
    IntakeIn.whileHeld(new IntakeIn());

    IntakeOut = new JoystickButton(mCoOpStick, 4);
    IntakeOut.whileHeld(new IntakeOut());

    ElevatorUp = new JoystickButton(mOpStick, 5);
    ElevatorUp.whenPressed(new ClimbHab2());

    ElevatorDown = new JoystickButton(mOpStick, 6);
    ElevatorDown.whenPressed(new ClimbEnd());
  }

  /**
   * Will return the operator stick varible
   * 
   * @return OpStick
   */
  public Joystick getOperatorStick() {
    return mOpStick;
  }

  /**
   * Will return the Cooperator Stick varible
   * 
   * @return CoOpStick
   */
  public Joystick getCoOperatorStick() {
    return mCoOpStick;
  }
}
