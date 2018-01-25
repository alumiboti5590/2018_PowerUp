package org.usfirst.frc.team5590.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Xbox controller with all of the buttons mapped out.
 * This can be reused if the controller is used.
 */
public class XboxController extends Joystick {
	
	// Buttons all around the controller
	public Button buttonA, 
			buttonB,
			buttonX,
			buttonY,
			buttonSelect,
			buttonStart,
			buttonLogo;
	
	// Bumpers are the top two buttons on the back
	// of the controller, above the triggers
	public Button leftBumper;
	public Button rightBumper;	
	
	/**
	 * Constructor with the port the controller was plugged into
	 */
	public XboxController(int port) {
		super(port);
		buttonA = new JoystickButton(this, 1);
		buttonB = new JoystickButton(this, 2);
		buttonX = new JoystickButton(this, 3);
		buttonY = new JoystickButton(this, 4);
		buttonSelect= new JoystickButton(this, 5);
		buttonStart = new JoystickButton(this, 6);
		buttonLogo = new JoystickButton(this, 7);
		leftBumper = new JoystickButton(this, 8);
		rightBumper = new JoystickButton(this, 9);
	}

	/**
	 * Gets the current position of the left trigger, 
	 * where pushed in all the way is a 1.0
	 * @return a value between 0.0 and 1.0
	 */
	public double getLeftTrigger() {
		return this.getRawAxis(2);
	}
	
	/**
	 * Gets the current position of the right trigger, 
	 * where pushed in all the way is a 1.0
	 * @return a value between 0.0 and 1.0
	 */
	public double getRightTrigger() {
		return this.getRawAxis(3);
	}
	
	/**
	 * Gets the current X axis position of the left thumb stick,  <-- or -->
	 * @return a value between -1.0 and 1.0
	 */
	public double getLeftStickX() {
		return this.getRawAxis(0);
	}
	
	/**
	 * Gets the current Y axis position of the left thumb stick,  ^ or v
	 * @return a value between -1.0 and 1.0
	 */
	public double getLeftStickY() {
		return this.getRawAxis(1);
	}
	
	/**
	 * Gets the current X axis position of the right thumb stick,  <-- or -->
	 * @return a value between -1.0 and 1.0
	 */
	public double getRightStickX() {
		return this.getRawAxis(4);
	}
	
	/**
	 * Gets the current Y axis position of the right thumb stick,  ^ or v
	 * @return a value between -1.0 and 1.0
	 */
	public double getRightStickY() {
		return this.getRawAxis(5);
	}
	
	public double getDPadX () {
		return 0;
	}
	
	public double getDPadY () {
		return 0;
	}
	
	public boolean getButtonA() {
		return buttonA.get();
	}
	public boolean getButtonB() {
		return buttonB.get();
	}
	public boolean getButtonX() {
		return buttonX.get();
	}
	public boolean getButtonY() {
		return buttonY.get();
	}
	public boolean getButtonSelect() {
		return buttonSelect.get();
	}
	public boolean getButtonStart() {
		return buttonStart.get();
	}
	public boolean getButtonLogo() {
		return buttonLogo.get();
	}
	
}