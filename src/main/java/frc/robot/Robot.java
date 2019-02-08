package frc.robot; // package declaration

// imports

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.EventImportance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.OperatorDrive;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Vision;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /* Subsystem Declarations */
  public static Intake sIntake;
  public static Drivetrain sDrivetrain;
  public static Elevator sElevator;
  public static Vision sVision;

  /* OI DECLARATION */
  public static OI oi;

  /* COMMAND DECLARATIONS */
  public static OperatorDrive cOpDrive;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    // RobotMap must be initialized first
    // because everything else uses it as
    // a reference
    RobotMap.init();
    // Subsystems must be initialized next because commands/OI use
    // the subsystems
    sIntake = new Intake();
    sDrivetrain = new Drivetrain();
    sElevator = new Elevator();
    sVision = new Vision();
    
    // OI must be inialized after Subsystems because OI
    // refrences subsystem objects.
    oi = new OI();
    
    // Commands must be defined after OI
    // This command must be defined after OI because they use
    // the joystick object in the commands
    cOpDrive = new OperatorDrive();

    // Updates data
    sVision.UpdateLimelightSettings();
    Shuffleboard.startRecording();
    sElevator.UpdateTelemetry();
    sDrivetrain.UpdateTelemetry();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    RobotMap.DrivetrainGyro.setAngleAdjustment(90);
    SmartDashboard.putNumber("limeLightSkew", sVision.getTargetSkew());
    SmartDashboard.putNumber("Gyro adjusted", (Math.round(RobotMap.DrivetrainGyro.getYaw()/90)));
    SmartDashboard.putNumber("limelightVert", sVision.getTargetVert());
    SmartDashboard.putNumber("limelightHor", sVision.getTargethor());
    SmartDashboard.putNumber("distance", (86.9 * Math.pow(sVision.getTargetArea(), -0.483)));
 
    /* PUT DATA ON THE SMARTDASHBOARD/SHUFFLEBOADR */
    sElevator.UpdateTelemetry();
    sDrivetrain.UpdateTelemetry();
    sVision.UpdateTelemetry();
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
    Shuffleboard.addEventMarker("Robot Disabled", EventImportance.kHigh);
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run(); // Will run the run() void, which does a bunch of behind the scenes stuff
    sElevator.UpdateTelemetry();
    sDrivetrain.UpdateTelemetry();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    Shuffleboard.addEventMarker("Autonomous Initialized", EventImportance.kNormal);
    // if (cAutoCommand != null) {
    // cAutoCommand.start();
    // }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run(); // Will run the run() void, which does a bunch of behind the scenes stuff
  }

  @Override
  public void teleopInit() {
    Shuffleboard.addEventMarker("Tele-Op Initialized", EventImportance.kNormal);
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    // if (cAutoCommand != null) {
    // cAutoCommand.cancel();
    // }
    cOpDrive.start(); // Tells the TeleOp Command to start
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    SmartDashboard.updateValues();
    Scheduler.getInstance().run(); // Will run the run() void, which does a bunch of behind the scenes stuff
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}