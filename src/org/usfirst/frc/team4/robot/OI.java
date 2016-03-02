package org.usfirst.frc.team4.robot;

import org.usfirst.frc.team4.robot.commands.DriveToggle;
import org.usfirst.frc.team4.robot.commands.GearToggle;
import org.usfirst.frc.team4.robot.commands.ToggleInverseDrive;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public OI() {
		ControllerConstants.driveLeftButton3.whenPressed(new DriveToggle());

		ControllerConstants.driveLeftBumper1.whenPressed(new GearToggle());
		ControllerConstants.driveRightBumper1.whenPressed(new GearToggle());

		ControllerConstants.driveA.whenPressed(new ToggleInverseDrive());
	}

	public boolean L1(Joystick cont) {
		return cont.getRawButton(ControllerConstants.AXIS_LEFT_Y);
	}

	public boolean R1(Joystick cont) {
		return cont.getRawButton(ControllerConstants.AXIS_RIGHT_Y);
	}

}
