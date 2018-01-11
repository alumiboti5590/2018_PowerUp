package org.usfirst.frc.team5590.robot.commands;

import org.usfirst.frc.team5590.robot.autonomous.DistanceDrive;
import org.usfirst.frc.team5590.robot.autonomous.TurnAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This command group will be our 'testing grounds' for 
 * all of our autonomous commands.
 */
public class AutonomousTest extends CommandGroup {

    public AutonomousTest() {
        addSequential(new TurnAngle(-90));
        addSequential(new DistanceDrive(72, .4));
        //addSequential(new TurnAngle(-45));
    }
}
