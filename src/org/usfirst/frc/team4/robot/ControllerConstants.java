package org.usfirst.frc.team4.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class ControllerConstants {

	public static final int CONTROLLER_DRIVE = 0;

	// Button, Trigger and Axis Constants
	public static final int BUTTON_A = 1;
	public static final int BUTTON_B = 2;
	public static final int BUTTON_X = 3;
	public static final int BUTTON_Y = 4;

	public static final int BUMPER_LEFT_1 = 5;
	public static final int BUMPER_RIGHT_1 = 6;

	public static final int TRIGGER_LEFT_2 = 2;
	public static final int TRIGGE_RIGHT_2 = 3;

	public static final int BUTTON_LEFT_3 = 9;
	public static final int BUTTON_RIGHT_3 = 10;

	public static final int BUTTON_SELECT = 7;
	public static final int BUTTON_START = 8;

	public static final int AXIS_LEFT_Y = 1;
	public static final int AXIS_LEFT_X = 0;

	public static final int AXIS_RIGHT_Y = 5;
	public static final int AXIS_RIGHT_X = 4;

	// Joystick and Button Initializer
	public static Joystick driveController;

	public static JoystickButton driveA;
	public static JoystickButton driveB;
	public static JoystickButton driveX;
	public static JoystickButton driveY;

	public static JoystickButton driveLeftBumper1;
	public static JoystickButton driveRightBumper1;

	public static JoystickButton driveLeftTrigger2;
	public static JoystickButton driveRightTrigger2;

	public static JoystickButton driveLeftButton3;
	public static JoystickButton driveRightButton3;

	public static JoystickButton driveSelect;
	public static JoystickButton driveStart;

	public static void init() {
		driveController = new Joystick(CONTROLLER_DRIVE);

		driveA = new JoystickButton(driveController, BUTTON_A);
		driveB = new JoystickButton(driveController, BUTTON_B);
		driveX = new JoystickButton(driveController, BUTTON_X);
		driveY = new JoystickButton(driveController, BUTTON_Y);

		driveLeftBumper1 = new JoystickButton(driveController, BUMPER_LEFT_1);
		driveRightBumper1 = new JoystickButton(driveController, BUMPER_RIGHT_1);

		driveLeftTrigger2 = new JoystickButton(driveController, TRIGGER_LEFT_2);
		driveRightTrigger2 = new JoystickButton(driveController, TRIGGE_RIGHT_2);

		driveLeftButton3 = new JoystickButton(driveController, BUTTON_LEFT_3);
		driveRightButton3 = new JoystickButton(driveController, BUTTON_RIGHT_3);

		driveSelect = new JoystickButton(driveController, BUTTON_SELECT);
		driveStart = new JoystickButton(driveController, BUTTON_START);
	}

}
