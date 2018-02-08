package org.usfirst.frc.team5590.robot.commands.elevator;

import java.util.logging.Logger;

import org.usfirst.frc.team5590.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FloorHeight extends Command {
	private final static Logger logger = Logger.getLogger(FloorHeight.class.getName());
    public FloorHeight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
