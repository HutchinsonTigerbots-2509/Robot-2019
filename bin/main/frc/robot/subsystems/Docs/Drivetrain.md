DriveTrain Subsystem
=====
The Drivetrain subsystem includes the motors and other object pertaining to driving. These methods control driving of the robot. That includes TeleOperated Mode (where the driver is in control) and the Autonomous Mode of the Robot. However, the autonoumous methods aren't really used unless the method takes an input from the camera.

### Components Info
| Name            | Type            | Port    |
| --------------- | --------------- |:-------:|
| *Left Master*   | TalonSRX        |  __0__  |
| *Left Slave*    | VictorSPX       |  __1__  |
| *Right Master*  | TalonSRX        |  __2__  |
| *Right Slave*   | VictorSPX       |  __3__  |
| *DT Shifter*    | Double Solenoid | __0,1__ |
| *Left Encoder*  | TalonSRX Quad   | __N/A__ |
| *Right Encoder* | TalonSRX Quad   | __N/A__ |

*Note: More can be found [here](https://docs.google.com/spreadsheets/d/1FEBEgIgFHLcY4xUZjEkiHl1moupbKuoPvh55APKpakg/edit?usp=sharing)*
##
-----
<!-- @see Constants.java -->
<!-- @see Drivetrain Category - PID Controller -->
### PID Info
> A PID equation is a control equation. There are three constants needed; a P, I, and D. For more information, read [this]( https://frc-pdr.readthedocs.io/en/latest/control/pid_control.html)

- PID Turn Info
  - kP = 0.13
  - KI = 0.0 (it doesn't work)
  - kD = 0.275

<p>The Turning Method will take an input from the Gyro to turn to a certain angle. It doesn't use an I varible because the I varible doesn't work well in this scenario. </p>

- PID Aim to Target Info
  - kP = 0.3
  - kI = 0.0
  - kD = 0.0

<p>The Aim to Target Method will take an input from the camera network table (tx) and then turn to a target. Based off of the Case Study in the limelight docs <a href="http://docs.limelightvision.io/en/latest/cs_aiming.html">here</a> </p>

- PID Distance Info
  - kP = 1
  - kI = 0
  - kD = 0

<p> The PID Distance Void will drive the robot to a certain distance and then fine tune adjust to a certain distance that is set with the parameter </p>

##
---------
### JavaDoc Categories
####  Basic Drive Methods

<p> The Basic Drive Methods are the simplest forms of driving, like the OperatorDrive Method. These normally are autonomous. </p>

####  Update Voids
    
<p> These Voids will update values or sensors </p>

####  Shifter
    
<p> All voids pertaining to the Shifter object </p>

#### PID Controller
    
<p> Methods in the drivetrain that use a PID Equation and a sensor input to make an output, like a PID_Turn with a Gyro. </p>

#### DriveTrain Getters
    
<p> Will return the objects of the DriveTrain </p>