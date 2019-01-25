# :sparkles: :rocket: FRC 2019 :rocket: :sparkles:

Team 2509's 2019 FRC robot code for *Resurgence*. *Resurgence*'s code is written in Java and is based off of WPILib's Java control system.

The code is divided into several packages, each responsible for a different aspect of the robot function. This README explains setup instructions, the function of each package, and some of the variable naming conventions used. Additional information about each specific class can be found in that class' Java file.

## Setup Instructions

### General
- Clone this repo
- Run `./gradlew` to download gradle and needed FRC libraries
- Run `./gradlew tasks` to see available build options
- Enjoy!

### Building/Deploying to the Robot
- Run `./gradlew build` to build the code. Use the `--info` flag for more details
- Run `./gradlew deploy -PteamNumber=2509` to deploy to the robot in Terminal (Mac) or Powershell (Windows)

### Wiring Compontents Diagram

Subsystem | Controller | Name | ID | PDP |
--------- | ---------- | ---- | -- | ---|
Drive     | TalonSRX   | Left Front | 0  | - |
Drive     | TalonSRX   | Left Rear | 1  | - |
Drive     | TalonSRX   | Right Front | 2  | - |
Drive     | TalonSRX   | Right Rear | 3  | - |
Intake    | VictorSP   | Left | 1 | - |
Intake    | VictorSP   | Right | 2 | - |


## Code Highlights

- Building with Gradle

	Instead of working with Ant, we used GradleRIO, which is a powerful Gradle plugin that allows us to build and deploy our code for FRC. It automatically fetches WPILib, CTRE Toolsuite, and other libraries, and is easier to use across different IDEs. 
<!--

