package org.usfirst.frc.team4.robot.commands.automodes;

import org.usfirst.frc.team4.robot.commands.AutoDriveController;
import org.usfirst.frc.team4.robot.commands.BringArmsDown;
import org.usfirst.frc.team4.robot.commands.BringIntakeArmsDown;
import org.usfirst.frc.team4.robot.commands.OutTakeBall;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FourthAutonomous extends CommandGroup {

	public FourthAutonomous() {
		// No PID on Arms to save time on Tuning
		addSequential(new BringIntakeArmsDown(), .01);
		
		addSequential(new BringArmsDown(), 1); 
		
		// Change to actual values
		addSequential(new AutoDriveController(177.5, 0));
		
		addSequential(new AutoDriveController(0, 35));
		
		addSequential(new AutoDriveController(91.6, 0));
		
		addSequential(new AutoDriveController(0, 84));
		
		addSequential(new AutoDriveController(35, 0));
		
		addSequential(new OutTakeBall(), 3);
	}
}
