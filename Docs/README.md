General Robot-2019 ReadMe
======

The code written for 2509's 2019 robot for the Deep Space Mission, *Resurgence*. The code is written in java and in command format. It uses the standard WPILIBj library for the code. This is the documentation of the code. It will tell you about the physical robot and about the code.


### [Commands](CommandDocs/README.md)

Commands are the main way we tell the robot what to do. This is the essential part of most command format codes. These will use a method, parts, and/or varibles from a subsystem to 'command' the robot to do something. This allows for use to easily program and tell the robot what to do.


### [Subsystems](SubsystemDocs/README.md)

Subsystems are the smaller collections of components that make up the robot. They will contain physical objects like motors and pistons, but also virtual data like varibles. These are grouped together in a single class, so they can be used and accessed throughout the code. Below are the individual subsystems and a link to their more specific docs. The subsystems are Climb, Drivetrain, Elevator, Intake, and Vision. 


###  [Constants](Constants.md)

The Constants class contains all of the contstants that are used and the same in the code. This makes for easy access and quick changes. These varibles all start with the prefix *k-*, so that they can be easily recognized while reading. This class will lead to cleaner and better organized code.


### [OI](OI.md)

The OI Class (short for Operator Interface) is the main class when it comes to tele-operated code. This code will create buttons and joysticks for the driver to control the robot. We will link the buttons that are created to commands. This allows the driver to run a section of code during the match. It also allows the joystick objects to be accessed throughout the code in different ways, for example OperatorDrive.java.


### [Robot](Robot.md)

The Robot class is the main class used by the code for running the robot. It has methods that will be called by the wpilibj tools when we want the robot to run. This is the main way we control the robot. We can tell what to run and when, along with how (if that makes sense). The robot class will tell the robot what to run when, and in a specific matter. This allows us to start OperatorDrive (very important) and more.


### [RobotMap](RobotMap.md)

The RobotMap Class is the class that contains and initalizes all of the robot's components for the code to use. This class will have all motors, pistons, and sensors that will be used by the subsystems and commands to control the robot. It will define the what the port numbers are for each component, along with declaring them. The port numbers, however, are stored in Constants.java.