package com.team4element.library;

public class JerkFilter {
	
	public JerkFilter(double output, double tolerance){
		filter(output, tolerance);
	}
	
	public double filter(double n, double t) {
		return Math.abs(n) > t ? n : 0;
	}
	
}
