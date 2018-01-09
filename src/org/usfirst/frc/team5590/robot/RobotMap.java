package org.usfirst.frc.team5590.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	/**
	 * DriveTrain
	 */
	public static final int DT_LEFT_CONTROLLER_PWM = 1;
	public static final int DT_RIGHT_CONTROLLER_PWM = 0;

	public static final int GYROSCOPE_ANALOG_PORT = 1;
	
	public static final int DT_LEFT_ENCODER_SIGNAL_INPUT = 0;
	public static final int DT_LEFT_ENCODER_SIGNAL_OUTPUT = 1;
	
	public static final int DT_RIGHT_ENCODER_SIGNAL_INPUT = 2;
	public static final int DT_RIGHT_ENCODER_SIGNAL_OUTPUT = 3;
}
