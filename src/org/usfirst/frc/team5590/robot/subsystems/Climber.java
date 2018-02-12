package org.usfirst.frc.team5590.robot.subsystems;

import org.usfirst.frc.team5590.robot.Library;
import org.usfirst.frc.team5590.robot.RobotMap;
import org.usfirst.frc.team5590.robot.commands.climber.ClimbToHeight;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	TalonSRX motor = new TalonSRX(RobotMap.CLIMBER_TALON_SRX);
	private double stabilizeSpeed = .1;
	
	public Encoder encoder;
	private static final boolean INVERT_ENCODER = false;
	private static final boolean INVERT_MOTOR = false;
	
	private double desiredHeight = 0;
	
	// 20 pulses per rev
	// .203 pitch
	
	private static final double CLIMB_DISTANCE_PER_PLUSE = .01015;

	public Climber() {
		encoder = new Encoder(RobotMap.CLIMBER_ENCODER_SIGNAL_INPUT, RobotMap.CLIMBER_ENCODER_SIGNAL_OUTPUT,
				INVERT_ENCODER, EncodingType.k2X);
		encoder.setDistancePerPulse(CLIMB_DISTANCE_PER_PLUSE);
		motor.setInverted(INVERT_MOTOR);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new ClimbToHeight(0, .1, 1));
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
	
	public void setSpeed(double speed) {
		this.motor.set(ControlMode.PercentOutput, speed);
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
	
	
}
