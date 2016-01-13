package org.usfirst.frc.team4.robot;

import org.usfirst.frc.team4.robot.commands.DriveToggle;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
	public OI(){
		RobotMap.driveL3.whenPressed(new DriveToggle());
	}
	
}

