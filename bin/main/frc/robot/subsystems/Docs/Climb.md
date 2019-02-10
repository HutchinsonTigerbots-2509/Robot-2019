Climb Subsystem
=====

The Climb subsystem is for climbing up onto the different levels of the habitat in the 2019 game. The subsystem uses a 2 stage piston system. A motor is included to drive the robot forward in case can't do that with the drivetrain.

### Components Info
| Name               | Type            | Port      |
| ------------------ | --------------- |:---------:|
| *Climb Motor*      | TalonSRX        |   __5__   |
| *High Pistons*     | Double Solenoid | __12,13__ |
| *Low Pistons*      | Double Solenoid | __14,15__ |

*Note: More can be found [here](https://docs.google.com/spreadsheets/d/1FEBEgIgFHLcY4xUZjEkiHl1moupbKuoPvh55APKpakg/edit?usp=sharing)*

##
-----

### Mechanism Info

<p> The mechanism will use a two stage piston system, with 2 pistons on either side of the robot. The pistons will be accessed by using only one object and splitting the air flow. The pistons work by first firing the lower 2, and then firing the top two. After that, the motor will be used to propel the robot forward in order to finish the climb onto the habitat level. </p>

##
-----

### JavaDoc Categories
####  Climbing Voids

<p> These voids deal with all of the climbing related methods </p>

####  General
    
<p> These voids deal with climbing </p>

####  Climb Getters
    
<p> Will return objects and data of the climb subsystem </p>