- Path following with a nonlinear feedback controller and splines

	To control autonomous driving, the robot utilizes a [nonlinear feedback controller](src/main/java/com/team2509/frc2019/planners/DriveMotionPlanner.java#L263) and drives paths constructed of [quintic Hermite splines](src/main/java/com/team2509/lib/spline/QuinticHermiteSpline.java).

- Path generation and visualization via Java app

	Cheesy Path, a Java webapp, allows a user to quickly and easily create and visualize autonomous paths. It is located in the [`src/main/webapp`](src/main/webapp) directory and the [com.team2509.path](src/main/java/com/team2509/path) package.  Run with `./gradlew tomcatRunWar` and open [`http://localhost:8080`](http://localhost:8080). To stop the server, run `./gradlew tomcatStop`.

- Self-test modes for each subsystem

	Each subsystem contains a [`checkSystem()`](src/main/java/com/team2509/frc2019/subsystems/Drive.java#L464) method that tests motor output currents and RPMs. These self tests allow us to quickly verify that all motors are working properly.

- Scale detection

	[Cheesy Vision 2.0](dash/CheesyVision2.py) is a Python app that uses OpenCV to track the angle of the scale. The app is meant to be run on the driver station computer and uses an external USB webcam pointed through the driver station glass at the scale. This allows us to set our elevator to the right height during autonomous and prevent wasting time by raising it higher than necessary, which we found was needed to complete a 4 cube auto within the time limit.

- Lidar Processing

	Even though this was not used on the final iteration of our robot code, we are still releasing our lidar processing code. This consisted of ICP algorithms to detect the scale within the points detected and sent by the [Slamtec RPLIDAR A2](http://www.slamtec.com/en/support#rplidar-a2) and can be found in the [`frc.robot.lidar`](src/main/java/com/team2509/frc2019/lidar) package. Our RPLIDAR driver can be found [here](https://github.com/Team2509/rplidar_sdk).
-->

## Package Functions
- frc.robot

	Contains the robot's central functions and holds a file with all numerical constants used throughout the code. For example, the `Robot` class controls all routines depending on the robot state.

<!--

- frc.robot.auto

	Handles the execution of autonomous routines and contains the `actions`, `creators`, and `modes` packages.
	
- frc.robot.auto.actions

	Contains all actions used during the autonomous period, which all share a common interface, [`Action`](src/main/java/com/team2509/frc2019/auto/actions/Action.java) (also in this package). Examples include shooting cubes, driving a trajectory, or moving the elevator. Routines interact with the Subsystems, which interact with the hardware.

- frc.robot.auto.creators

	Contains all the auto mode creators, which select the correct auto mode to run based on user input and FMS data.
	
- frc.robot.auto.modes
	
	Contains all autonomous modes. Autonomous modes consist of a list of autonomous actions executed in a certain order.

- frc.robot.controlboard
	
	Contains all the code for the different control boards. This allows any combination of driver station joysticks, button board, and Xbox Controllers to be used for both driving and operating. These are controlled by booleans in `Constants.java`.

- frc.robot.lidar

	Contains classes that are used to communicate with the Slamtec RPLIDAR A2 and to store and process points sent by the lidar.

- frc.robot.lidar.icp

	Contains the algorithms for processing points sent by the lidar.
	
- frc.robot.loops

	Loops are routines that run periodically on the robot, such as calculating robot pose, processing vision feedback, or updating subsystems. All loops implement the `Loop` interface and are handled (started, stopped, added) by the `Looper` class, which runs at 200 Hz.
    The `Robot` class has one main looper, `mEnabledLooper`, that runs all loops when the robot is enabled.
	
- frc.robot.paths

    Contains the generator for all of the trajectories that the robot drives during autonomous period.

- frc.robot.planners

	Loops are routines that run periodically on the robot, such as calculating robot pose, processing vision feedback, or updating subsystems. All loops implement the `Loop` interface and are handled (started, stopped, added) by the `Looper` class, which runs at 200 Hz.
	The `Robot` class has one main looper, `mEnabledLooper`, that runs all loops when the robot is enabled.

- frc.robot.statemachines

    Contains the state machines for the intake and overall superstructure.

- frc.robot.states

    Contains states and other classes used in the subsystem and state machine classes.
-->
- frc.robot.subsystems
	
	Subsystems are consolidated into one central class per subsystem, all of which extend the Subsystem abstract class. Each subsystem uses state machines for control.
	Each subsystem is also a singleton, meaning that there is only one instance of each. To modify a subsystem, one would get the instance of the subsystem and change its state. The `Subsystem` class will work on setting the desired state.

- frc.robot.commands

	Commands define the operation of the robot incorporating the capabilities defined in the subsystems. Commands are subclasses of `Command` or `CommandGroup`. Commands run when scheduled or in response to buttons being pressed or virtual buttons from the `SmartDashboard`.


<!--	

- com.team2509.lib.drivers

    Contains a set of custom classes for TalonSRXs.
	
- com.team2509.lib.geometry

    Contains a set of classes that represent various geometric entities.
	
- com.team2509.lib.physics

    Contains classes that model DC motor transmissions and differential drive characterization.

- com.team2509.lib.spline

    Contains the code for generating and optimizing splines.

- com.team2509.lib.trajectory

    Contains classes for following and storing trajectories.

- com.team2509.lib.trajectory.timing

	Contains classes for fitting trajectories with time profiles.

- com.team2509.lib.util

    A collection of assorted utilities classes used in the robot code. Check each file for more information.
-->

## Variable Naming Conventions
- c*** (i.e. `cAutonomous`): Command instance variables
- k*** (i.e. `kDriveWheelTrackWidthInches`): Final constants, especially those found in the Constants.java file
- m*** (i.e. `mIsHighGear`): Private instance variables
- s*** (i.e. `sDrivetrain`): Subsystems variables, especially those found in Robot.java file

## PID Tuning Method
1. Start by setting `I` and `D` to 0.
2. Increase `P` until the system starts oscillating for a period of `Tu`. You want the oscillation to be large enough that you can time it. This maximum `P` will be referred to as `Ku`.
3. Use the chart below to calculate different `P`, `I`, and `D` values.

Control Types | P | I | D |
------------- | - | - | - |
P | .5*`Ku` | 0 | 0 |
PI | .45*`Ku` | .54*`Ku`/`Tu` | 0 |
PID | .6*`Ku` | 1.2*`Ku`/`Tu` |	3*`Ku`*`Tu`/40 |


## Programmers
* [GoldenGollem](https://github.com/GoldenGollem) :space_invader:
* [CRahne](https://github.com/CRahne) :golf:
* [DScheele1](https://github.com/DScheele1) :tea:
* [ceciliaschmitz1](https://github.com/ceciliaschmitz1)
* [FerisJumbo](https://github.com/FerisJumbo) :ferris_wheel:
* [danevenson13](https://github.com/danevenson13) :muscle:
* [Tjwiseguy](https://github.com/Tjwiseguy) :tiger:

## Dependencies Documents
* [Phoenix](https://phoenix-documentation.readthedocs.io/en/latest/index.html#) :chicken:
* [FRC Programming Done Right](https://frc-pdr.readthedocs.io/en/latest/index.html)