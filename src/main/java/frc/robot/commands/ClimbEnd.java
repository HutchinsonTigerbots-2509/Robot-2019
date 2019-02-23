/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
<<<<<<< HEAD:src/main/java/frc/robot/commands/DriveShift.java
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;
=======
import frc.robot.subsystems.Climber;
import frc.robot.Robot;
>>>>>>> origin/DriverReady:src/main/java/frc/robot/commands/ClimbEnd.java

/**
 * Add your docs here.
 */
<<<<<<< HEAD:src/main/java/frc/robot/commands/DriveShift.java
public class DriveShift extends InstantCommand {
  private Drivetrain sDriveTrain = new Drivetrain();
  private boolean isShifted;
=======
public class ClimbEnd extends InstantCommand {
  private final Climber sClimber = Robot.sClimb;
>>>>>>> origin/DriverReady:src/main/java/frc/robot/commands/ClimbEnd.java
  /**
   * Add your docs here.
   */
  public ClimbEnd() {
    super();
<<<<<<< HEAD:src/main/java/frc/robot/commands/DriveShift.java
    requires(sDriveTrain);
=======
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
>>>>>>> origin/DriverReady:src/main/java/frc/robot/commands/ClimbEnd.java
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
<<<<<<< HEAD:src/main/java/frc/robot/commands/DriveShift.java
    if(sDriveTrain.getCurrentShifter() == Value.kReverse) {
      sDriveTrain.shiftToHigh();
      SmartDashboard.putString("Shifted to", "High");
    }
    else if(sDriveTrain.getCurrentShifter() == Value.kForward) {
      sDriveTrain.shiftToLow();
      SmartDashboard.putString("Shifted to", "Low");
    }
=======

    sClimber.RetractStageOne();
    sClimber.RetractStageTwo();
    sClimber.setMotorSpeed(0);

>>>>>>> origin/DriverReady:src/main/java/frc/robot/commands/ClimbEnd.java
  }

}
