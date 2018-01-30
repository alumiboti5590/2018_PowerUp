package org.usfirst.frc.team5590.robot.subsystems;

import org.usfirst.frc.team5590.robot.RobotMap;
import org.usfirst.frc.team5590.robot.commands.Collect;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Controls the sucking in and sucking out of the cubes via a belt drive
 */
public class BeltDrive extends Subsystem {
	
	TalonSRX leftMotor = new TalonSRX(RobotMap.TALON_SRX_LEFT_MOTOR);
	TalonSRX rightMotor = new TalonSRX(RobotMap.TALON_SRX_RIGHT_MOTOR);

	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		
		setDefaultCommand(new Collect());
	}

	public void intake() {
		setBeltSpeed(.6);
	}

	// Closes the claw that houses the Power Cube
	public void output() {
		setBeltSpeed(-.6);
	}

	// Deactivates the subsystem
	public void beltStop() {
		setBeltSpeed(0);
	}
	
	private void setBeltSpeed(double speed) {
		
		leftMotor.set(ControlMode.PercentOutput,speed);
		rightMotor.set(ControlMode.PercentOutput,-speed);

	}
}
