Elevator Subsystem
=====

The Elevator Subsystem is the class with everything for the lift mechanism on the robot. This includes lifting the intake subsystem up and down. This controls the up and down motion of the scoring system.

### **Components Info**
| Name            | Type            | Port    |
| --------------- | --------------- |:-------:|
| *Spool Master*  | TalonSRX        |  __4__  |
| *Spool Slave*   | VictorSPX       |  __5__  |
| *Ele. Shifter*  | Double Solenoid | __N/A__ |
| *Left Limit*    | Digital Input   |  __0__  |
| *Right Limit*   | Digital Input   |  __1__  |

*Note: More can be found [here](https://docs.google.com/spreadsheets/d/1FEBEgIgFHLcY4xUZjEkiHl1moupbKuoPvh55APKpakg/edit?usp=sharing)*

-----

### **PID Info**
- PID Lift Info -- PIDFinal()
  - kP = 0.15
  - KI = 0.0
  - kD = 4.0

<p> This PID Control will have the lift move up and down as it settles into a target height. This is the main control of the subsystem. </p>

-----

### **Mechanism Info**

This Subsystem works like so:

```
        |         |
       ||         ||
       ||         ||
      |||         |||
      |||         |||
      ||| :-----: |||
  |---------------------|
  |---------------------|
```

This is a robot. The towers on the side are sliders that move up and down (the line in the middle is the intake subsystem). There is a spool on the bottem that is connected to the towers through a system of pulleys. When the spool spins, this happens:

```
          | :-----: |
          |         |
         ||         ||
         ||         ||
         |           |
         |           |
        ||           ||
        |            ||
        |             |
        |             |
     | ----------------- |
     | ----------------- |
```

Which lifts the intake subsystem. This allows us to score at varying heights on the rocket and cargo ship.

##
---------

### **JavaDoc Categories**
#### Lift Methods
    
<p> The Lift Methods are for lifting. These will lift the mechansism either up or down with the spool motors. </p>

#### Shifter

<p> All voids pertaining to the Shifter object for the Elevator </p>

#### Update Voids

<p> These Voids will update values or sensors </p>

#### Elevator Getters

<p> Will return objects or data </p>