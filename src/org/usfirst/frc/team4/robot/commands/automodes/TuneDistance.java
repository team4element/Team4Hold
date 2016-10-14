package org.usfirst.frc.team4.robot.commands.automodes;

import org.usfirst.frc.team4.robot.commands.routines.DriveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TuneDistance extends CommandGroup {
    
    public  TuneDistance() {
    	
    	addSequential(new DriveDistance(36), 5);
    	   	
    }
}
