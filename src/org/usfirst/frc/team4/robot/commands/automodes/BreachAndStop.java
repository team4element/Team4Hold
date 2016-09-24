package org.usfirst.frc.team4.robot.commands.automodes;

import org.usfirst.frc.team4.robot.commands.routines.AutoDriveController;
import org.usfirst.frc.team4.robot.commands.routines.BringArmsDown;
import org.usfirst.frc.team4.robot.commands.routines.BringIntakeArmsDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BreachAndStop extends CommandGroup {

	public BreachAndStop() {
		// No PID on Arms to save time on Tuning
		addSequential(new BringIntakeArmsDown(), .5);
		
		addSequential(new BringArmsDown(), 1); 
		
		addSequential(new AutoDriveController(-146, 0), 3);
	}
}
