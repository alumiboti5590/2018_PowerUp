package org.usfirst.frc.team5590.robot.subsystems;

import org.usfirst.frc.team5590.robot.Library;
import org.usfirst.frc.team5590.robot.Robot;
import org.usfirst.frc.team5590.robot.RobotMap;
import org.usfirst.frc.team5590.robot.commands.JoystickDrive;
import org.usfirst.frc.team5590.robot.components.ADXRS453Gyro;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drivetrain Subsystem to control the robot chassis
 */
@SuppressWarnings("deprecation")
public class Drivetrain extends Subsystem {

	/**
	 * Constants
	 */
	// Driving speeds
	private static final double MIN_SPEED = -1.0;
	private static final double MAX_SPEED = 1.0;
	private static final double START_SCALE_DOWN_DISTANCE_INCHES = 24;
	
	// How many degrees per pulse the encoder measures
	private static final boolean INVERT_LEFT_ENCODER = false;
	private static final boolean INVERT_RIGHT_ENCODER = true;
	
	// 20 pulse per rev

	// 1364 pulse / sec
	// 10 ft per sec
	// 11.367 inch per pulse 
	private static final double DRIVE_DISTANCE_PER_PULSE = 0.08656;

	// Gyroscope metrics
	private static final double GYRO_SENSITIVITY = .03;
	private static final boolean INVERT_GYRO = true;

	/* Joystick controller deadzones
	 * Since the controllers are never really at 0, we say anything +/-
	 * this value from 0 will be considered stopped.
	 */
	private static final double DEADZONE_TOLERANCE_LEFT = .15;
	private static final double DEADZONE_TOLERANCE_RIGHT = .15;
	private static final double DEADZONE_TRIGGER_INVERT = .5;
	private static final double LEFT_TRACK_MULTIPLIER = .8;
	private static final double RIGHT_TRACK_MULTIPLIER = .8;

	/**
	 * Motors and attachments
	 */
	private RobotDrive robotDrive; // Allows for us to drive like a tank
	public static final ADXRS453Gyro gyro = new ADXRS453Gyro();
	public Encoder leftEncoder;
	public Encoder rightEncoder;
	
	public double prevDistance = 0;

	/**
	 * Constructor for the Drivetrain
	 */
	public Drivetrain() {
		// Create the new Robot Drive system, using the port mappings
		// provided in the RobotMap, so we control the right motors.
		robotDrive = new RobotDrive(
				RobotMap.DT_LEFT_CONTROLLER_PWM,
				RobotMap.DT_RIGHT_CONTROLLER_PWM
		);
		// Disable the Motor Safety Feature
		// https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599705-using-the-motor-safety-feature
		robotDrive.setSafetyEnabled(false);
		robotDrive.setExpiration(.1);
		
		// Create the encoders
		leftEncoder = new Encoder(
			RobotMap.DT_LEFT_ENCODER_SIGNAL_INPUT,
			RobotMap.DT_LEFT_ENCODER_SIGNAL_OUTPUT,
			INVERT_LEFT_ENCODER,
			EncodingType.k2X
		);
		leftEncoder.setDistancePerPulse(DRIVE_DISTANCE_PER_PULSE);
		
		rightEncoder = new Encoder(
			RobotMap.DT_RIGHT_ENCODER_SIGNAL_INPUT,
			RobotMap.DT_RIGHT_ENCODER_SIGNAL_OUTPUT,
			INVERT_RIGHT_ENCODER,
			EncodingType.k2X
		);
		rightEncoder.setDistancePerPulse(DRIVE_DISTANCE_PER_PULSE);

		// Create the Gyroscope
		gyro.startThread();
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
	public boolean stop() {
		this.setSpeed(0, 0);
		
		// Determine if encoders have changed
		double current = getEncoderAverage();
		
		if (Library.withinTolerance(current, prevDistance, .5)) {
			return true;
		}
		
		prevDistance = current;
		return false;
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
		if (Robot.oi.driveController.getRightTrigger() < DEADZONE_TRIGGER_INVERT) {
			// Standard drive using the two thumb knobs
			left = -1 * Robot.oi.driveController.getLeftStickY();
			right = -1 * Robot.oi.driveController.getRightStickY();
		} else {
			// Invert if the robot is facing with the back forward
			// using the two thumb knobs
			left = Robot.oi.driveController.getRightStickY();
			right = Robot.oi.driveController.getLeftStickY();
		}

		// Validate the speeds within the range and remove the deadzone
		left = left * LEFT_TRACK_MULTIPLIER;
		right = right * RIGHT_TRACK_MULTIPLIER;
		
		double validLeft = Library.ensureDeadzone(Library.ensureRange(left, MIN_SPEED, MAX_SPEED), DEADZONE_TOLERANCE_LEFT);
		double validRight = Library.ensureDeadzone(Library.ensureRange(right, MIN_SPEED, MAX_SPEED), DEADZONE_TOLERANCE_RIGHT);

		// Set the robot motors to the deadzoned, ranged, values.)
		robotDrive.tankDrive(validLeft, validRight);
		
	}
	
	/**
	 * This function will turn the robot `degrees` degrees clockwise, setting the motors 
	 * to `speed` and negative speed. It will return TRUE if the current position is within
	 * the `angleTolerance`
	 */
	public boolean turn(double degrees, double speed, double angleTolerance) {
		double angle = gyro.getAngle();
		
		if (Library.withinTolerance(angle, degrees, angleTolerance)) {
			this.stop();
			return true;
			
		} else if (angle > degrees) {
			this.setSpeed(-speed, speed);
			
		} else {
			this.setSpeed(speed, -speed);
		}
		return false;
	}
	
	public boolean driveToDistance(double desiredDistance, double speed, double tolerance) {
		double distance = getEncoderAverage();
		
		double angle = gyro.getAngle() * (INVERT_GYRO ? -1 : 1);
		
		speed = scaleDownSpeed(speed, distance, desiredDistance);
		
		if (Library.withinTolerance(distance, desiredDistance, tolerance)) {
			this.stop();
			return true;
			
		// Still need to go farther
		} else if (distance < desiredDistance) {
			driveStraight(speed);
		// Overdriven
		} else {
			driveStraight(-speed);
		}
		
		return false;
	}
	
	public void driveStraight(double speed) {
		double angle = gyro.getAngle() * (INVERT_GYRO ? -1 : 1);
		if (speed > 0) {
			robotDrive.drive(speed, angle * GYRO_SENSITIVITY);
		} else {
			robotDrive.drive(speed, -angle * GYRO_SENSITIVITY);
		}
		
	}
	
	private double scaleDownSpeed(double speed, double distanceCovered, double desiredDistance) {
		double distance = desiredDistance - distanceCovered;
		double slowDownDistance = START_SCALE_DOWN_DISTANCE_INCHES;
//		if (speed > .5) {
//			slowDownDistance += 12;
//		}
		if (Math.abs(distance) < slowDownDistance) {
			return .2;
		}
		
		return speed;
	}
	
	/**
	 * Centers the robot according to the gyroscope, then resets the 
	 */
	public boolean recalibrate() {		
		// Reset the gyro angle
		gyro.reset();
		leftEncoder.reset();
		rightEncoder.reset();
		return true;
	}
	
	public double getEncoderAverage() {
		return leftEncoder.getDistance(); //Library.average(new double[] {leftEncoder.getDistance(), rightEncoder.getDistance()});
	}

}
