package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.commands.ChangeState;

public class State {
    public static int HatchState = 0;
    public static int CargoState = 1;
    public int CurrentState;
    public double height = Robot.sElevator.CurrentHeight();
    public State(int CurrentState){
        this.CurrentState = CurrentState;
    }
    public void toggleState(){
        if(this.CurrentState!=HatchState){
            setState(HatchState);
        }else if(this.CurrentState!=CargoState){
            setState(CargoState);
        }
    }
    public void setState(int state){
        if(this.CurrentState!=state){
            new ChangeState(state).start();
            CurrentState = state;
        }
    }
    public String toString(){
        if(CurrentState==HatchState){
            return "Hatch";
        }else if(CurrentState==CargoState){
            return "Cargo";
        }else{
            return "Error";
        }
    }
}