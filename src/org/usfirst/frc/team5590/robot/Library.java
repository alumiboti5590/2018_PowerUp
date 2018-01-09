package org.usfirst.frc.team5590.robot;

public class Library {

	public static double average(double[] data) {
		double sum = 0;
		for (double d : data) sum += d;
		return 1.0d * sum / data.length;
	}

}
