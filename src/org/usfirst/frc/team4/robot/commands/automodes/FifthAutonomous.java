package org.usfirst.frc.team4.robot.commands.automodes;

import org.usfirst.frc.team4.robot.commands.AutoDriveController;
import org.usfirst.frc.team4.robot.commands.BringArmsDown;
import org.usfirst.frc.team4.robot.commands.BringIntakeArmsDown;
import org.usfirst.frc.team4.robot.commands.OutTakeBall;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FifthAutonomous extends CommandGroup {

	public FifthAutonomous() {
		// No PID on Arms to save time on Tuning
		addSequential(new BringIntakeArmsDown(), .01);
		
		addSequential(new BringArmsDown(), 1); 
		
		// Change to actual values
		addSequential(new AutoDriveController(252, 0));
		
		addSequential(new AutoDriveController(0, -61));
		
		addSequential(new AutoDriveController(35, 0));
		
		addSequential(new OutTakeBall(), 3);
	}
}
