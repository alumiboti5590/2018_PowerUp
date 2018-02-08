package org.usfirst.frc.team5590.robot.subsystems;

import org.usfirst.frc.team5590.robot.Library;
import org.usfirst.frc.team5590.robot.RobotMap;
import org.usfirst.frc.team5590.robot.commands.StopElevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
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
	public Encoder encoder;
	private static final boolean INVERT_ENCODER = false;
	private static final double LIFT_DISTANCE_PER_PLUSE = .1;
	
	public Elevator() {
		encoder = new Encoder(RobotMap.ELEVATOR_ENCODER_SIGNAL_INPUT, RobotMap.ELEVATOR_ENCODER_SIGNAL_OUTPUT,
				INVERT_ENCODER, EncodingType.k2X);
		encoder.setDistancePerPulse(LIFT_DISTANCE_PER_PLUSE);
	}
	

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new StopElevator());

	}

	public void LiftUp() {
		setElevatorSpeed(.7);

	}

	public void DropDown() {
		setElevatorSpeed(-.7);
	}

	public void StopLift() {
		setElevatorSpeed(0);
	}

	private void setElevatorSpeed(double speed) {
		leftMotor.set(ControlMode.PercentOutput, speed);
		rightMotor.set(ControlMode.PercentOutput, -speed);
	}

	public boolean LiftHeight(double desired, double speed, double tolerence) {
		double distance = encoder.getDistance();
	
		if (Library.withinTolerance(distance, desired, tolerence)) {
			this.StopLift();
			return true; 
		} else if (distance > desired) {
			this.setElevatorSpeed(speed);
		} else {
			this.setElevatorSpeed(speed);
		}
		return false; 
	}
	
}
