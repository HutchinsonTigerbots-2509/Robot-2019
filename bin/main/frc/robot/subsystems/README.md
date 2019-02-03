 General Subsystem ReadMe
======

<h4> Subsystems are the smaller collections of components that make up the robot. They will contain physical objects like motors and pistons, but also virtual data like varibles. These are grouped together in a single class, so they can be used and accessed throughout the code. Below are the individual subsystems and a link to their more specific docs. </h4> 

## [DriveTrain](Docs/Drivetrain.md)
This subsystem has all of the voids and methods pertaining to the driving of the robot. Sometimes, these are just basic driving methods, but mainly the methods take an input and pass an output to the motors of the drivetrain.

#

## [Intake](Docs/Intake.md)
This subsystem is the main scoring subsystem of the robot. This subsystem will handle the getting and scoring of the hatch panels and balls. It will do this through the cooperation of pistons and motors. This is moved up and down by the Elevator

#

## [Elevator](Docs/Elevator)
The elevator subsystem handles the lifting and lowering of the robot's scoring mechanism. This subsystem will use a spool of string and a motor to extend upwards towards the set height. This subsystem will move the Intake one up and down in order to score points on the rocket and cargo ship.