package org.usfirst.frc.team5590.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drop the Climber to the floor
 */
public class FloorHeight extends CommandGroup {
	
	public FloorHeight() {
		addSequential(new LiftToHeight(0, .1, .5));
	}
}
