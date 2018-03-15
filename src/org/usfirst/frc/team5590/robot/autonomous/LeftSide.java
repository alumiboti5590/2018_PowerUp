package org.usfirst.frc.team5590.robot.autonomous;

import java.util.logging.Logger;

import org.usfirst.frc.team5590.robot.autonomous.commands.LiftOutput;
import org.usfirst.frc.team5590.robot.autonomous.commands.PolarDrive;

/**
 * This auto class assumes we are on the left side of the field.
 */
public class LeftSide extends AutoStrategy {

	private final static Logger logger = Logger.getLogger(LeftSide.class.getName());

	private final static double SCALE_LIFT_HEIGHT = 77;
	private final static double SWITCH_LIFT_HEIGHT = 40; 

	public void scheduleCommand() {

		logger.info("************ STARTING AUTONOMOUS ************");

		/*
		 * SCALE ON OUR SIDE
		 */
		if (this.scaleSide == 'L') {
			leftSideScale();
			/*
			 * SWITCH ON OUR SIDE
			 */
		} else if (this.switchSide == 'L') {
			leftSideSwitch();

		} else {
			driveToAutoZone();
		}

	}

	/**
	 * Attach the scale if its on the same side as the robot.
	 */
	private void leftSideScale() {
		logger.info("*** DRIVING TO LEFT SCALE ***");

		// Drive straight
		addSequential(new PolarDrive(0, 194, fastSpeed));
		
		// Start lifting, while doing that...
		addParallel(new LiftOutput(SCALE_LIFT_HEIGHT));
		
		// Turn slightly left, and drive forward
		addSequential(new PolarDrive(30, 64, .4));
		
		// Turn left a small amount to line up over
		addSequential(new PolarDrive(-5, 0, slowSpeed));
	}
	
	/**
	 * If the scale is opposite side, Attach the switch 
	 * if its on the same side as the robot.
	 */
	private void leftSideSwitch() {
		logger.info("*** DRIVING TO LEFT SWITCH ***");
		
		// Drive straight next to switch
		addSequential(new PolarDrive(0, 160, fastSpeed));
		
		// Start lifting
		addParallel(new LiftOutput(SWITCH_LIFT_HEIGHT));
		
		// Turn 90 degrees to face switch and drive forward a nudge
		addSequential(new PolarDrive(90, 5, slowSpeed));
	}
	
	/**
	 * If both are on the other side, just drive forward.
	 */
	private void driveToAutoZone() {
		logger.info("*** DRIVING TO AUTO ZONE ***");
		
		// Drive straight into auto zone
		addSequential(new PolarDrive(0, 160, fastSpeed));
	}

}
