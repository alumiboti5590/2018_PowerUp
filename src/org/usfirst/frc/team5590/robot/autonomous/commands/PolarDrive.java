package org.usfirst.frc.team5590.robot.autonomous.commands;

import org.usfirst.frc.team5590.robot.autonomous.basics.DistanceDrive;
import org.usfirst.frc.team5590.robot.autonomous.basics.TurnAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command to drive using polar coordinates.
 * Use -0 --> 179 for counter clockwise, and
 * use 0 --> 179 for clockwise
 */
public class PolarDrive extends CommandGroup {

    public PolarDrive(double theta, double radius, double speed) {
    	
    		System.out.println("***** Starting Polar Drive *****");
    		if (theta != 0) {
    			addSequential(new TurnAngle(theta));
    		}
    		if (radius != 0) {
    			addSequential(new DistanceDrive(radius, speed));
    		}
    }
}
