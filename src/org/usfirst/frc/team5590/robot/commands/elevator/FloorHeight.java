package org.usfirst.frc.team5590.robot.commands.elevator;

import java.util.logging.Logger;

import org.usfirst.frc.team5590.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FloorHeight extends Command {
	private final static Logger logger = Logger.getLogger(FloorHeight.class.getName());

	protected enum State {
		STARTING, PERFORMING, STOP, COMPLETE
	}

	private State state = State.STARTING;
	private double speed = .7;
	private double distance = 0;

	//Sampling?
	private static final int DESIRED_SAMPLES = 40;
	private int validSamples = 0;
	private double tolerance = 7;

	public FloorHeight(double distance) {
		this.distance = distance;
		requires(Robot.elevator);
	}

	public FloorHeight(double distance, double speed) {
		this.speed = speed;
		this.distance = distance;
		requires(Robot.elevator);
	}

	public FloorHeight(double distance, double speed, double tolerance) {
		this.speed = speed;
		this.distance = distance;
		this.tolerance = tolerance;
		requires(Robot.elevator);
	}

	// Called just before this Command runs the first time
	protected void initalizing() {
		logger.info("Initializing floor height comand.");
		Robot.elevator.StopLift();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
    	switch (this.state) {
    	
    	case STARTING:
    		if (Robot.elevator.()) 
    		this.state = State.PERFORMING;
    			
    	case PERFORMING:
    		
    		
    	case STOP:
    		
    		
    	case COMPLETE:
    		
    		
    	}
    	
    	
    }

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
