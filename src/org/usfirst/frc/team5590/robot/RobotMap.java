package org.usfirst.frc.team5590.robot;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	/**
	 * PWM Ports
	 */
	// Drivetrain Speed Controllers
	public static final int DT_LEFT_CONTROLLER_PWM = 1;
	public static final int DT_RIGHT_CONTROLLER_PWM = 0;

	/**
	 * SPI Ports
	 */
	// Gyroscope
	public static final int GYROSCOPE_ANALOG_PORT = 0;
	
	/**
	 * DIO Ports
	 */
	// Drivetrain
	public static final int DT_LEFT_ENCODER_SIGNAL_INPUT = 0;
	public static final int DT_LEFT_ENCODER_SIGNAL_OUTPUT = 1;
	
	public static final int DT_RIGHT_ENCODER_SIGNAL_INPUT = 2;
	public static final int DT_RIGHT_ENCODER_SIGNAL_OUTPUT = 3;
	
	public static final int GRABBER_SOLENOID_IN = 2;
	public static final int GRABBER_SOLENOID_OUT = 3;
	
	public static final int TALON_SRX_LEFT_MOTOR = 2;
	public static final int TALON_SRX_RIGHT_MOTOR = 3;
	
	public static final int HALT_MOTOR_SWITCH = 3;
	

	
}

