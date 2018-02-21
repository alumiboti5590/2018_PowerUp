package org.usfirst.frc.team5590.robot;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	/*
	 * Prefix Legend: 
	 * 
	 * DT_: Drivetrain
	 * GR_: Grabber
	 * BD_: Beltdrive
	 * EL_: Elevator
	 */
	
	/**
	 * PWM Values
	 */
	public static final int DT_RIGHT_CONTROLLER = 0;
	public static final int DT_LEFT_CONTROLLER  = 1;
	
	/**
	 * DIO Values
	 */
	public static final int DT_LEFT_ENCODER_SIGNAL_INPUT   = 0;
	public static final int DT_LEFT_ENCODER_SIGNAL_OUTPUT  = 1;
	public static final int DT_RIGHT_ENCODER_SIGNAL_INPUT  = 2;
	public static final int DT_RIGHT_ENCODER_SIGNAL_OUTPUT = 3;
	public static final int EL_ENCODER_SIGNAL_INPUT = 4;
	public static final int EL_ENCODER_SIGNAL_OUTPUT = 5;
	
	/**
	 * PCM Values
	 */
	public static final int GR_SOLENOID_IN = 0;
	public static final int GR_SOLENOID_OUT = 1;
	
	/**
	 * CAN Values
	 */
	public static final int BD_RIGHT_TALON = 1;
	public static final int EL_TALON_SRX_ASSIST = 2;
	public static final int BD_LEFT_TALON = 3;
	public static final int EL_TALON_SRX = 4;
}

