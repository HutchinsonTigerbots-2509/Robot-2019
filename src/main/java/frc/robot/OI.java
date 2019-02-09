
package frc.robot; // package declaraition

// imports
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.commands.AlignWithTarget;
import frc.robot.commands.AlignWithTargetPID;
import frc.robot.commands.Angle_check;
import frc.robot.commands.ClimbExtend;
import frc.robot.commands.ElevatorRise;
import frc.robot.commands.ElevatorShift;
import frc.robot.commands.Follow_target;
import frc.robot.commands.IntakeClose;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakeOpen;
import frc.robot.commands.IntakeOut;
import frc.robot.commands.Reset_Gyro;
import frc.robot.commands.RetractPistons;
import frc.robot.commands.Shift;
import frc.robot.commands.WristDown;
import frc.robot.commands.WristUp;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;
import frc.robot.commands.HeightToggle;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  /* JOYSTICK DECLARATIONS */
  private Joystick mOpStick; // The main joystick. Used for driving and driving related commands
  private Joystick mCoOpStick; // Everything else is used here

  /* BUTTON DECLARATIONS */
  // Intake Buttons
  private JoystickButton mCloseintake;
  private JoystickButton mOpenintake;
  private JoystickButton mIntakein;
  private JoystickButton mIntakeout;
  private JoystickButton mWristdown;
  private JoystickButton mWristup;

  // Elevator Buttons
  private JoystickButton mElevatorHigh;
  private JoystickButton mElevatorMid;
  public JoystickButton mElevatorLow;
  private JoystickButton mHeightToggle;
  private JoystickButton mElevatorShift;

  //Drive Train
  private JoystickButton mShifter;

  // Vision Alignment Buttons
  private JoystickButton AlignButton;
  private JoystickButton AlignButtonPID;
  // private JoystickButton Follow_low_targets_Button;
  private JoystickButton Follow_hatch_Button;
  // private JoystickButton Follow_high_targets_Button;
  private JoystickButton Follow_ball_Button;
  private JoystickButton Distance_Calculated;
  private JoystickButton Follow_alingment_tape_Button;
  private JoystickButton Reset_gyro;
  
  // Climb
  private JoystickButton ExtendClimbPistons;
  private JoystickButton RetractClimbPistons;

  /* Misc */
  private ShuffleboardTab mCommandTab;
  private Vision sVision;
  private NetworkTable mLimeTable;

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
   * OI class constructor that will create an object with access to all
   * of the buttons and joysticks
   */
  public OI() {
    sVision  = Robot.sVision;
    mCommandTab = Shuffleboard.getTab("Commands");
    /* Joysticks & Buttons */
    mOpStick = new Joystick(0);
    mCoOpStick = new Joystick(1);

    Distance_Calculated = new JoystickButton(mOpStick, 11);
    Distance_Calculated.whenPressed(new Angle_check());
    //AlignButton.toggleWhenPressed(new FollowTarget(0));
    
    AlignButton = new JoystickButton(mOpStick, 5);
    //AlignButton.toggleWhenPressed(new FollowTarget(0));
    AlignButton.toggleWhenPressed(new Follow_target(0, -0.1, -0.009));

    // AlignButtonPID = new JoystickButton(mOpStick, 10);
    // AlignButtonPID.toggleWhenPressed(new FollowTarget(1));

    // #region Intake Subsystem Buttons
    mCloseintake = new JoystickButton(mOpStick, 5); // Close intake
    mCloseintake.whileHeld(new IntakeClose());
    mCommandTab.add("IntakeClose()", new IntakeClose());

    mOpenintake = new JoystickButton(mOpStick, 6); // Open intake
    mOpenintake.whileHeld(new IntakeOpen());
    mCommandTab.add("IntakeOpen()", new IntakeOpen());

     //Doing this cuz idk its easier
    if(mOpStick.getRawAxis(2) != 0){
      new IntakeIn();
      mCommandTab.add("IntakeIn()", new IntakeIn());
    }
  
    if(mOpStick.getRawAxis(3) != 0){
      new IntakeOut();
      mCommandTab.add("IntakeOut()", new IntakeOut());
    }

    mWristdown = new JoystickButton(mCoOpStick, 5); // Wrist down
    mWristdown.whileHeld(new WristDown());

    mWristup = new JoystickButton(mOpStick, 6); // Wrist up
    mWristup.whileHeld(new WristUp());
    // #endregion
    
    // #region Vision Subsystem
    AlignButton = new JoystickButton(mOpStick, 12);
    AlignButton.toggleWhenPressed(new AlignWithTarget());

    AlignButtonPID = new JoystickButton(mOpStick, 10);
    AlignButtonPID.whileHeld(new AlignWithTargetPID());

    //Follow_low_targets_Button = new JoystickButton(mOpStick, 11);
    //Follow_low_targets_Button.toggleWhenPressed(new FollowTarget(2));

    Follow_hatch_Button = new JoystickButton(mOpStick, 4);
    Follow_hatch_Button.toggleWhenPressed(new Follow_target(4, -0.02, -0.02));

    Follow_alingment_tape_Button = new JoystickButton(mOpStick, 3);
    Follow_alingment_tape_Button.toggleWhenPressed(new Follow_target(1, -0.05, -0.02));
    
    Follow_ball_Button = new JoystickButton(mOpStick, 6);
    Follow_ball_Button.toggleWhenPressed(new Follow_target(2, -0.03, -0.03));

    Reset_gyro = new JoystickButton(mOpStick, 2);
    Reset_gyro.whenPressed(new Reset_Gyro());
    // #endregion
    

    ExtendClimbPistons = new JoystickButton(mOpStick, 0);
    ExtendClimbPistons.whenPressed(new ClimbExtend());

    RetractClimbPistons = new JoystickButton(mOpStick, 1);
    RetractClimbPistons.whenPressed(new RetractPistons());

    /* Drivetrain */
    mShifter = new JoystickButton(mOpStick, 12);
    mShifter.whenPressed(new Shift());
    // #endregion

    // #region Vision & NetworkTables
    mLimeTable = sVision.getTable();
    // SmartDashboard.putNumber("distance", (41.5 * Math.pow(sVision.getTargetArea(), -0.416)));
    
    // #endregion

    // #region Elevator
    mHeightToggle = new JoystickButton(mCoOpStick, 2);
    mHeightToggle.toggleWhenPressed(new HeightToggle());

    mElevatorHigh = new JoystickButton(mCoOpStick, 4);
    mElevatorHigh.whenPressed(new ElevatorRise(mHigh));

    mElevatorMid = new JoystickButton(mCoOpStick, 3);
    mElevatorMid.whenPressed(new ElevatorRise(mMid));

    mElevatorLow = new JoystickButton(mCoOpStick, 1);
    mElevatorLow.whenPressed(new ElevatorRise(mLow));

    mElevatorShift = new JoystickButton(mOpStick, 7);
    mElevatorShift.whenPressed(new ElevatorShift());
    // #endregion
    UpdateCommands();
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
   
  public void UpdateCommands(){

    //Drivetrain
    mCommandTab.add("Drivetrain Shift", new Shift());
    mCommandTab.add("Gyro Reset", new Reset_Gyro());

    //Climb
    mCommandTab.add("Climb Extend", new ClimbExtend());
    mCommandTab.add("Climb Retract", new RetractPistons());

    //Elevator
    mCommandTab.add("Elevator High", new ElevatorRise(mHigh));
    mCommandTab.add("Elevator Mid", new ElevatorRise(mMid));
    mCommandTab.add("Elevator Low", new ElevatorRise(mLow));
    mCommandTab.add("Elevator Shift", new ElevatorShift());
    mCommandTab.add("Elevaotr Hieght", new HeightToggle());

    //Intake
    mCommandTab.add("Intake Close", new IntakeClose());
    mCommandTab.add("Intake Open", new IntakeOpen());
    mCommandTab.add("Intake In", new IntakeIn());
    mCommandTab.add("Intake Out", new IntakeOut());
    mCommandTab.add("Intake Down", new WristDown());
    mCommandTab.add("Intake Up", new WristUp());

    //Vision
    mCommandTab.add("Align",new Follow_target(0, -0.1, -0.009));
    mCommandTab.add("Align PID", new AlignWithTargetPID());
    mCommandTab.add("Distance Calculated", new Angle_check());
    mCommandTab.add("Follow Ball", new Follow_target(2, -.03 , -0.02));
    mCommandTab.add("Follow Hatch", new Follow_target(4, -0.02, -0.02));
    mCommandTab.add("Follow Tape", new Follow_target(1, -0.05, -.02));
    // mCommandTab.add("Follow Low Targets",);
    // mCommandTab.add("Follow High Targets", );
  }
}
