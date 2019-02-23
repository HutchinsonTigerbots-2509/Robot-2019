package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.commands.Angle_check;
import frc.robot.commands.ClimbAlt;
import frc.robot.commands.ClimbEnd;
import frc.robot.commands.ClimbExtend;
import frc.robot.commands.ClimbHab2;
import frc.robot.commands.ClimbRetract;
import frc.robot.commands.DriveShift;
import frc.robot.commands.ElevatorMoveHighGear;
import frc.robot.commands.ElevatorMoveLowGear;
import frc.robot.commands.ElevatorShift;
import frc.robot.commands.Follow_target;
import frc.robot.commands.HeightToggle;
import frc.robot.commands.IntakeBall;
import frc.robot.commands.IntakeHatch;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakeOut;
import frc.robot.commands.PrepareToClimb;
import frc.robot.commands.ResetGyro;
import frc.robot.commands.WristDown;
import frc.robot.commands.WristMove;
import frc.robot.commands.WristUp;
import frc.robot.commands.ZeroElevator;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Vision;


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
  // Intake Buttons
  private JoystickButton mIntakein;
  private JoystickButton mIntakeout;

  // Elevator Buttons
  public JoystickButton mElevatorHigh;
  public JoystickButton mElevatorMid;
  public JoystickButton mElevatorLow;
  public JoystickButton mElevatorHAB;
  private JoystickButton mHeightToggle;
  private JoystickButton mElevatorShift;

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
  private JoystickButton RetractClimbPistons; // Should retract the climb pistons in sequence
  private JoystickButton mPrepareToClimb;
  private JoystickButton mClimb;

  //Intake
private JoystickButton mIntakeStartingPosition;
private JoystickButton mIntakeGroundPosition;
private JoystickButton mIntakeBall;
private JoystickButton mIntakeHatch;

