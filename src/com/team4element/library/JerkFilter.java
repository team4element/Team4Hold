package com.team4element.library;

public class JerkFilter {

	private double lastInput;
	private double changeMax = 0.25;
	private double changeMin = 0.25;

	public JerkFilter() {
		lastInput = 0;
	}

	public double filter(double currentInput) {

		currentInput = filterInputs(currentInput, lastInput);
		lastInput = currentInput;
		return currentInput;

	}

	public void setMaxRateOfChange(double changeMax) {
		this.changeMax = changeMax;
	}

	public void setMinRateOfChange(double changeMin) {
		this.changeMin = changeMin;
	}

	public double getMaxRateOfChange() {
		return changeMax;
	}

	public double getMinRateOfChange() {
		return changeMin;
	}

	private double filterInputs(double input, double lastSpeed) {

		int sign = (int) Math.signum(input);

		input = Math.abs(input);
		lastSpeed = Math.abs(lastSpeed);
		if (input > lastSpeed + changeMax) {
			input = lastSpeed + changeMax;
		} else if (input < lastSpeed - changeMin) {
			input = lastSpeed - changeMin;
		}

		return input * sign;
	}
}
