package org.usfirst.frc.team5590.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team5590.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Grabber opening via pneumatics 
 */

public class OpenGrabber extends Command {
	
	private final static Logger logger = Logger.getLogger(OpenGrabber.class.getName());

	public OpenGrabber() {
		// Use requires() here to declare subsystem dependencies
		
		requires(Robot.grabber);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		logger.info("Starting OpenGrabber");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.grabber.clawsOpen();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
		logger.info("Ending OpenGrabber");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		
		logger.info("Open Grabber Interrupted");
	}
}