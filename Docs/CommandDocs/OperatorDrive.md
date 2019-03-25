# OperatorDrive

Operator Drive is the probably one of the most important commands for teleOp. It will tell the robot to drive using the input from a joystick (by convention, it should be OpStick). It will take an input and convert it to either tankDrive or arcadeDrive format for telling the drivetrain to do something depending what is placed in the Drivetrain subsystem command. This command is called in Robot.teleopInit.

### **Imports**

- **Subsystem**: Drivetrain
- **Joystick**: The OPStick Object from the OI Class

-----

### **initialize**

Will start the operator drive method from Drivetrain.java. It will pass the OPStick for a parameter to this method. Then, the robot will drive based on that input and the type of drive that is called in the void.

```java
public void OperatorDrive(Joystick stick) {
    if (Math.abs(stick.getY()) > 0.1 || Math.abs(stick.getZ()) > 0.1) {
      mDrive.arcadeDrive(-stick.getY(), -stick.getZ());
    } else {
      mDrive.arcadeDrive(0, 0);
    }
  }
```

This one, which is the one we use, will only tell the robot to move if the input is greater than 10 %. That is what the control looop is for. Otherwise, it will set the voltage set to the motors as 0.

-----

### **execute**

Will do the same as the initialize void, but will constantly update the values throughout the match.