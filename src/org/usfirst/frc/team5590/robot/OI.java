package org.usfirst.frc.team5590.robot;

import org.usfirst.frc.team5590.robot.commands.ManualLiftDown;
import org.usfirst.frc.team5590.robot.commands.ManualLiftUp;
import org.usfirst.frc.team5590.robot.commands.OpenGrabber;
import org.usfirst.frc.team5590.robot.commands.SuckOut;
import org.usfirst.frc.team5590.robot.controllers.LogitechX3;
import org.usfirst.frc.team5590.robot.controllers.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	// Ports for controllers
	private static final int XBOX_PORT = 0;
	private static final int LOGITECH_PORT = 1;
		
	// Controller objects
	public XboxController xboxController;
	public LogitechX3 logitechController;
	
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
		xboxController = new XboxController(XBOX_PORT);
		logitechController = new LogitechX3(LOGITECH_PORT);
		

		logitechController.button8.whileHeld(new SuckOut());
		logitechController.button7.whileHeld(new OpenGrabber());
		logitechController.button9.whileHeld(new ManualLiftUp());
		logitechController.button5.whileHeld(new ManualLiftDown());
	}
}
