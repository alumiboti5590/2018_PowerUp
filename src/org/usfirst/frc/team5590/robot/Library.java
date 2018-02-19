package org.usfirst.frc.team5590.robot;

public class Library {

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
	
	public static boolean withinTolerance(double value, double goal, double tolerance) {
		return Math.abs(goal - value) <= tolerance;
	}
	
	/**
	 * Sets speed to 0 if its within the tolerance
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
