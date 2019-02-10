# Robot.java

Robot.java is the main robot class throughout the entire code. This class is called when the robot is told to run. This class contains the places to put autonomous, teleOp, and test modes. It will tell java to create all of the object, voids, and classes and then utilize them during the round. This sheet will show you what the methods are and what they are used for.

### robotInit()

This method will declare the parts, subsystems, OI, and OPDrive objects, as well as update the telementry. The order for declaring is RobotMap -> All Subsystems -> OI -> OPDRive. This allows all of the parts to be initailized first. Then, the methods behind those parts in their subsystems will be initialized next. Next will be the OI because it uses the methods from the subsystems. Finally, the OPDrive command is initialized (for teleOp), as it uses all of the above.

### robotPeriodic()

This method will update values sent to the ShuffleBoard constantly while the robot is active.

### disabledInit()

This will be called when the robot is disabled via the drivestation. This method will only tell Shuffleboard that the robot is disabled.

### disabledPeriodic()

This void will update constantly (like robotPeriodic) when the robot is disabled. This will update values and stop the Scheduler from running.

### autonomousInit()

Will run at the beginning of the autonomous mode of the robot. Isn't used this year as the whole match can be played in teleOp. It will only notify the ShuffleBoard that teleOp mode has been initialized.

### autonomousPeriodic()

This will run constantly during the automous mode of the robot. It only will stop the Scheduler from running.

### teleopInit

This void will run at the beginning of teleOp mode. This will tell the Shuffleboard that teleOp has started. It will also start the OperatorDrive command, which will allow the driver to drive with joystick inputs.

### teleopPeriodic()

Will be called constantly during the teleOp mode. It will update values and keep the scheduler from running.

### testPeriodic()

This method will be called periodically druing the test mode of the robot. We use this to test the robot by being able to send it fake values from components of the robot.