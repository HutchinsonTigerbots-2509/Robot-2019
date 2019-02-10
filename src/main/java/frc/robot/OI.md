# OI Class

The OI Class, short for Operator Interface, is the place where physical joysticks/buttons are bound to the commands in the commands folder. That allows the driver(s) to control the robot during the teleOperated period of the match. The buttons are always bound to a command, and they are built to be called a certain way. This class will declare all of the joystick and button objects and will bind commands to them. This class will also update the commands on the SmartDashboard so they can be called during a test.

## Button Information

| Button                       | Joystick   | ## | Command                |
| ---------------------------- | ---------- | -- | ---------------------- |
| mCloseintake                 | mOpStick   | 5  | *IntakeClose.java*     |
| mOpenintake                  | mOpStick   | 6  | *IntakeOpen.java*      |
| mWristdown                   | mCoOpStick | 5  | *WristDown.java*       |
| mWristup                     | mCoOpStick | 6  | *WristUp.java*         |
| mHeightToggle                | mCoOpStick | 2  | *HeightToggle.java*    |
| mElevatorShift               | mOpStick   | 7  | *ElevatorShift.java*   |
| mShifter                     | mOpStick   | 12 | *Shift.java*           |
| AlignButton                  | mOpStick   | 5  | *Follow_target.java*   |
| AlignButtonPID               | mOpStick   | 12 | *AlignWithTarget.java* |
| Follow_hatch_Button          | mOpStick   | 4  | *Follow_target.java*   |
| Distance_Calculated          | mOpStick   | 11 | *Angle_check.java*     |
| Follow_alingment_tape_Button | mOpstick   | 3  | *Follow_target.java*   |
| Reset_gyro                   | mOpStick   | 2  | *ResetGyro.java*       |
| ExtendClimbPistons           | mOpStick   | 0  | *ClimbExtend.java*     |
| RetractClimbPistons          | mOpStick   | 1  | *RestractPistons.java* |

## Joystick Information

| Name | Desc |
| ---------- | --------------------------------------------------------------------------------- |
| mOpStick   | *This joystick is the main joystick responsible for driving and intaking objects* |
| mCoOpStick | *This joystick will mainly control the climbing and elevator*                     |