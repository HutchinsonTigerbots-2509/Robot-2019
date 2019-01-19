/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static VictorSPX motorRight = new VictorSPX(4);
    public static VictorSPX motorLeft = new VictorSPX(5);
    public static SpeedController intakeMotors = new SpeedController(){
        //#region intake
        @Override
        public void pidWrite(double output) {
            
        }
    
        @Override
        public void stopMotor() {
            motorRight.set(ControlMode.PercentOutput, 0.0);
            motorLeft.set(ControlMode.PercentOutput, 0.0);
        }
    
        @Override
        public void setInverted(boolean isInverted) {
            
        }
    
        @Override
        public void set(double speed) {
            motorRight.set(ControlMode.PercentOutput, speed);
            motorLeft.set(ControlMode.PercentOutput, speed);
        }
    
        @Override
        public boolean getInverted() {
            return false;
        }
    
        @Override
        public double get() {
            return 0;
        }
    
        @Override
        public void disable() {
            
        }
        //#endregion
    };
    public static DoubleSolenoid leftPush = new DoubleSolenoid(6,7);
    public static DoubleSolenoid wrist = new DoubleSolenoid(10,11);
    public static DoubleSolenoid open = new DoubleSolenoid(12,13);
}
