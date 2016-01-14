package org.usfirst.frc.team4.robot;

import org.usfirst.frc.team4.robot.commands.DriveToggle;
import org.usfirst.frc.team4.robot.commands.GearToggle;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
	public OI(){
		RobotMap.driveL3.whenPressed(new DriveToggle());
		
		RobotMap.driveL1.whenPressed(new GearToggle());
		RobotMap.driveR1.whenPressed(new GearToggle());
	}
}

