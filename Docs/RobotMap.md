# RobotMap.java

RobotMap is the class that stores all of the robot's components. These include the sensors, motors, and pistons that are used in the subsystems of the code. This class creates the objects for the code to use. The objects are all initialized first with the void init (which is called in Robot.robotInit). This makes it easier and cleaner to understand and write code. It also makes it very neat to import the object needed (by just accessing this class). The port numbers for each part are stored in Constants.java.

### DriveTrain Components

| Name            | Type            | Port    |
| --------------- | --------------- |:-------:|
| *Left Master*   | TalonSRX        |  __0__  |
| *Left Slave*    | VictorSPX       |  __1__  |
| *Right Master*  | TalonSRX        |  __2__  |
| *Right Slave*   | VictorSPX       |  __3__  |
| *DT Shifter*    | Double Solenoid | __0,1__ |
| *Left Encoder*  | TalonSRX Quad   | __N/A__ |
| *Right Encoder* | TalonSRX Quad   | __N/A__ |

### Climb Components

| Name               | Type            | Port      |
| ------------------ | --------------- |:---------:|
| *Climb Motor*      | TalonSRX        |   __5__   |
| *High Pistons*     | Double Solenoid | __12,13__ |
| *Low Pistons*      | Double Solenoid | __14,15__ |

### Elevator Comopnents

| Name            | Type            | Port    |
| --------------- | --------------- |:-------:|
| *Spool Master*  | TalonSRX        |  __4__  |
| *Spool Slave*   | VictorSPX       |  __5__  |
| *Ele. Shifter*  | Double Solenoid | __N/A__ |
| *Left Limit*    | Digital Input   |  __0__  |
| *Right Limit*   | Digital Input   |  __1__  |

### Intake Components

| Name                      | Type             | Port         |
| ------------------------- | ---------------- |:------------:|
| *Intake Motor*            | VictorSP         |     __4__    |
| *Wrist Piston*            | Double Solenoid  |    __2,3__   |
| *Intake Open*             | Double Solenoid  |    __4,5__   |
| *Intake HatchOut Left*    | Double Solenoid  |    __8,9__   |
| *Intake HatchOut Right*   | Double Solenoid  |   __10,11__  |