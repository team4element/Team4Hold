package org.usfirst.frc.team4.robot.commands.automodes;

import org.usfirst.frc.team4.robot.commands.routines.AutoDriveController;
import org.usfirst.frc.team4.robot.commands.routines.BringArmsDown;
import org.usfirst.frc.team4.robot.commands.routines.BringIntakeArmsDown;
import org.usfirst.frc.team4.robot.commands.routines.OutTakeBall;
import org.usfirst.frc.team4.robot.commands.routines.TurnInPlace;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FirstAutonomous extends CommandGroup {

	public FirstAutonomous() {
		// No PID on Arms to save time on Tuning
		addSequential(new BringIntakeArmsDown(), .5);
		
		addSequential(new BringArmsDown(), 1);
		
		// Change to actual values
		addSequential(new AutoDriveController(147.5, 0), 3);
		
		Timer.delay(.5);
		
		addSequential(new TurnInPlace(60), 3);
		
		addSequential(new AutoDriveController(153.5, 0), 3);
		
		addSequential(new OutTakeBall(), 3);
		
	}
}
