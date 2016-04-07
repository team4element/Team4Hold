package org.usfirst.frc.team4.robot.commands.automodes;

import org.usfirst.frc.team4.robot.commands.routines.TurnInPlace;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TuneTurn extends CommandGroup {
    
    public  TuneTurn() {
    	
    	addSequential(new TurnInPlace(180), 5);
    	   	
    }
}
