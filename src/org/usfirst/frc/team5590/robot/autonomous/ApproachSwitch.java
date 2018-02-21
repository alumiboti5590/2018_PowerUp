package org.usfirst.frc.team5590.robot.autonomous;

import java.util.logging.Logger;

import org.usfirst.frc.team5590.robot.autonomous.commands.LiftOutput;
import org.usfirst.frc.team5590.robot.autonomous.commands.PolarDrive;

/**
 *
 */
public class ApproachSwitch extends AutoStrategy {

private final static Logger logger = Logger.getLogger(ApproachSwitch.class.getName());
	
	private final static double LIFT_HEIGHT = 30;
    
    public void scheduleCommand() {
    	
    		logger.info("************ STARTING AUTONOMOUS ************");
    		
    		/*
    		 * SCALE ON OUR SIDE
    		 */
    		if (this.switchSide == 'L') {
    			leftSideSwitch();
    		} else {
    			rightSideSwitch();
    		}
    		
    }
    
    
    private void leftSideSwitch() {
    		addSequential(new PolarDrive(0, 12, this.slowSpeed, true));
    		addSequential(new PolarDrive(-25, 60, this.slowSpeed, true));
    		addParallel(new LiftOutput(LIFT_HEIGHT));
    		addSequential(new PolarDrive(25, 20, this.slowSpeed, true));
    }
    
    private void rightSideSwitch() {
    		addSequential(new PolarDrive(0, 12, this.slowSpeed, true));
    		addSequential(new PolarDrive(25, 60, this.slowSpeed, true));
    		addParallel(new LiftOutput(LIFT_HEIGHT));
    		addSequential(new PolarDrive(-30, 20, this.slowSpeed, true));
    }
}
