package org.usfirst.frc.team5590.robot.commands;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5590.robot.Library;
import org.usfirst.frc.team5590.robot.Robot;

/**
 * Drives the robot using the joysticks on and the Drivetrain Subsystem
 */
public class JoystickDrive extends Command {

	private final static Logger logger = Logger.getLogger(JoystickDrive.class.getName());
	
	/*
	 * Initialize the command in Java. This command doesn't take any arguments, so
	 * its a basic constructor.
	 */
	public JoystickDrive() {

		// This will override any commands that use the Drivetrain
		requires(Robot.drivetrain);
	}

	/*
	 * Called just before this Command runs the first time
	 * 
	 * Start the match and make sure we are stopped.
	 */
	protected void initialize() {
		logger.info("Initializing Joystick Drive");
		Robot.drivetrain.stop();
		Robot.drivetrain.recalibrate();
	}

	/*
	 * Executes on a loop every 20ms until the command's `isFinished` evaluates as
	 * true.
	 * 
	 * Seeing as it always returns false, this will always run, meaning the robot
	 * drives using the joystick positions.
	 */
	protected void execute() {
		Robot.drivetrain.joystickSpeed();
	}

	/*
	 * Required for a Command. Since driving will never be finished, just always
	 * return false.
	 */
	protected boolean isFinished() {
		return false;
	}

	/*
	 * Called once after isFinished returns true. Stop the robot if something
	 * happens
	 */
	protected void end() {
		logger.info("Joystick Drive Ended");
		Robot.drivetrain.stop();
		Robot.drivetrain.recalibrate();
	}

	/*
	 * Called when another command which requires one or more of the same subsystems
	 * is scheduled to run
	 * 
	 * Stop the robot if something happens
	 */
	protected void interrupted() {
		logger.info("Joystick Drive Interrupted");
		Robot.drivetrain.stop();
		Robot.drivetrain.recalibrate();
	}
}
