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
	
	public static final int BELTDRIVE_LEFT_MOTOR = 41;
	public static final int BELTDRIVE_RIGHT_MOTOR = 40;
	
	public static final int HALT_MOTOR_SWITCH = 3;
	
	public static final int Talon_SRX_Elevator = 5;
	public static final int Talon_SRX_Elevator_Assist = 3;		
	
	public static final int ELEVATOR_ENCODER_SIGNAL_INPUT = 4;
	public static final int ELEVATOR_ENCODER_SIGNAL_OUTPUT = 5;
	
}