private JoystickButton mButtonNine;

  /* Misc */
  private ShuffleboardTab mCommandTab;
  private Elevator sElevator;
  private Vision sVision;

  private JoystickButton mWristTest;

  private JoystickButton mElevatorManualUp;
  private JoystickButton mElevatorManualDown;
  private JoystickButton mWristManualUp;
  private JoystickButton mWristManualDown;
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

    // Climb 
    RetractClimbPistons = new JoystickButton(mOpStick, 2);
    RetractClimbPistons.whenPressed(new ClimbRetract());

    mClimb = new JoystickButton(mOpStick, 8);
    mClimb.whileHeld(new ClimbAlt(mOpStick));

    // mWristTest = new JoystickButton(mCoOpStick, 8);
    // mWristTest.whenPressed(new WristTest());


    //NEED POV
    // mElevatorManualUp = new JoystickButton(mCoOpStick, 6);
    // mElevatorManualUp.whileHeld(new ElevatorUp());

    // mElevatorManualDown = new JoystickButton(mCoOpStick, 5);
    // mElevatorManualDown.whileHeld(new ElevatorDown());

    // mWristManualUp = new JoystickButton(mCoOpStick, 10);
    // mWristManualUp.whileHeld(new WristManualUp());

    // mWristManualDown = new JoystickButton(mCoOpStick, 9);
    // mWristManualDown.whileHeld(new WristManualDown());    

    //Intake
    //IN OP DRIVE
    // mIntakein = new JoystickButton(mOpStick, 1); //mCoOpStick, 6
    // mIntakein.whileHeld(new IntakeIn());

    // mIntakeout = new JoystickButton(mOpStick, 2);//mCoOpStick, 5
    // mIntakeout.whileHeld(new IntakeOut());
    //IN OP DRIVE

    // mIntakeStartingPosition = new JoystickButton(mCoOpStick, 7);
    // mIntakeStartingPosition.whenPressed(new WristMove(Constants.kWristStartingAngle));//kWristStartingAngle

    mIntakeGroundPosition = new JoystickButton(mCoOpStick, 3);
    mIntakeGroundPosition.whenPressed(new WristMove(Constants.kWristGroundAngle));

    mIntakeBall = new JoystickButton(mCoOpStick, 6);
    mIntakeBall.whileHeld(new IntakeBall());
    mIntakeBall.whenReleased(new WristMove(Constants.kWristCargoAngle));

    mIntakeHatch = new JoystickButton(mCoOpStick, 5);
    mIntakeHatch.whileHeld(new IntakeHatch());
    mIntakeBall.whenReleased(new WristMove(Constants.kWristHatchAngle));

    DriveShifter = new JoystickButton(mOpStick, 10);
    DriveShifter.whenPressed(new DriveShift());
    
    mPrepareToClimb = new JoystickButton(mCoOpStick, 8);
    mPrepareToClimb.whileHeld(new PrepareToClimb());

    // mHeightToggle = new JoystickButton(mCoOpStick, 2);
    // mHeightToggle.whenPressed(new HeightToggle());

    mElevatorHigh = new JoystickButton(mCoOpStick, 4);
    mElevatorMid = new JoystickButton(mCoOpStick, 2);
    mElevatorLow = new JoystickButton(mCoOpStick, 1);
    setElevatorButtonsHatch();

    mElevatorShift = new JoystickButton(mOpStick, 7);
    mElevatorShift.whenPressed(new ElevatorShift());

    UpdateCommands();
    WristUp = new JoystickButton(mCoOpStick, 1);
    WristUp.whileHeld(new WristUp());

    WristDown = new JoystickButton(mCoOpStick, 2);
    WristDown.whileHeld(new WristDown());

    IntakeIn = new JoystickButton(mCoOpStick, 3);
    IntakeIn.whileHeld(new IntakeIn());

    IntakeOut = new JoystickButton(mCoOpStick, 4);
    IntakeOut.whileHeld(new IntakeOut());

    //AT CLIMB RIGHT NOW FOR TESTING
    ElevatorUp = new JoystickButton(mOpStick, 5);
    ElevatorUp.whenPressed(new ClimbHab2());

    ElevatorDown = new JoystickButton(mOpStick, 6);
    ElevatorDown.whenPressed(new ClimbEnd());
    //AT CLIMB RIGHT NOW FOR TESTING
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

    mCommandTab.add("Wrist -30", new WristMove(-30));
    mCommandTab.add("Wrist -90", new WristMove(-90));
    mCommandTab.add("Wrist 0", new WristMove(0));
    mCommandTab.add("Wrist 20", new WristMove(20));

    //Vision
    mCommandTab.add("Align",new Follow_target(0, -0.1, -0.009));
    mCommandTab.add("Distance Calculated", new Angle_check());
    mCommandTab.add("Follow Ball", new Follow_target(2, -.03 , -0.02));
    mCommandTab.add("Follow Hatch", new Follow_target(4, -0.02, -0.02));
    mCommandTab.add("Follow Tape", new Follow_target(1, -0.05, -.02));
    // mCommandTab.add("Follow Low Targets",);
    // mCommandTab.add("Follow High Targets", );
    
  }

  /**
   * Sets the Joystick Buttons for the Elevator Rise
   *  to be the Hatch Heights
   */ 
  public void setElevatorButtonsHatch(){
    sElevator.state = "Hatch";
    // mElevatorHigh.whenPressed(new ElevatorWristMove(Constants.kWristHatchAngle, Constants.kHatchHigh));
    // mElevatorMid.whenPressed(new ElevatorWristMove(Constants.kWristHatchAngle, Constants.kHatchMid));
    // mElevatorLow.whenPressed(new ElevatorWristMove(Constants.kWristHatchAngle, Constants.kHatchLow));
    mElevatorHigh.whenPressed(new ElevatorMoveHighGear(Constants.kHatchHigh));
    mElevatorMid.whenPressed(new ElevatorMoveHighGear(Constants.kHatchMid));
    mElevatorLow.whenPressed(new ElevatorMoveHighGear(Constants.kHatchLow));
  }

  /**
   * Sets the Joystick Buttons for the Elevator Rise
   *  to be the Cargo Heights
   */ 
  public void setElevatorButtonsCargo(){
    sElevator.state = "Cargo";
    // mElevatorHigh.whenPressed(new ElevatorWristMove(Constants.kWristCargoAngle, Constants.kBallHigh));
    // mElevatorMid.whenPressed(new ElevatorWristMove(Constants.kWristCargoAngle, Constants.kBallMid));
    // mElevatorLow.whenPressed(new ElevatorWristMove(Constants.kWristCargoAngle, Constants.kBallLow));
    mElevatorHigh.whenPressed(new ElevatorMoveHighGear(Constants.kBallHigh));
    mElevatorMid.whenPressed(new ElevatorMoveHighGear(Constants.kBallMid));
    mElevatorLow.whenPressed(new ElevatorMoveHighGear(Constants.kBallLow));
  }
}
