package org.usfirst.frc.team4.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class ControllerConstants {

	public static final int CONTROLLER_DRIVE = 0;
	public static final int CONTROLLER_OPERATOR = 1;

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
	
	public static final int POV_TOP = 0;
	public static final int POV_BOT = 180;
	
	public static final int POV_LEFT = 270;
	public static final int POV_RIGHT = 90;

	// Drive Controller
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
	
	public static JoystickButton drivePOVTop;
	public static JoystickButton drivePOVBot;

	public static JoystickButton drivePOVLeft;
	public static JoystickButton drivePOVRight;
	
	// Operator Controller
	public static Joystick operatorController;
	
	public static JoystickButton operatorA;
	public static JoystickButton operatorB;
	public static JoystickButton operatorX;
	public static JoystickButton operatorY;

	public static JoystickButton operatorLeftBumper1;
	public static JoystickButton operatorRightBumper1;

	public static JoystickButton operatorLeftTrigger2;
	public static JoystickButton operatorRightTrigger2;

	public static JoystickButton operatorLeftButton3;
	public static JoystickButton operatorRightButton3;

	public static JoystickButton operatorSelect;
	public static JoystickButton operatorStart;
	
	public static JoystickButton operatorPOVTop;
	public static JoystickButton operatorPOVBot;

	public static JoystickButton operatorPOVLeft;
	public static JoystickButton operatorPOVRight;


	// Joystick and Button Initializer
	public static void init() {
		// Drive Controller
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
		
		drivePOVTop = new JoystickButton(operatorController, POV_TOP);
		drivePOVBot = new JoystickButton(operatorController, POV_BOT);

		drivePOVLeft = new JoystickButton(operatorController, POV_LEFT);
		drivePOVRight = new JoystickButton(operatorController, POV_RIGHT);
		
		// Operator Controller
		operatorController = new Joystick(CONTROLLER_OPERATOR);

		operatorA = new JoystickButton(operatorController, BUTTON_A);
		operatorB = new JoystickButton(operatorController, BUTTON_B);
		operatorX = new JoystickButton(operatorController, BUTTON_X);
		operatorY = new JoystickButton(operatorController, BUTTON_Y);

		operatorLeftBumper1 = new JoystickButton(operatorController, BUMPER_LEFT_1);
		operatorRightBumper1 = new JoystickButton(operatorController, BUMPER_RIGHT_1);

		operatorLeftTrigger2 = new JoystickButton(operatorController, TRIGGER_LEFT_2);
		operatorRightTrigger2 = new JoystickButton(operatorController, TRIGGE_RIGHT_2);

		operatorLeftButton3 = new JoystickButton(operatorController, BUTTON_LEFT_3);
		operatorRightButton3 = new JoystickButton(operatorController, BUTTON_RIGHT_3);

		operatorSelect = new JoystickButton(operatorController, BUTTON_SELECT);
		operatorStart = new JoystickButton(operatorController, BUTTON_START);
		
		operatorPOVTop = new JoystickButton(operatorController, POV_TOP);
		operatorPOVBot = new JoystickButton(operatorController, POV_BOT);

		operatorPOVLeft = new JoystickButton(operatorController, POV_LEFT);
		operatorPOVRight = new JoystickButton(operatorController, POV_RIGHT);
	}

}
