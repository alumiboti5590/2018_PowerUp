package org.usfirst.frc.team5590.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team5590.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Collect extends Command {
	
	private final static Logger logger = Logger.getLogger(Collect.class.getName());

	public Collect() {
		requires(Robot.beltdrive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		logger.info("Starting Belt Stop");
		Robot.beltdrive.beltStop();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// fill me in
		if(!Robot.beltdrive.switchTriggered()){
			Robot.beltdrive.intake();
		} else {
			Robot.beltdrive.beltStop();
		}
		// fill me in
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.beltdrive.beltStop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.beltdrive.beltStop();
	}
}