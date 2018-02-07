package org.usfirst.frc.team5590.robot.autonomous.basics;

import java.util.logging.Logger;

import org.usfirst.frc.team5590.robot.Robot;
import org.usfirst.frc.team5590.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DistanceDrive extends Command {

	// Command basics: Logging and state
	private final static Logger logger = Logger.getLogger(DistanceDrive.class.getName());

	protected enum State {
		INITIALIZING, PERFORMING, STOP, STRAIGHTEN, CLEANING, COMPLETE
	}

	private State state = State.INITIALIZING;

	private double speed = .5;
	private double distance = 0;

	// Sampling to get exact

	private static final int DESIRED_SAMPLES = 40;
	private static final int DESIRED_TURN_SAMPLES = 5;
	private int validSamples = 0;
	private double tolerance = 2;

	public DistanceDrive(double distance) {
		this.distance = distance;
		requires(Robot.drivetrain);
	}
	
	public DistanceDrive(double distance, double speed) {
		this.speed = speed;
		this.distance = distance;
		requires(Robot.drivetrain);
	}

	public DistanceDrive(double distance, double speed, double tolerance) {
		this.speed = speed;
		this.distance = distance;
		this.tolerance = tolerance;
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		logger.info("Initializing DriveDistance Command.");
		Robot.drivetrain.stop();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		logger.info("state: " + this.state);
		
		switch (this.state) {

		case INITIALIZING:
			if (Robot.drivetrain.recalibrate())
				this.state = State.PERFORMING;
			logger.info("Drive Distance Initialized to " + Robot.drivetrain.getEncoderAverage());
			break;

		case PERFORMING:
			// Sample whether our robot is in the correct position or not.
			// Reset the samples if out of position
			double accurateSpeed = validSamples > 0 ? -.01 : this.speed;
			if (Robot.drivetrain.driveToDistance(distance, accurateSpeed, tolerance)) {
				++validSamples;
			} else {
				validSamples = 0;
			}

			// If we have hit our correct sample rate
			if (validSamples >= DESIRED_SAMPLES)
				this.state = State.STRAIGHTEN;
			break;
			
		case STOP:
			if (Robot.drivetrain.stop()) {
				this.state = State.CLEANING;
				validSamples = 0;
			}
			break;
		
		case STRAIGHTEN:
			if (Robot.drivetrain.turn(0, .5, 1)) {
				++validSamples;
			} else {
				validSamples = 0;
			}

			// If we have hit our correct sample rate
			if (validSamples >= DESIRED_TURN_SAMPLES)
				this.state = State.CLEANING;
			break;
				
		case CLEANING:
			if (Robot.drivetrain.recalibrate())
				this.state = State.COMPLETE;
			break;
			

		default:
			Robot.drivetrain.stop();
			this.state = State.COMPLETE;
		}

		logger.info("Distance: " + Robot.drivetrain.getEncoderAverage());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return this.state == State.COMPLETE;
	}

	// Called once after isFinished returns true
	protected void end() {
		logger.info("TurnAngle Command Finished.");
		logger.info("Angle Finished at " + Drivetrain.gyro.getAngle());
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}