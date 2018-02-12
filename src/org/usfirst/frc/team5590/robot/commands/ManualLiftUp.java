package org.usfirst.frc.team5590.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team5590.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualLiftUp extends Command {
	private final static Logger logger = Logger.getLogger(ManualLiftUp.class.getName());

	public ManualLiftUp() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.elevator);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		logger.info("Starting Lift");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.elevator.setSpeed(.5);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.elevator.saveCurrentHeightAsDesired();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.elevator.saveCurrentHeightAsDesired();
	}
}
