package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.EventImportance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.OperatorDrive;
import frc.robot.commands.intake.IntakeManual;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Vision;
import edu.wpi.first.wpilibj.Compressor;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /* Subsystem Declarations */
  public static Drivetrain sDrivetrain;
  public static Climber sClimb;
  public static Elevator sElevator;
  public static Intake sIntake;
  public static Vision sVision;
  /* OI DECLARATION */
  public static OI oi;
  /* COMMAND DECLARATIONS */
  public static OperatorDrive cOpDrive;
  public static IntakeManual cIntakeManual;
  private static WPI_TalonSRX ElevatorMotor = RobotMap.ElevatorMotorMaster;
  private static WPI_TalonSRX WristMotor = RobotMap.WristMotor;
  public static  Compressor comp;

  public static boolean trigger = false;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    // Shuffleboard.startRecording();//Starts the Shuffleboard recording
    // RobotMap must be initialized first
    
    RobotMap.init();
    // Subsystems must be initialized after RobotMap
    sDrivetrain = new Drivetrain();
    sClimb = new Climber();
    sElevator = new Elevator();
    sIntake = new Intake();
    sVision = new Vision();
    sVision.UpdateLimelightSettings();
    comp = new Compressor();
    // OI must be inialized after Subsystems
    oi = new OI();
    // Commands must be defined after OI
    cOpDrive = new OperatorDrive();
    cIntakeManual = new IntakeManual();
    // private static WPI_TalonSRX ElevatorMotor = RobotMap.ElevatorMotorMaster;
    // private static WPI_TalonSRX WristMotor = RobotMap.WristMotor;
    // Put data on Shuffleboard
    // sDrivetrain.UpdateTelemetry();
    // sVision.UpdateTelemetry();
    Shuffleboard.addEventMarker("Robot Initialized", EventImportance.kHigh);
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
    SmartDashboard.putNumber("Elevator Current Height",sElevator.CurrentTicks());
    SmartDashboard.putNumber("Wrist Current Angle", sIntake.getCurrentAngle());
    // SmartDashboard.putNumber("Elevator Power", sElevator.getMotor().get());
    // SmartDashboard.putNumber("Elevator AMPs", sElevator.getMotor().getOutputCurrent());
    Shuffleboard.update();
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
    Shuffleboard.update();
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
    RobotMap.ElevatorMotorMaster.setSelectedSensorPosition(Constants.kElevatorStartingHeightTicks);
    RobotMap.WristMotor.setSelectedSensorPosition(Constants.kWristStartingTicks);
    if(!cOpDrive.isRunning())cOpDrive.start(); // Tells the TeleOp Command to start  
    comp.stop();
  }
  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run(); // Will run the run() void, which does a bunch of behind the scenes stuff
    sElevator.CheckBottomSwitch();
    sIntake.CheckLimitSwitches();
  }

  @Override
  public void teleopInit() {   
    Shuffleboard.addEventMarker("Tele-Op Initialized", EventImportance.kNormal);
    // This makes sure that the autonomous stops running when teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove this line or comment it out.
    // if (cAutoCommand != null) {
    // cAutoCommand.cancel();
    // }
    if(!cOpDrive.isRunning())cOpDrive.start(); // Tells the TeleOp Command to start
    //if(!cIntakeManual.isRunning())cIntakeManual.start();
    //cIntakeManual.start();
    comp.stop();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("Elevator Current Height",sElevator.CurrentTicks());
    SmartDashboard.putNumber("Wrist Current Angle", sIntake.getCurrentAngle());
    SmartDashboard.putBoolean("State", (sElevator.state == "Hatch") ? true : false);
    // SmartDashboard.putNumber("Elevator Power", sElevator.getMotor().get());
    // //cIntakeManual.start();
    // SmartDashboard.putNumber("Elevator AMPs", sElevator.getMotor().getOutputCurrent());
    // SmartDashboard.putNumber("power", RobotMap.ElevatorMotorMaster.get());
    Scheduler.getInstance().run(); // Will run the run() void, which does a bunch of behind the scenes stuff
    sElevator.CheckBottomSwitch();
    sIntake.CheckLimitSwitches();
    if(oi.getOperatorStick().getRawButton(8)){
      comp.start();
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}