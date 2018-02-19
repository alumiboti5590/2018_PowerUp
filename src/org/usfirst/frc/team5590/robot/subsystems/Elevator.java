package org.usfirst.frc.team5590.robot.subsystems;

import org.usfirst.frc.team5590.robot.Library;
import org.usfirst.frc.team5590.robot.RobotMap;
import org.usfirst.frc.team5590.robot.commands.elevator.ManualLift;

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
	TalonSRX mainMotor = new TalonSRX(RobotMap.ELEVATOR_TALON_SRX);
	TalonSRX assistMotor = new TalonSRX(RobotMap.ELEVATOR_TALON_SRX_ASSIST);
	private double stabilizeSpeed = 0;
	
	public Encoder encoder;
	private static final boolean INVERT_ENCODER = true;
	private static final boolean INVERT_MAIN_MOTOR = false;
	private static final boolean INVERT_ASSIST_MOTOR = false;
	
	public double desiredHeight = 0;
	public double beforeManualHeight = 0;
	
	// 20 pulses per rev
	// .203 pitch
	
	private static final double LIFT_DISTANCE_PER_PLUSE = 0.046061792138574;

	public Elevator() {
		encoder = new Encoder(RobotMap.ELEVATOR_ENCODER_SIGNAL_INPUT, RobotMap.ELEVATOR_ENCODER_SIGNAL_OUTPUT,
				INVERT_ENCODER, EncodingType.k2X);
		encoder.setDistancePerPulse(LIFT_DISTANCE_PER_PLUSE);
		mainMotor.setInverted(INVERT_MAIN_MOTOR);
		assistMotor.setInverted(INVERT_ASSIST_MOTOR);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new ManualLift());
	}
	
	/**
	 * Set the height you want to go to. This is the
	 * only command that needs called for moving the 
	 * climber
	 * @return
	 */
	public void setDesiredHeight(double height) {
		this.desiredHeight = height;
	}
	
	public void saveCurrentHeightAsDesired() {
		this.desiredHeight = encoder.getDistance();
	}
	
	public void setSpeed(double speed) {
		this.mainMotor.set(ControlMode.PercentOutput, speed);
		this.assistMotor.set(ControlMode.PercentOutput, speed);
	}
	
	public void stabilize() {
		this.setSpeed(stabilizeSpeed);
	}
	
	/**
	 * Sampling method. Returns true or false on if the subsystem is where
	 * it should be. It tries to correct itself if not. In a command, call
	 * this until you reach a number of 'trues' which means we are where 
	 * it should be.
	 * @param desiredHeight: How high you want to be from start, in inches.
	 * @param speed: How fast to move
	 * @param tolerance: How much error is allowed? Error must be allowed
	 * @return True or False on if its where it should be
	 */
	public boolean maintainPosition(double speed, double tolerance) {
		
		double currentHeight = encoder.getDistance();
		
		if (Library.withinTolerance(currentHeight, this.desiredHeight, tolerance)) {
			this.stabilize();
			return true;
		}
		
		if (currentHeight < desiredHeight) {
			this.setSpeed(speed);
		} else {
			this.setSpeed(-.05);
		}
		
		return false;
	}
	
public boolean maintainPosition(double height, double speed, double tolerance) {
		
		double currentHeight = encoder.getDistance();
		System.out.println(currentHeight + " : " + height);
		
		if (Library.withinTolerance(currentHeight, height, tolerance)) {
			this.stabilize();
			return true;
		}
		
		if (currentHeight < desiredHeight) {
			this.setSpeed(speed);
		} else {
			this.setSpeed(-speed);
		}
		
		return false;
	}
	
	
	
	
}
