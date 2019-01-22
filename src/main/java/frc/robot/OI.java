package frc.robot; // package declaraition

// imports
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AlignWithTarget;
import frc.robot.commands.AlignWithTargetPID;
import frc.robot.commands.FollowTarget;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private Joystick mOpStick;
  private Joystick mCoOpStick;
  private JoystickButton AlignButton;
  private JoystickButton AlignButtonPID;
  private JoystickButton FollowButton;
  private final Drivetrain sDrivetrain = Robot.sDrivetrain;
  private final Vision sVision = Robot.sVision;
  private NetworkTable mLimeTable;

  // #region Joystic Button Creation
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
  // #endregion

  public OI() {
    /* Joysticks & Buttons */
    mOpStick = new Joystick(0);
    mCoOpStick = new Joystick(1);

    AlignButton = new JoystickButton(mOpStick, 12);
    AlignButton.toggleWhenPressed(new AlignWithTarget());
    SmartDashboard.putData(AlignButton);

    AlignButtonPID = new JoystickButton(mOpStick, 10);
    AlignButtonPID.whileHeld(new AlignWithTargetPID());
    SmartDashboard.putData(AlignButtonPID);

    FollowButton = new JoystickButton(mOpStick, 11);
    FollowButton.toggleWhenPressed(new FollowTarget());
    SmartDashboard.putData(FollowButton);

    /* Drivetrain */
    SmartDashboard.putData(sDrivetrain);

    /* Vision & NetworkTables */
    mLimeTable = sVision.getTable();
    SmartDashboard.putNumber("limeLightX", sVision.getTargetX());
    SmartDashboard.putNumber("limeLightY", sVision.getTargetY());
    SmartDashboard.putNumber("limeLightArea", sVision.getTargetArea());

  }
  public Joystick getOperatorStick(){
    return mOpStick;
  }
  public Joystick getCoOperatorStick()
  {
    return mCoOpStick;
  }
}
