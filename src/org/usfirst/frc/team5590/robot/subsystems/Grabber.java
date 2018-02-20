package org.usfirst.frc.team5590.robot.subsystems;

import org.usfirst.frc.team5590.robot.RobotMap;
import org.usfirst.frc.team5590.robot.commands.ControlGrabber;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/*
 * 
 */
public class Grabber extends Subsystem {
	
	private boolean isOpen = false;

	// Double solenoid can open at both ends
	// allowing opening and closing
	DoubleSolenoid grabberSolenoid;

	public Grabber() {
		
		// Create the solenoid on the right ports
		grabberSolenoid = new DoubleSolenoid(
			RobotMap.GRABBER_SOLENOID_IN, 
			RobotMap.GRABBER_SOLENOID_OUT
		);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		//setDefaultCommand(new CloseGrabber());
		setDefaultCommand(new ControlGrabber());
	}
	
	public void toggleArms() {
		this.isOpen = !this.isOpen;
	}
	
	public void controlArms() {
		if (isOpen) {
			this.clawsOpen();
		} else {
			this.clawsClose();
		}
	}

	// Opens the claw that houses the Power Cube
	public void clawsOpen() {
		grabberSolenoid.set(DoubleSolenoid.Value.kReverse);

	}

	// Closes the claw that houses the Power Cube
	public void clawsClose() {
		grabberSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	// Deactivates the subsystem
	public void grabberOff() {
		grabberSolenoid.set(DoubleSolenoid.Value.kOff);
	}

}
