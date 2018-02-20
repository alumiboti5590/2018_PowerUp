package org.usfirst.frc.team5590.robot.commands.elevator;

import java.util.logging.Logger;

import org.usfirst.frc.team5590.robot.Library;
import org.usfirst.frc.team5590.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualLift extends Command {
	
	private final static Logger logger = Logger.getLogger(ManualLift.class.getName());
	
	private final static double MAX_SPEED = 1;
	
	private static final double STICK_DEADZONE = .2;

    public ManualLift() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.desiredHeight = Robot.elevator.encoder.getDistance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		double stickValue = Library.ensureDeadzone(-Robot.oi.assistController.getLeftStickY(), STICK_DEADZONE);
    		stickValue = Library.ensureRange(stickValue, -MAX_SPEED, MAX_SPEED);
    		
    		if (stickValue != 0) {
    			Robot.elevator.setSpeed(stickValue);
    			Robot.elevator.desiredHeight = Robot.elevator.encoder.getDistance();
    		} else {
    			Robot.elevator.maintainPosition(.2, 2);
    		}
    		
    		
    		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.saveCurrentHeightAsDesired();
    	Robot.elevator.stabilize();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    		Robot.elevator.stabilize();
    		Robot.elevator.saveCurrentHeightAsDesired();
    }
}
