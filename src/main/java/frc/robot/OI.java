package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.commands.DriveShift;
import frc.robot.commands.Manual;
import frc.robot.commands.climb.ClimbAlt;
import frc.robot.commands.climb.ClimbExtend;
import frc.robot.commands.climb.ClimbRetract;
import frc.robot.commands.climb.PrepareToClimb;
import frc.robot.commands.elevator.ElevatorMoveHighGear;
import frc.robot.commands.elevator.ElevatorMoveLowGear;
import frc.robot.commands.elevator.ElevatorShift;
import frc.robot.commands.elevator.ElevatorWristMove;
import frc.robot.commands.elevator.HeightToggle;
import frc.robot.commands.elevator.ZeroElevator;
import frc.robot.commands.intake.IntakeBall;
import frc.robot.commands.intake.IntakeHatchBrush;
import frc.robot.commands.intake.IntakeIn;
import frc.robot.commands.intake.IntakeOut;
import frc.robot.commands.sensors.ResetGyro;
import frc.robot.commands.vision.DistanceCheck;
import frc.robot.commands.vision.FollowTarget;
import frc.robot.commands.wrist.WristMove;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Vision;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  /* JOYSTICK DECLARATIONS */
  private Joystick mOpStick; // The main joystick. Used for driving and driving related commands
  private Joystick mCoOpStick; // Everything else is used here

  /* BUTTON DECLARATIONS */
  // Elevator Buttons
  public JoystickButton mElevatorHigh;
  public JoystickButton mElevatorMid;
  public JoystickButton mElevatorLow;
  public JoystickButton mElevatorHAB;
  private JoystickButton mHeightToggle;
  // Drive Train
  private JoystickButton DriveShifter;
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
  private JoystickButton Creep;
  private JoystickButton ExtendClimbPistons; // Should extend the climb pistons in sequence
  private JoystickButton mRetractClimbPistons; // Should retract the climb pistons in sequence
  private JoystickButton mPrepareToClimb;
  private JoystickButton mClimb;

  //Intake
  private JoystickButton ManualElevatorWrist;
  private JoystickButton IntakeIn;
  private JoystickButton IntakeOut;
  private JoystickButton WristDown;
  private JoystickButton WristUp;
  private JoystickButton mWristStart;
  private JoystickButton ElevatorUp;
  private JoystickButton ElevatorDown;
  private JoystickButton IntakeBall;
  private JoystickButton IntakeHatchBrush;

  /* Misc */
  private ShuffleboardTab mCommandTab;
  private Elevator sElevator;
  private Vision sVision;

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
    /* JOYSTICKS */
    mOpStick = new Joystick(0);
    mCoOpStick = new Joystick(1);
    /* MISC */
    sElevator = Robot.sElevator;
    mCommandTab = Shuffleboard.getTab("Commands");

    /* JOYSTICK BUTTONS */
    // Main Driver Joystick
    DriveShifter = new JoystickButton(mOpStick,10);
    DriveShifter.whenPressed(new DriveShift());

    mClimb = new JoystickButton(mOpStick, 8);
    mClimb.whenPressed(new ClimbAlt(mOpStick));

    mRetractClimbPistons = new JoystickButton(mOpStick, 2);
    mRetractClimbPistons.whenPressed(new ClimbRetract());

    // Co-Driver Joystick
    mElevatorHigh = new JoystickButton(mCoOpStick, 4);
    mElevatorMid = new JoystickButton(mCoOpStick, 2);
    mElevatorLow = new JoystickButton(mCoOpStick, 1);
    setElevatorButtonsHatch();

    ManualElevatorWrist = new JoystickButton(mCoOpStick,9);
    ManualElevatorWrist.toggleWhenPressed(new Manual());

    mWristStart = new JoystickButton(mCoOpStick, 8);
    mWristStart.whenPressed(new WristMove(Constants.kWristStartingAngle));

    mPrepareToClimb = new JoystickButton(mCoOpStick, 7);
    mPrepareToClimb.whenPressed(new PrepareToClimb());

    mHeightToggle = new JoystickButton(mCoOpStick, 3);
    mHeightToggle.whenPressed(new HeightToggle());

    IntakeBall = new JoystickButton(mCoOpStick, 6);
    IntakeBall.whileHeld(new IntakeBall());

    IntakeHatchBrush = new JoystickButton(mCoOpStick, 5);
    IntakeHatchBrush.whileHeld(new IntakeHatchBrush());

    // IntakeIn = new JoystickButton(mCoOpStick, 1); // Trigger
    // IntakeIn.whileHeld(new IntakeIn());

    // IntakeOut = new JoystickButton(mCoOpStick, 1); // Trigger
    // IntakeOut.whileHeld(new IntakeOut());

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
    mCommandTab.add("Drivetrain Shift", new DriveShift());
    mCommandTab.add("Gyro Reset", new ResetGyro());

    //Climb
    mCommandTab.add("Climb Extend", new ClimbExtend());
    mCommandTab.add("Climb Retract", new ClimbRetract());

    //Elevator
    mCommandTab.add("Elevator Hatch High", new ElevatorMoveHighGear(Constants.kHatchHigh));
    mCommandTab.add("Elevator Hatch Mid", new ElevatorMoveHighGear(Constants.kHatchMid));
    mCommandTab.add("Elevator Hatch Low", new ElevatorMoveHighGear(Constants.kHatchLow));
    mCommandTab.add("Elevator Ball High", new ElevatorMoveHighGear(Constants.kBallHigh));
    mCommandTab.add("Elevator Ball Mid", new ElevatorMoveHighGear(Constants.kBallMid));
    mCommandTab.add("Elevator Ball Low", new ElevatorMoveHighGear(Constants.kBallLow));
    mCommandTab.add("Elevator HAB", new ElevatorMoveLowGear(Constants.kHABHeight));
    mCommandTab.add("Elevator 12", new ElevatorMoveHighGear(12));
    mCommandTab.add("Elevator Shift", new ElevatorShift());
    mCommandTab.add("Elevator Hieght", new HeightToggle());
    mCommandTab.add("Elevator Zero", new ZeroElevator());

    //Intake/
    mCommandTab.add("Intake In", new IntakeIn());
    mCommandTab.add("Intake Out", new IntakeOut());

    //Wrist
    mCommandTab.add("Wrist -30", new WristMove(-30));
    mCommandTab.add("Wrist -90", new WristMove(-90));
    mCommandTab.add("Wrist 0", new WristMove(0));
    mCommandTab.add("Wrist 20", new WristMove(20));

    //Vision
    mCommandTab.add("Align",new FollowTarget(0, -0.1, -0.009));
    mCommandTab.add("Distance Calculated", new DistanceCheck());
    mCommandTab.add("Follow Ball", new FollowTarget(2, -.03 , -0.02));
    mCommandTab.add("Follow Hatch", new FollowTarget(4, -0.02, -0.02));
    mCommandTab.add("Follow Tape", new FollowTarget(1, -0.05, -.02));
  }

  /**
   * Sets the Joystick Buttons for the Elevator Rise
   *  to be the Hatch Heights
   */ 
  public void setElevatorButtonsHatch(){
    sElevator.state = "Hatch";
    mElevatorHigh.whenPressed(new ElevatorWristMove(Constants.kWristHatchAngle, Constants.kHatchHigh));
    mElevatorMid.whenPressed(new ElevatorWristMove(Constants.kWristHatchAngle, Constants.kHatchMid));
    mElevatorLow.whenPressed(new ElevatorWristMove(Constants.kWristHatchAngle, Constants.kHatchLow));
    // mElevatorHigh.whenPressed(new ElevatorMoveLowGear(Constants.kHatchHigh));
    // mElevatorMid.whenPressed(new ElevatorMoveLowGear(Constants.kHatchMid));
    // mElevatorLow.whenPressed(new ElevatorMoveLowGear(Constants.kHatchLow));
  }

  /**
   * Sets the Joystick Buttons for the Elevator Rise
   *  to be the Cargo Heights
   */ 
  public void setElevatorButtonsCargo(){
    sElevator.state = "Cargo";
    mElevatorHigh.whenPressed(new ElevatorWristMove(Constants.kWristCargoAngle, Constants.kBallHigh));
    mElevatorMid.whenPressed(new ElevatorWristMove(Constants.kWristCargoAngle, Constants.kBallMid));
    mElevatorLow.whenPressed(new ElevatorWristMove(Constants.kWristCargoAngle, Constants.kBallLow));
    // mElevatorHigh.whenPressed(new ElevatorMoveHighGear(Constants.kBallHigh));
    // mElevatorMid.whenPressed(new ElevatorMoveHighGear(Constants.kBallMid));
    // mElevatorLow.whenPressed(new ElevatorMoveHighGear(Constants.kBallLow));
  }
}
