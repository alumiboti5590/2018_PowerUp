package org.usfirst.frc.team5590.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Logitech controller with all of the buttons mapped out.
 * This can be reused if the controller is used.
 */
public class LogitechX3 extends Joystick{
	
	// Instantiate the buttons on the controller
	public Button button1,
			button2,
			button3 ,
			button4,
			button5,
			button6 ,
			button7 ,
			button8 ,
			button9,
			button10,
			button11,
			button12;
		
	// Constructor with the port the controller was plugged into
	public LogitechX3(int port) {
		super(port);
		button1 = new JoystickButton(this, 1);
		button2 = new JoystickButton(this, 2);
		button3 = new JoystickButton(this, 3);
		button4 = new JoystickButton(this, 4);
		button5 = new JoystickButton(this, 5);
		button6 = new JoystickButton(this, 6);
		button7 = new JoystickButton(this, 7);
		button8 = new JoystickButton(this, 8);
		button9 = new JoystickButton(this, 9);
		button10 = new JoystickButton(this, 10);
		button11 = new JoystickButton(this, 11);
		button12 = new JoystickButton(this, 12);	
	}
	
	/**
	 * Get X Axis of the main control stick. <-- or -->
	 * @return a value between -1.0 and 1.0
	 */
	public double getMainStickX() {
		return this.getRawAxis(0);
	}
	
	/**
	 * Get X Axis of the main control stick. ^ or v
	 * @return a value between -1.0 and 1.0
	 */
	public double getMainStickY() {
		return this.getRawAxis(1);
	}
	
	/**
	 * Get Z Axis of the main control stick. (Twist)
	 * A negative number is counter-clockwise
	 * @return a value between -1.0 and 1.0
	 */
	public double getMainStickZ () {
		return this.getRawAxis(2);
	}
	
	/**
	 * Get value of the Angled switch in the front
	 * @return a value between -1.0 and 1.0
	 */
	public double getSliderY() {
		return this.getRawAxis(3);
	}
	public boolean getButton1() {
		return button1.get();
	}
	public boolean getButton2() {
		return button2.get();
	}
	public boolean getButton3() {
		return button3.get();
	}
	public boolean getButton4() {
		return button4.get();
	}
	public boolean getButton5() {
		return button5.get();
	}
	public boolean getButton6() {
		return button6.get();
	}
	public boolean getButton7() {
		return button7.get();
	}
	public boolean getButton8() {
		return button8.get();
	}
	public boolean getButton9() {
		return button9.get();
	}
	public boolean getButton10() {
		return button10.get();
	}
	public boolean getButton11() {
		return button11.get();
	}
	public boolean getButton12() {
		return button12.get();
	}
}