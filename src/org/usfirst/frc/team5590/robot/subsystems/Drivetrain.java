package org.usfirst.frc.team5590.robot.subsystems;

import org.usfirst.frc.team5590.robot.Robot;
import org.usfirst.frc.team5590.robot.RobotMap;
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
	 * Constants
	 */
	// Driving speeds
	private static final double MIN_SPEED = -1.0;
	private static final double MAX_SPEED = 1.0;

	// Gyroscope metrics
	private static final double GYRO_SENSITIVITY = .03;

	/* Joystick controller deadzones
	 * Since the controllers are never really at 0, we say anything +/-
	 * this value from 0 will be considered stopped.
	 */
	private static final double DEADZONE_TOLERANCE_LEFT = .15;
	private static final double DEADZONE_TOLERANCE_RIGHT = .15;
	private static final double DEADZONE_TRIGGER_INVERT = .5;

	/**
	 * Motors and attachments
	 */
	private RobotDrive robotDrive; // Allows for us to drive like a tank
	public Gyro gyro;              // Allows for us to measure turn degree

	/**
	 * Constructor for the Drivetrain
	 */
	public Drivetrain() {
		// Create the new Robot Drive system, using the port mappings
		// provided in the RobotMap, so we control the right motors.
		robotDrive = new RobotDrive(
				RobotMap.DRIVETRAIN_LEFT_CONTROLLER_PWM,
				RobotMap.DRIVETRAIN_RIGHT_CONTROLLER_PWM
		);
		// Disable the Motor Safety Feature
		// https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599705-using-the-motor-safety-feature
		robotDrive.setSafetyEnabled(false);
		robotDrive.setExpiration(.1);

		// Create the Gyroscope, reset the value, and calibrate it
		gyro = new AnalogGyro(RobotMap.DRIVETRAIN_GYROSCOPE_ANALOG_PORT);
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
	 * This method will set the speed of the motors in the drivetrain 
	 * to whatever `speed` is.
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
	 * This method will set the speed of the Drivetrain motors according
	 * to the joystick controllers.
	 */
	public void joystickSpeed() {

		double left, right;  // Initialize the variables for the left and right speed

		/* 
		 * Based on if the right bumper trigger is pressed, switch which
		 * side of the robot is either the front or back, making driving easier.
		 */
		if (Robot.oi.xboxController.getRightTrigger() < DEADZONE_TRIGGER_INVERT) {
			// Standard drive using the two thumb knobs
			left = -1 * Robot.oi.xboxController.getLeftStickY();
			right = -1 * Robot.oi.xboxController.getRightStickY();
		} else {
			// Invert if the robot is facing with the back forward
			// using the two thumb knobs
			left = Robot.oi.xboxController.getRightStickY();
			right = Robot.oi.xboxController.getLeftStickY();
		}

		// Validate the speeds within the range and remove the deadzone
		double validLeft = ensureDeadzone(this.ensureRange(left, MIN_SPEED, MAX_SPEED), DEADZONE_TOLERANCE_LEFT);
		double validRight = ensureDeadzone(this.ensureRange(right, MIN_SPEED, MAX_SPEED), DEADZONE_TOLERANCE_RIGHT);

		// Set the robot motors to the deadzoned, ranged, values.
		robotDrive.tankDrive(validLeft, validRight);
	}

	/**
	 * Ensure that a value is within a range
	 * 
	 * @param value: the raw value
	 * @param min: the min value allowed
	 * @param max: the max value allowed
	 * @return: value bounded within [min, max]
	 */
	private double ensureRange(double value, double min, double max) {
		return Math.min(Math.max(value, min), max);
	}

	/**
	 * Sets speed to 0 if its within the tolerance
	 * 
	 * @param speed: The raw speed value
	 * @param tolerance: The tolerance above and below 0 to allow
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
