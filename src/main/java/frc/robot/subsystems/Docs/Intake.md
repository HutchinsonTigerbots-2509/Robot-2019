Intake Subsystem
=====
The Intake Subsystem is the main scoring mechanism on the robot. It will intake balls using a motor and will pick up the hatch by using a system of pistons and the underside of the subsystem. Then, it will be rasied by the Elevator Subsystem to a scoring height, where it will place either the ball or the hatch for points.

### Components Info
| Name                      | Type             | Port         |
| ------------------------- | ---------------- |:------------:|
| *Intake Motor*            | VictorSP         |     __4__    |
| *Wrist Piston*            | Double Solenoid  |    __2,3__   |
| *Intake Open*             | Double Solenoid  |    __4,5__   |
| *Intake HatchOut Left*    | Double Solenoid  |    __8,9__   |
| *Intake HatchOut Right*   | Double Solenoid  |   __10,11__  |

*Note: More can be found [here](https://docs.google.com/spreadsheets/d/1FEBEgIgFHLcY4xUZjEkiHl1moupbKuoPvh55APKpakg/edit?usp=sharing)*
##
-----

### Summary of Actions

#### Hatch Collection

<p> The System will stop whatever is going on with the ball intake, and then lower itself to the ground (hopefully on top of a hatch). It will then raise with the hatch attached by velcro. After driving to the target, the Elevator will raise to the desired height. The hatch will be attached by first attaching the velcro, and then driving away with the eject pistons extended. </p>

#### Ball Collection

<p> The robot will drive up to a ball. The intake arms will open using a piston and then have a motor and belt system attached to wheels  spin inwards, so the ball is sucked in. The robot will then drive to a target, the Elevator will raise to a certain height, and then the ball will be sent out by setting the motors to spin outward. </p>

##
---------

### JavaDoc Categories
####  Hatch

<p> These methods will pretain to the intake of the hatch subsystem. </p>

####  Ball
    
<p> These methods will pretain to the intake of the ball subsystem. </p>

####  General
    
<p> Voids that interact with every part of the intake subsystem (and didn't belong anywhere else) </p>

#### Intake Getters
    
<p> Will return the objects of the Intake subsystem. </p>