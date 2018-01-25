package org.usfirst.frc.team5590.robot.autonomous;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoStrategy extends CommandGroup {

	private final static Logger logger = Logger.getLogger(AutoStrategy.class.getName());
	
	public boolean driveNearSide;
	public double fastSpeed, slowSpeed;
	public char scaleSide, switchSide;

	public void setValues(boolean driveNearSide, char scaleSide, char switchSide, double fastSpeed, double slowSpeed) {
		this.driveNearSide = driveNearSide;
		this.fastSpeed = fastSpeed;
		this.slowSpeed = slowSpeed;
		this.scaleSide = scaleSide;
		this.switchSide = switchSide;
		logger.info("Using values: {" + this.driveNearSide + ", " + this.fastSpeed + ", " + this.slowSpeed + "}");
	}
	
	public void scheduleCommand() {
		throw new RuntimeException("Override me!");
	}
}
