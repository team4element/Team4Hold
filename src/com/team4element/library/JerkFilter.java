package com.team4element.library;

public class JerkFilter {

	private static double lastInput;
	private double changeMax = 0.1;
	private double changeMin = 0.1;
	private double deadZone = .1;

	public JerkFilter() {
		lastInput = 0;
	}

	public double filter(double currentInput) {

		currentInput = filterInputs(currentInput, lastInput);
		lastInput = currentInput;
		return currentInput;

	}

	/*
	 * public void setMaxRateOfChange(double changeMax){ this.changeMax =
	 * changeMax; }
	 * 
	 * public void setMinRateOfChange(double changeMin){ this.changeMin =
	 * changeMin; }
	 * 
	 * public void getMaxRateOfChange(){ return changeMax; }
	 * 
	 * public void setMaxRateOfChange(double changeMax){ this.changeMax =
	 * changeMax; }
	 */
	private double filterInputs(double input, double lastSpeed) {

		int sign = getSign(input);

		input = Math.abs(input);
		lastSpeed = Math.abs(lastSpeed);
		if (input > deadZone) {
			if (input > lastSpeed + changeMax) {
				input = lastSpeed + changeMax;
			} else if (input < lastSpeed - changeMin) {
				input = lastSpeed - changeMin;
			}
		}

		return input * sign;
	}

	private int getSign(double number) {
		int sign = 1;
		if (number < 0) {
			sign = -1;
		}

		return sign;
	}
}
