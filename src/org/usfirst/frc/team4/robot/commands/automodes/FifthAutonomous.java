package org.usfirst.frc.team4.robot.commands.automodes;

import org.usfirst.frc.team4.robot.commands.DoNothing;
import org.usfirst.frc.team4.robot.commands.routines.AutoDriveController;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FifthAutonomous extends CommandGroup {

	public FifthAutonomous() {
//		SmartDashboard.putString("done", "");
//		SmartDashboard.putString("Done1", "");
//		SmartDashboard.putString("Done2", "");
		// No PID on Arms to save time on Tuning
		//addSequential(new BringIntakeArmsDown(), .01);
		
	//	addSequential(new BringArmsDown(), 1); 
		
		// Change to actual values
//		addSequential(new AutoDriveController(180, 0));
		
//		addSequential(new AutoDriveController(0, 180));
		
//		addSequential(new AutoDriveController(72, 0));
		
//		SmartDashboard.putString("done", "test");
//		addSequential(new AutoDriveController(0, 180));
////		addSequential(new AutoDriveController(0, 0));
//		SmartDashboard.putString("Done1", "DONE!");
//		addSequential(new AutoDriveController(90, 0));
//		SmartDashboard.putString("Done2", "DONE!");
		
//		addSequential(new OutTakeBall(), 3);
		
//		addSequential(new BangBang(0, 90));
//		addSequential(new BangBang(0, 90));
//		addSequential(new BangBang(0, 90));
//		addSequential(new BangBang(0, 90));
		
//		addSequential(new AutoDriveController(0, 180));
//		addSequential(new AutoDriveController(0, 180));
		Timer.delay(.5);
		addSequential(new AutoDriveController(0, 90));
		Timer.delay(.5);
		addSequential(new DoNothing());
		addSequential(new AutoDriveController(0, 90));
		addSequential(new DoNothing());
		
//		addSequential(new BangBang(0, 0));
//		addSequential(new BangBang(0, 90));
		
//		addSequential(new BangBang(90, 45));
//		addSequential(new BangBang(0, 0));
	}
}
