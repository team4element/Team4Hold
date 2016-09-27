package org.usfirst.frc.team4.robot.commands.automodes;

import org.usfirst.frc.team4.robot.commands.AutoDrive;
import org.usfirst.frc.team4.robot.commands.routines.BringArmsDown;
import org.usfirst.frc.team4.robot.commands.routines.BringIntakeArmsDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLowBar_RockyTerrain extends CommandGroup {
	public AutoLowBar_RockyTerrain(){
		addSequential(new BringIntakeArmsDown(), .5);
		addSequential(new BringArmsDown(), 1);
		addSequential(new AutoDrive(), 1.5);
	}

}
