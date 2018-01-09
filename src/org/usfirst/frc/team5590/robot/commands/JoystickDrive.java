package org.usfirst.frc.team5590.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5590.robot.Robot;

/**
 * Drives the robot using the joysticks on and
 * the Drivetrain Subsystem
 */
public class JoystickDrive extends Command {

	/*
	 * Initialize the command in Java. This command doesn't
	 * take any arguments, so its a basic constructor.
	 */
	public JoystickDrive() {
		
		// This will override any commands that use the Drivetrain
		requires(Robot.drivetrain);
	}

	/*
	 *  Called just before this Command runs the first time
	 *  
	 *  Start the match and make sure we are stopped.
	 */
	protected void initialize() {
		Robot.drivetrain.stop();
	}

	/*
	 * Executes on a loop every 20ms until the command's
	 * `isFinished` evaluates as true. 
	 * 
	 * Seeing as it always returns false, this will 
	 * always run, meaning the robot drives using 
	 * the joystick positions.
	 */
	protected void execute() {
		Robot.drivetrain.joystickSpeed();
	}
	
	/*
	 * Required for a Command. Since driving will never be
	 * finished, just always return false. 
	 */
	protected boolean isFinished() {
		return false;
	}

	/*
	 *  Called once after isFinished returns true. 
	 *  Stop the robot if something happens
	 */
	protected void end() {
		Robot.drivetrain.stop();
	}

	/*
	 *  Called when another command which requires one or 
	 *  more of the same subsystems is scheduled to run
	 *  
	 *  Stop the robot if something happens
	 */
	protected void interrupted() {
		Robot.drivetrain.stop();
	}
}
