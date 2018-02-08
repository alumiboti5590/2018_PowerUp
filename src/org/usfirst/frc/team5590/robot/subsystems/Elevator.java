package org.usfirst.frc.team5590.robot.subsystems;

import org.usfirst.frc.team5590.robot.RobotMap;
import org.usfirst.frc.team5590.robot.commands.StopElevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	TalonSRX leftMotor = new TalonSRX(RobotMap.Talon_SRX_Elevator);
	TalonSRX rightMotor = new TalonSRX(RobotMap.Talon_SRX_Elevator_Assist);
	private double speed = .7; 
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new StopElevator());
    	
    }
    
    public void LiftUp() {
    		setElevatorSpeed(.7);
    				
    }
    
    public void DropDown () {
    		setElevatorSpeed(-.7);
    }
    
    public void StopLift() {
    		setElevatorSpeed(0);
    }
    
    private void setElevatorSpeed(double speed) {
		leftMotor.set(ControlMode.PercentOutput, speed);
		rightMotor.set(ControlMode.PercentOutput, -speed);
	}
    
}


