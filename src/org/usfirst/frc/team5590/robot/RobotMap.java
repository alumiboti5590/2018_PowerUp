package org.usfirst.frc.team5590.robot;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	/**
	 * Drivetrain subsystem
	 */
	// PWM Ports
	public static final int DT_LEFT_CONTROLLER_PWM = 1;
	public static final int DT_RIGHT_CONTROLLER_PWM = 0;
	
	// Gyroscope / SPI Port
	public static final int GYROSCOPE_ANALOG_PORT = 0;
	
	// DIO Ports
	public static final int DT_LEFT_ENCODER_SIGNAL_INPUT = 0;
	public static final int DT_LEFT_ENCODER_SIGNAL_OUTPUT = 1;
	
	public static final int DT_RIGHT_ENCODER_SIGNAL_INPUT = 2;
	public static final int DT_RIGHT_ENCODER_SIGNAL_OUTPUT = 3;
	
	/**
	 * Grabber subsystem
	 */
	// PCM Ports
	public static final int GRABBER_SOLENOID_IN = 2;
	public static final int GRABBER_SOLENOID_OUT = 3;
	
	/**
	 * Beltdrive Subsystem
	 */
	// PWM Ports
	public static final int BELTDRIVE_LEFT_MOTOR = 41;
	public static final int BELTDRIVE_RIGHT_MOTOR = 40;
	
	// Analog ports
	public static final int HALT_MOTOR_SWITCH = 3;
	
	/**
	 * Elevator Subsystem
	 */
	// PWM Ports
	public static final int ELEVATOR_TALON_SRX = 5;
	public static final int ELEVATOR_TALON_SRX_ASSIST = 3;		
	
	// DIO Ports
	public static final int ELEVATOR_ENCODER_SIGNAL_INPUT = 4;
	public static final int ELEVATOR_ENCODER_SIGNAL_OUTPUT = 5;
	
	/**
	 * Climber subsystem
	 */
	// PWM Ports
	public static final int CLIMBER_TALON_SRX = 6;
	
	// DIO Ports
	public static final int CLIMBER_ENCODER_SIGNAL_INPUT = 6;
	public static final int CLIMBER_ENCODER_SIGNAL_OUTPUT = 6;
	
}

