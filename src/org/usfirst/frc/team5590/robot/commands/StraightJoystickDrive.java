package org.usfirst.frc.team5590.robot.commands;

import java.util.logging.Logger;

import org.usfirst.frc.team5590.robot.Library;
import org.usfirst.frc.team5590.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StraightJoystickDrive extends Command {

	private final static Logger logger = Logger.getLogger(StraightJoystickDrive.class.getName());

	private final static double tolerance = .2;

	public StraightJoystickDrive() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		logger.info("Initializing Joystick Drive");
		Robot.drivetrain.stop();
		Robot.drivetrain.recalibrate();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double stickValue = Library.ensureDeadzone(Robot.oi.driveController.getLeftStickY(), tolerance);
		// If A Button is held down, drive straight using left trigger
		Robot.drivetrain.driveStraight(-stickValue);
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		logger.info("Joystick Drive Ended");
		Robot.drivetrain.stop();
		Robot.drivetrain.recalibrate();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		logger.info("Joystick Drive Ended");
		Robot.drivetrain.stop();
		Robot.drivetrain.recalibrate();
	}
}
