package org.usfirst.frc.team5590.robot.autonomous;

import java.util.logging.Logger;

import org.usfirst.frc.team5590.robot.Robot;
import org.usfirst.frc.team5590.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnAngle extends Command {

	// Command basics: Logging and state
	private final static Logger logger = Logger.getLogger(TurnAngle.class.getName());
	protected enum State {
		INITIALIZING,
		PERFORMING,
		CLEANING,
		COMPLETE
	}
	private State state = State.INITIALIZING;

	private double speed = .5;
	private double degrees = 0;
	
	// Sampling to get exact
	
	private static final int DESIRED_SAMPLES = 5;
	private int validSamples = 0;
	private double tolerance = .6;
	

	public TurnAngle(double degrees) {
		if (Math.abs(degrees) > 180) {
			degrees = degrees % 360;
		}
		this.degrees = degrees;
		requires(Robot.drivetrain);
	}
	
	public TurnAngle(double degrees, double tolerance) {
		if (Math.abs(degrees) > 180) {
			degrees = degrees % 360;
		}
		this.degrees = degrees;
		this.tolerance = tolerance;
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		logger.info("Initializing TurnAngle Command.");
		Robot.drivetrain.stop();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch (this.state) {
		
		case INITIALIZING:
			if (Robot.drivetrain.recalibrate()) this.state = State.PERFORMING;
			logger.info("Angle Initialized to " + Drivetrain.gyro.getAngle());
			break;
			
		case PERFORMING:
			// Sample whether our robot is in the correct position or not.
			// Reset the samples if out of position
			double accurateSpeed = validSamples > 0 ? this.speed / 3 : this.speed;
			if (Robot.drivetrain.turn(degrees, accurateSpeed, tolerance)) {
				++validSamples;
			} else {
				validSamples = 0;
			}
			
			// If we have hit our correct sample rate
			if (validSamples >= DESIRED_SAMPLES) this.state = State.CLEANING;
			break;
			
		case CLEANING:
			if (Robot.drivetrain.recalibrate()) this.state = State.COMPLETE;
			break;
			
		default:
			Robot.drivetrain.stop();
			this.state = State.COMPLETE;
		}
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
