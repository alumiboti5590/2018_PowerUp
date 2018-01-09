package org.usfirst.frc.team5590.robot.commands;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5590.robot.Robot;

/**
 * Drives the robot using the joysticks
 */
public class JoystickDrive extends Command {
	
	private final static Logger logger = Logger.getLogger(JoystickDrive.class.getName());

	public JoystickDrive() {
		// This will override any commands that use the drivetrain
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		logger.info("Initializing Joystick Drive");
		Robot.drivetrain.stop();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drivetrain.joystickSpeed();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		logger.info("Joystick Drive Ended");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		logger.info("Joystick Drive Interrupted");
	}
}
