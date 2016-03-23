package org.usfirst.frc.team4.robot.triggers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class MultiButton extends Trigger {

	private Joystick joystick;
	private int buttonOne, buttonTwo, buttonThree, buttonFour;

	private enum ButtonCount {
		TWO, THREE, FOUR;
	}

	private ButtonCount buttonAmount;

	public MultiButton(Joystick joystick, int button1, int button2) {
		this.joystick = joystick;
		this.buttonOne = button1;
		this.buttonTwo = button2;
		buttonAmount = ButtonCount.TWO;
	}

	public MultiButton(Joystick joystick, int button1, int button2, int button3) {
		this.joystick = joystick;
		this.buttonOne = button1;
		this.buttonTwo = button2;
		this.buttonThree = button3;
		buttonAmount = ButtonCount.THREE;
	}

	public MultiButton(Joystick joystick, int button1, int button2, int button3, int button4) {
		this.joystick = joystick;
		this.buttonOne = button1;
		this.buttonTwo = button2;
		this.buttonThree = button3;
		this.buttonFour = button4;
		buttonAmount = ButtonCount.FOUR;
	}

	public boolean get() {
		switch (buttonAmount) {
		case TWO:
			return joystick.getRawButton(buttonOne) && joystick.getRawButton(buttonTwo);
		case THREE:
			return joystick.getRawButton(buttonOne) && joystick.getRawButton(buttonTwo)
					&& joystick.getRawButton(buttonThree);
		case FOUR:
			return joystick.getRawButton(buttonOne) && joystick.getRawButton(buttonTwo)
					&& joystick.getRawButton(buttonThree) && joystick.getRawButton(buttonFour);
		default:
			System.out.println("Something went wrong :'(");
			return false;
		}
	}
}
