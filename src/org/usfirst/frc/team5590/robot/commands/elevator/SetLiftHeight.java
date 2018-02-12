package org.usfirst.frc.team5590.robot.commands.elevator;

import org.usfirst.frc.team5590.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Use this command to set the elevator to a specific height
 */
public class SetLiftHeight extends Command {
	
	// This will be around 30 inches
	private double desiredRaiseHeight = 1;  // inches
	
	public SetLiftHeight(double height) {
		this.desiredRaiseHeight = height;
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    		Robot.elevator.setDesiredHeight(this.desiredRaiseHeight);
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
    }
}
