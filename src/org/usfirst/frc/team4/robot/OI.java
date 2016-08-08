package org.usfirst.frc.team4.robot;

import org.usfirst.frc.team4.robot.commands.WinchOn;
import org.usfirst.frc.team4.robot.commands.WinchReverse;
import org.usfirst.frc.team4.robot.commands.toggles.DriveToggle;
import org.usfirst.frc.team4.robot.commands.toggles.GearToggle;
import org.usfirst.frc.team4.robot.commands.toggles.ToggleInverseDrive;
import org.usfirst.frc.team4.robot.triggers.MultiButton;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public OI() {
		ControllerConstants.driveY.whenPressed(new DriveToggle());

		ControllerConstants.driveLeftBumper1.whenPressed(new GearToggle());
		ControllerConstants.driveRightBumper1.whenPressed(new GearToggle());

		ControllerConstants.driveA.whenPressed(new ToggleInverseDrive());
		/*
		new MultiButton(ControllerConstants.operatorController, ControllerConstants.BUTTON_START,
				ControllerConstants.BUTTON_SELECT).whileActive(new WinchOn());
		

		new MultiButton(ControllerConstants.operatorController, ControllerConstants.BUTTON_SELECT,
				ControllerConstants.BUTTON_START, ControllerConstants.BUTTON_B).whileActive(new WinchReverse());
		;
				*/

		// Turn in place 180
		// ControllerConstants.driveB.whenPressed(new AutoDriveController(0,
		// 180));
	}

	public boolean L1(Joystick cont) {
		return cont.getRawButton(ControllerConstants.BUMPER_LEFT_1);
	}

	public boolean R1(Joystick cont) {
		return cont.getRawButton(ControllerConstants.BUMPER_RIGHT_1);
	}
}
