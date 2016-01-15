package org.usfirst.frc.team4.robot;

import org.usfirst.frc.team4.robot.commands.DriveToggle;
import org.usfirst.frc.team4.robot.commands.GearToggle;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public OI() {
		RobotMap.driveL3.whenPressed(new DriveToggle());

		RobotMap.driveL1.whenPressed(new GearToggle());
		RobotMap.driveR1.whenPressed(new GearToggle());
	}

	public boolean L1(Joystick cont) {
		return cont.getRawButton(RobotMap.CONT_L1);
	}

	public boolean R1(Joystick cont) {
		return cont.getRawButton(RobotMap.CONT_R1);
	}

}
