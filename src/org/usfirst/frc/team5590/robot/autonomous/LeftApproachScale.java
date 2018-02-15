package org.usfirst.frc.team5590.robot.autonomous;

import java.util.logging.Logger;

import org.usfirst.frc.team5590.robot.autonomous.commands.PolarDrive;

/**
 *
 */
public class LeftApproachScale extends AutoStrategy {
       
    	private final static Logger logger = Logger.getLogger(LeftApproachScale.class.getName());
        
        public void scheduleCommand() {
        	
        		logger.info("************ STARTING AUTONOMOUS ************");
        		
        		/*
        		 * SCALE ON OUR SIDE
        		 */
        		if (this.scaleSide == 'L') {
        			leftSideScale();
        		/*
        		 * SCALE ON OTHER SIDE 
        		 */
        		} else if (this.driveNearSide) {
        			rightSideScaleThruBackWallDrive();
        			
        		} else if (!this.driveNearSide) {
        			rightSideScaleThruSwitchScaleDrive();
        		}
        		
        }
        
        private void leftSideScale() {
        		logger.info("*** DRIVING TO RIGHT SCALE BETWEEN SCALE & SWITCH ***");
    		addSequential(new PolarDrive(0, 288, fastSpeed));
    		addSequential(new PolarDrive(-80, 0, slowSpeed));
        }
        
        private void rightSideScaleThruBackWallDrive() {
        		logger.info("*** DRIVING TO LEFT SCALE BETWEEN WALL & SWITCH ***");
        		addSequential(new PolarDrive(0, 57, slowSpeed));
        		addSequential(new PolarDrive(-90, 230, fastSpeed));
        		addSequential(new PolarDrive(90, 254, fastSpeed));
        		addSequential(new PolarDrive(90, 0, slowSpeed));
        }
        
        private void rightSideScaleThruSwitchScaleDrive() {
        		logger.info("*** DRIVING TO LEFT SCALE BETWEEN SCALE & SWITCH ***");
        		addSequential(new PolarDrive(0, 222, fastSpeed));
        		addSequential(new PolarDrive(-90, 215, fastSpeed));
        		addSequential(new PolarDrive(92, 30, slowSpeed));
        }
        
    }
