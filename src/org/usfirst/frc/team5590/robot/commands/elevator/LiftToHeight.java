package org.usfirst.frc.team5590.robot.commands.elevator;

import java.util.logging.Logger;

import org.usfirst.frc.team5590.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves the elevator to a specific height. This was FloorHeight, but 
 * now its abstract to handle any height passed to its parameters.
 * Don't actually call me in OI! Use SetLiftHeight
 */
public class LiftToHeight extends Command {
	private final static Logger logger = Logger.getLogger(LiftToHeight.class.getName());

	protected enum State {
		PERFORMING, COMPLETE;
	}

	private State state = State.PERFORMING;
	private double speed = .7;
	private double height = 0;

	// Sampling
	private static final int DESIRED_SAMPLES = 7;
	private int validSamples = 0;
	private double tolerance = 2;

	public LiftToHeight(double height) {
		this.height = height;
		requires(Robot.elevator);
	}

	public LiftToHeight(double height, double speed) {
		this.speed = speed;
		this.height = height;
		requires(Robot.elevator);
	}

	public LiftToHeight(double height, double speed, double tolerance) {
		this.speed = speed;
		this.height = height;
		this.tolerance = tolerance;
		requires(Robot.elevator);
	}

	// Called just before this Command runs the first time
	protected void initalizing() {
		System.out.println("Lifting to " + Robot.elevator.desiredHeight);
		Robot.elevator.setDesiredHeight(height);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		if (Robot.elevator.encoder.getDistance() < height) {
			Robot.elevator.setSpeed(1);
		} else {
			Robot.elevator.maintainPosition(.1, 2);
		}
		
		//Robot.elevator.maintainPosition(height, speed, tolerance);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("Finished lifting :o");
		Robot.elevator.stabilize();  // Small speed to keep where it is
		
	}
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.elevator.stabilize();
	}
}
