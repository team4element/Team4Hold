package org.usfirst.frc.team4.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SecondAutonomous extends CommandGroup {

	public SecondAutonomous() {
		// No PID on Arms to save time on Tuning
		addSequential(new BringIntakeArmsDown(), .01);
		
		addSequential(new BringArmsDown(), 1); 
		
		// Change to actual values
		addSequential(new AutoDriveController(254, 0));
		
		addSequential(new AutoDriveController(0, 90));
		
		addSequential(new AutoDriveController(254, 0));
		
		addSequential(new OutTakeBall(), 3);
	}
}
