
package org.usfirst.frc.team5590.robot;

import org.usfirst.frc.team5590.robot.autonomous.ApproachSwitch;
import org.usfirst.frc.team5590.robot.autonomous.AutoStrategy;
import org.usfirst.frc.team5590.robot.autonomous.LeftApproachScale;
import org.usfirst.frc.team5590.robot.autonomous.RightApproachScale;
import org.usfirst.frc.team5590.robot.subsystems.BeltDrive;
import org.usfirst.frc.team5590.robot.subsystems.Drivetrain;
import org.usfirst.frc.team5590.robot.subsystems.Elevator;
import org.usfirst.frc.team5590.robot.subsystems.Grabber;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	/**
	 * Initialize the subsystems below
	 */
	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Grabber grabber = new Grabber();
	public static final BeltDrive beltdrive = new BeltDrive();
	public static final Elevator elevator = new Elevator();
	
	public static OI oi;  // Input / Output
	
	// Compressor to run pneumatics
	Compressor compressor;

	/**
	 * External Programs and Metrics
	 */
	// Preferences are gathered from the SmartDashboard.jar program.
	// They allow changing of certain values without redeploying.
	public static Preferences preferences;
	
	// The DriverStation program to get the game data (scoring positions) from
	public static DriverStation ds = DriverStation.getInstance();
	String gameData;
	
	// The autonomous command to run. Set in autonomousInit() below.
	AutoStrategy autoCommand = new ApproachSwitch();;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code. It is used to start the camera feed, 
	 * initialize controls, and start the compressor
	 */
	@Override
	public void robotInit() {
		CameraServer.getInstance().startAutomaticCapture();
		oi = new OI();

		compressor = new Compressor();
		// Closed Loop means that the pressure regulator will automatically
		// cut the compressor off when the system hits 120 PSI.
		compressor.setClosedLoopControl(true);
		compressor.start();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {
		if (gameData == null || gameData.length() != 3) {
			gameData = ds.getGameSpecificMessage();
		}
	}

	@Override
	public void disabledPeriodic() {
		if (gameData == null || gameData.length() != 3) {
			gameData = ds.getGameSpecificMessage();
		}
		Scheduler.getInstance().run();
	}

	/**
	 * This gets run initial when autonomous is enabled. It is used
	 * to select and schedule the autonomous
	 */
	@Override
	public void autonomousInit() {
		
		// Get the field configuration
		gameData = ds.getGameSpecificMessage();

		// Some configs for autonomous
		boolean driveNearSide = false;
		double fastSpeed = 1;
		double slowSpeed = .4;
		
		// The game data for what sides are lit up
		char scaleSide = gameData.charAt(1);
		char switchSide = gameData.charAt(0);
		
		// Set the values for the auto strategy
		autoCommand.setValues(driveNearSide, scaleSide, switchSide, fastSpeed, slowSpeed);
		autoCommand.scheduleCommand();
		if (autoCommand != null) {
			autoCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autoCommand != null)
			autoCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		drivetrain.joystickSpeed();
		System.out.println(drivetrain.getEncoderAverage() + " : " +Robot.drivetrain.gyro.getAngle());
	}
}
