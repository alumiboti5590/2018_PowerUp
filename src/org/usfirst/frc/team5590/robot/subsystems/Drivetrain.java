package org.usfirst.frc.team5590.robot.subsystems;

import org.usfirst.frc.team5590.robot.Robot;
import org.usfirst.frc.team5590.robot.commands.JoystickDrive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class Drivetrain extends Subsystem {

	/**
	 * Port Mappings to the RoboRio
	 */
	// Drivetrain motors
	private static final int LEFT_CONTROLLER_PWM = 1;
	private static final int RIGHT_CONTROLLER_PWM = 0;

	// Drivetrain
	private static final int GYROSCOPE_ANALOG_PORT = 1;

	/**
	 * Constants
	 */
	// Driving speeds
	private static final double MIN_SPEED = -1.0;
	private static final double MAX_SPEED = 1.0;

	// Gyroscope metrics
	private static final double GYRO_SENSITIVITY = .03;

	// Joystick controller deadzones
	private static final double DEADZONE_TOLERANCE_LEFT = .15;
	private static final double DEADZONE_TOLERANCE_RIGHT = .18;

	/**
	 * Motors and attachments
	 */
	private RobotDrive robotDrive; // Allows for us to drive like a tank
	public Gyro gyro; // Allows for us to measure turn degree

	/**
	 * Constructor for the Drivetrain
	 */
	public Drivetrain() {
		// Create the new Robot Drive system
		robotDrive = new RobotDrive(LEFT_CONTROLLER_PWM, RIGHT_CONTROLLER_PWM);
		robotDrive.setSafetyEnabled(false);
		robotDrive.setExpiration(.1);

		// Create the Gyroscope
		gyro = new AnalogGyro(GYROSCOPE_ANALOG_PORT);
		gyro.reset();
		gyro.calibrate();
		// distanceSensor = new AnalogInput(DISTANCE_SENSOR_INPUT);
	}

	/**
	 * Set the default command for the Drivetrain. If not command is scheduled, then
	 * this command will run. Currently, it is set to drive the robot using the
	 * joysticks.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new JoystickDrive());
	}

	/**
	 * This method will set the speed of the motors in the drivetrain to whatever
	 * *speed* is.
	 */
	public void setSpeed(double leftSpeed, double rightSpeed) {
		robotDrive.tankDrive(leftSpeed, rightSpeed);
	}

	/**
	 * This method will be called to STOP the robot.
	 */
	public void stop() {
		this.setSpeed(0, 0);
	}

	/**
	 * This method will set the speed of the drivetrain motors according to the
	 * joystick controllers.
	 */
	public void joystickSpeed() {

		double left, right;

		if (Robot.oi.xboxController.getRightTrigger() < .5) {
			// Standard drive
			left = -1 * Robot.oi.xboxController.getLeftStickY();
			right = -1 * Robot.oi.xboxController.getRightStickY();
		} else {
			// Invert if the robot is facing with the back forward
			left = Robot.oi.xboxController.getRightStickY();
			right = Robot.oi.xboxController.getLeftStickY();
		}

		// Validate the speeds within the range and remove the deadzone
		double validLeft = ensureDeadzone(this.ensureRange(left, MIN_SPEED, MAX_SPEED), DEADZONE_TOLERANCE_LEFT);
		double validRight = ensureDeadzone(this.ensureRange(right, MIN_SPEED, MAX_SPEED), DEADZONE_TOLERANCE_RIGHT);

		// Set the robot motors
		robotDrive.tankDrive(validLeft, validRight);
	}

	/**
	 * Ensure that a value is within a range
	 * 
	 * @param value:
	 *            the raw value
	 * @param min:
	 *            the min value allowed
	 * @param max:
	 *            the max value allowed
	 * @return: value bounded within [min, max]
	 */
	private double ensureRange(double value, double min, double max) {
		return Math.min(Math.max(value, min), max);
	}

	/**
	 * Sets speed to 0 if its within the tolerance
	 * 
	 * @param speed:
	 *            The raw speed value
	 * @param tolerance:
	 *            The tolerance above and below 0 to allow
	 * @return: Either 0 or the raw speed
	 */
	private double ensureDeadzone(double speed, double tolerance) {
		double directionalSpeed = Math.abs(speed);
		if (directionalSpeed < tolerance) {
			return 0;
		}
		return speed;
	}

}
