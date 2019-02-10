# Constants.java

The constants class contains all constants that will be used throughout the entire code. This include port numbers for RobotMap.java to max speeds to PID values for subsystems. This is an important part of our code because it allows us to quickly change and access values. All of the varibles declared in this class start with the prefix *-k*, so the varible is instantly known to be part of constants.

## How to Use

**First,** import the class

```java
package frc.robot;

import frc.robot.Constants;

public class myClass {
    private Constants Constants = new Constants();
}
```

**Second,** Access the varible used in the object created above. Make sure when making the class varible.

```java
package frc.robot;

import frc.robot.Constants;

public class myClass {
    private Constants Constants = new Constants();
    private int kInteger = Constants.kMyClassInteger;
}
```

**Finally,** use that varible in any way you desire. This could be an equation, making a new component, or setting a limit.


```java
package frc.robot;

import frc.robot.Constants;

public class myClass {
    private Constants Constants = new Constants();
    private int kInteger = Constants.kMyClassInteger;

    public myClass() {
        System.out.println(kInteger);
    }
}
```

