package org.usfirst.frc.team5590.robot;

import org.usfirst.frc.team5590.robot.commands.OpenGrabber;
import org.usfirst.frc.team5590.robot.commands.StraightJoystickDrive;
import org.usfirst.frc.team5590.robot.commands.elevator.LiftToHeight;
import org.usfirst.frc.team5590.robot.commands.elevator.ManualLift;
import org.usfirst.frc.team5590.robot.controllers.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	// Ports for controllers
	private static final int DRIVE_XBOX_PORT = 0;
	private static final int ASSIST_XBOX_PORT = 1;
		
	// Controller objects
	public XboxController driveController;
	public XboxController assistController;
	
	/**
	 * TRIGGERING COMMANDS WITH BUTTONS
	 * Once you have a button, it's trivial to bind it to a button in one of
	 * three ways:

	 * Start the command when the button is pressed and let it run the command
	 * until it is finished as determined by it's isFinished method.
	 * button.whenPressed(new ExampleCommand());

	 * Run the command while the button is being held down and interrupt it once
	 * the button is released.
	 * button.whileHeld(new ExampleCommand());

	 * Start the command when the button is released and let it run the command
	 * until it is finished as determined by it's isFinished method.
	 * button.whenReleased(new ExampleCommand());
	 */
	public OI() {
		driveController = new XboxController(DRIVE_XBOX_PORT);
		assistController = new XboxController(ASSIST_XBOX_PORT);
		
		// Driving
		driveController.buttonA.whileHeld(new StraightJoystickDrive());
		
		// Pneumatics Grabber
		assistController.leftBumper.whileHeld(new OpenGrabber());
		
		// Lift
		assistController.buttonX.whenPressed(new LiftToHeight(0));  // Ground height
		assistController.buttonA.whenPressed(new LiftToHeight(36));  // Ground height
		assistController.buttonB.whenPressed(new LiftToHeight(72));  // Scale height
		assistController.buttonY.whileHeld(new ManualLift());       // Switch height
	}
}
