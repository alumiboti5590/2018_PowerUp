package org.usfirst.frc.team5590.robot;

public class Library {

	/**
	 * Average an array of doubles
	 * @param data: double array to average
	 * @return the average of data
	 */
	public static double average(double[] data) {
		double sum = 0;
		for (double d : data) sum += d;
		return 1.0d * sum / data.length;
	}
	
	/**
	 * Ensure that a value is within a range
	 * 
	 * @param value: the raw value
	 * @param min: the min value allowed
	 * @param max: the max value allowed
	 * @return: value bounded within [min, max]
	 */
	public static double ensureRange(double value, double min, double max) {
		return Math.min(Math.max(value, min), max);
	}
	
	/**
	 * Checks whether or not a value is within tolerance of a 
	 * certain goal.
	 * @param value: Current value to check if correct
	 * @param goal: Target value to reach
	 * @param tolerance: Error allowed on either side of target
	 * @return boolean on if value is within tolerance of target
	 */
	public static boolean withinTolerance(double value, double goal, double tolerance) {
		return Math.abs(goal - value) <= tolerance;
	}
	
	/**
	 * Handles controller/joystick deadzones. Returns value
	 * of zero if the `speed` is +- within the tolerance.
	 * 
	 * @param speed: The raw speed value
	 * @param tolerance: The tolerance above and below 0 to allow
	 * @return: Either 0 or the raw speed
	 */
	public static double ensureDeadzone(double speed, double tolerance) {
		double directionalSpeed = Math.abs(speed);
		if (directionalSpeed < tolerance) {
			return 0;
		}
		return speed;
	}

}
