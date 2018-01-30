package org.usfirst.frc.team5590.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team5590.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SuckOut extends Command {
	
	private final static Logger logger = Logger.getLogger(SuckIn.class.getName());

	public SuckOut() {
		requires(Robot.beltdrive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		logger.info("Starting SuckOut");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.beltdrive.output();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.beltdrive.beltStop();
	}
}