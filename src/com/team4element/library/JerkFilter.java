package com.team4element.library;

public class JerkFilter {

	private static double lastInput;
	private static double changeMax = 0.1;
	private static double changeMin = 0.1;

	public JerkFilter() {
		lastInput = 0;
	}

	public static double filter(double currentInput) {

		currentInput = filterInputs(currentInput, lastInput);
		lastInput = currentInput;
		return currentInput;

	}

	private static double filterInputs(double input, double lastSpeed) {

		int sign = getSign(input);

		input = Math.abs(input);
		lastSpeed = Math.abs(lastSpeed);
		if (input > .1) {
			if (input > lastSpeed + changeMax) {
				input = lastSpeed + changeMax;
			} else if (input < lastSpeed - changeMin) {
				input = lastSpeed - changeMin;
			}
		}

		return input * sign;
	}

	private static int getSign(double number) {
		int sign = 1;
		if (number < 0) {
			sign = -1;
		}

		return sign;
	}
}
