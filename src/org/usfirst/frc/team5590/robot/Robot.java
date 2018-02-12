
package org.usfirst.frc.team5590.robot;

import org.usfirst.frc.team5590.robot.autonomous.AutoStrategy;
import org.usfirst.frc.team5590.robot.autonomous.RightApproachScale;
import org.usfirst.frc.team5590.robot.subsystems.BeltDrive;
import org.usfirst.frc.team5590.robot.subsystems.Climber;
import org.usfirst.frc.team5590.robot.subsystems.Drivetrain;
import org.usfirst.frc.team5590.robot.subsystems.Elevator;
import org.usfirst.frc.team5590.robot.subsystems.Grabber;

import edu.wpi.first.wpilibj.CameraServer;
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
	public static final Climber climber = new Climber();
	
	public static Preferences preferences;
	AutoStrategy autonomousCommand;
	public static DriverStation ds = DriverStation.getInstance();
	
	/**
	 * Initialize the Input/Output controllers below
	 */
	public static OI oi;
	
	String gameData;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		CameraServer.getInstance().startAutomaticCapture();
		oi = new OI();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
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
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		// Get the field configuration
		gameData = ds.getGameSpecificMessage();
		
		// Preferences from driver station
		preferences = Preferences.getInstance();
		
		boolean driveNearSide = preferences.getBoolean("CrossBackwallSwitchSide", false);
		double fastSpeed = preferences.getDouble("DriveFastSpeed", .8);
		double slowSpeed = preferences.getDouble("DriveSlowSpeed", .4);
		char scaleSide = gameData.charAt(1);
		char switchSide = gameData.charAt(0);
		String autonomousString = preferences.getString("Autonomous", "RightApproachScale");
		
		switch (autonomousString) {
		default:
			autonomousCommand = new RightApproachScale();
			break;
		}
		
		
		// Schedule and run command
		autonomousCommand.setValues(driveNearSide, scaleSide, switchSide, fastSpeed, slowSpeed);
		autonomousCommand.scheduleCommand();

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
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
		if (autonomousCommand != null)
			autonomousCommand.cancel();
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
		
	}
}
