package org.usfirst.frc.team4.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FirstAutonomous extends CommandGroup {

	public FirstAutonomous() {
		// No PID on Arms to save time on Tuning
		addSequential(new BringIntakeArmsDown(), .01);
		
		addSequential(new BringArmsDown(), 1); 
		
		// Change to actual values
		addSequential(new AutoDriveController(207, 0));
		
		addSequential(new AutoDriveController(0, 60));
		
		addSequential(new AutoDriveController(122.6, 0));
		
		addSequential(new OutTakeBall(), 3);
		
	}
}
