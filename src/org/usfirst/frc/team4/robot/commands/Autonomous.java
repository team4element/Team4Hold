package org.usfirst.frc.team4.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous extends CommandGroup {

	public Autonomous() {
		// No PID on Arms to save time on Tuning
		addSequential(new BringArmsDown());

		Timer.delay(.05);

		// TODO: Change to actual values
		addSequential(new AutoDriveController(72, 0));

		// Turn around
		addSequential(new AutoDriveController(0, 180));

		// Go back
		addSequential(new AutoDriveController(72, 0));
		
		// Turn around
		addSequential(new AutoDriveController(0, 180));
	}
}
