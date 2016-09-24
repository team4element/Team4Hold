package com.team4element.library;

public class DeadZone {

	// Eliminates tiny drift from controllers via adding deadzones
	public static double inputFilter(double n, double t) {
		return Math.abs(n) > t ? n : 0;
	}

}
