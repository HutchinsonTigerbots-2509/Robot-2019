package frc.robot; // package declaraition

// imports
import edu.wpi.first.networktables.NetworkTable; 
import edu.wpi.first.wpilibj.Joystick; 
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; 
import frc.robot.subsystems.Vision; 
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AlignWithTarget;
import frc.robot.commands.AlignWithTargetPID;
import frc.robot.commands.Angle_check;
import frc.robot.commands.FollowTarget;
import frc.robot.subsystems.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private Joystick mOpStick;
  private JoystickButton AlignButton;
  private JoystickButton AlignButtonPID;
  private JoystickButton FollowButton;
  private JoystickButton Follow_low_targets_Button;
  private JoystickButton Follow_hatch_Button;
  private JoystickButton Follow_high_targets_Button;
  private JoystickButton Follow_ball_Button;
  
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

    AlignButton = new JoystickButton(mOpStick, 12);
    //AlignButton.toggleWhenPressed(new FollowTarget(0));
    AlignButton.toggleWhenPressed(new FollowTarget(0));
    SmartDashboard.putData(AlignButton);

    AlignButtonPID = new JoystickButton(mOpStick, 10);
    AlignButtonPID.whileHeld(new AlignWithTargetPID());
    SmartDashboard.putData(AlignButtonPID);

    Follow_low_targets_Button = new JoystickButton(mOpStick, 11);
    //mLimeTable.putNumber("pipeline", 0);
    Follow_low_targets_Button.toggleWhenPressed(new FollowTarget(2));
    //SmartDashboard.putData(FollowButton);

    Follow_hatch_Button = new JoystickButton(mOpStick, 13);
    //mLimeTable.putNumber("pipeline", 1);
    Follow_hatch_Button.toggleWhenPressed(new FollowTarget(3));
    //SmartDashboard.putData(FollowButton);

    Follow_high_targets_Button = new JoystickButton(mOpStick, 14);
    //mLimeTable.putNumber("pipeline", 2);
    Follow_high_targets_Button.toggleWhenPressed(new FollowTarget(4));
    //SmartDashboard.putData(FollowButton);
    
    Follow_ball_Button = new JoystickButton(mOpStick, 15);
    //mLimeTable.("pipeline", 3);
    Follow_ball_Button.toggleWhenPressed(new FollowTarget(1));
    //SmartDashboard.putData(FollowButton);

    

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
}
