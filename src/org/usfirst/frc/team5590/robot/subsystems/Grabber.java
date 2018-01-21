package org.usfirst.frc.team5590.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
//**MUST IMPORT FOR TALONPORT**

/*
 * 
 */
public class Grabber extends Subsystem{
	
	private static final int SOLENOID_IN = 2;
	private static final int SOLENOID_OUT = 3;
	private static final int TALONPORT= 4;
	
	private static final double SPINNER_SPEED = .7;
	
	DoubleSolenoid grabberSolenoid;
	SpeedController spinSpeed;

	public Grabber() {
		grabberSolenoid = new DoubleSolenoid(SOLENOID_IN,SOLENOID_OUT);
		spinSpeed = new TalonSRX(TALONPORT);
	}
	
	public void initDefaultCommand(){
		//Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		clawsOpen();
	}
	
	// Opens the doors that hold in the gear
	public void clawsOpen(){
		grabberSolenoid.set(DoubleSolenoid.Value.kForward);

	}
	
	// Closes the doors that hold in the gear
	public void clawsClose(){
		grabberSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	// Deactivates the subsystem
	public void grabberOff(){
		grabberSolenoid.set(DoubleSolenoid.Value.kOff);
		spinSpeed.stopMotor();
	}
	
	public void spinnerIn() {
		
		double validSpeed;
		
		validSpeed = ensureRange(-SPINNER_SPEED, -1, 1);
		spinSpeed.set(validSpeed);
		
	}
	
	public void spinnerOut() {
		
		double validSpeed;
			
		validSpeed = ensureRange(SPINNER_SPEED, -1, 1);
	    spinSpeed.set(validSpeed);
	
	}
	
	public void spinnerStop() {
		
		double validSpeed;
		
		validSpeed = ensureRange(0, -1, 1);
	    spinSpeed.set(validSpeed);
	
	}
	
	private double ensureRange(double value, double min, double max) {
		return Math.min(Math.max(value, min), max);
	}

	
	
}