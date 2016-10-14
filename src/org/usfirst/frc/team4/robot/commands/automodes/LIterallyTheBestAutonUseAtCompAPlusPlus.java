package org.usfirst.frc.team4.robot.commands.automodes;

import org.usfirst.frc.team4.robot.commands.routines.AutoDriveController;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LIterallyTheBestAutonUseAtCompAPlusPlus extends CommandGroup{
	public LIterallyTheBestAutonUseAtCompAPlusPlus() {
		addSequential(new AutoDriveController(0, 180));
	}
}
