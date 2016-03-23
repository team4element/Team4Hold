package org.usfirst.frc.team4.robot.commands.automodes;

import org.usfirst.frc.team4.robot.commands.BringArmsDown;
import org.usfirst.frc.team4.robot.commands.BringIntakeArmsDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DoNothingMode extends CommandGroup {

	public DoNothingMode() {
		// No PID on Arms to save time on Tuning
		addSequential(new BringIntakeArmsDown(), .5);
		
		addSequential(new BringArmsDown(), 1); 
	}
}
