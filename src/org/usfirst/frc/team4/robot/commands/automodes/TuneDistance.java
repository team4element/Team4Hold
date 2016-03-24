package org.usfirst.frc.team4.robot.commands.automodes;

import org.usfirst.frc.team4.robot.commands.routines.AutoDriveController;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TuneDistance extends CommandGroup {
    
    public  TuneDistance() {
    	
    	addSequential(new AutoDriveController(0, 0), 5);
    	   	
    }
}
