package org.usfirst.frc.team4.robot.commands.automodes;

import org.usfirst.frc.team4.robot.commands.ResetGyro;
import org.usfirst.frc.team4.robot.commands.routines.TurnInPlace;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TuneTurn extends CommandGroup {
    
    public  TuneTurn() {
    	
//    	addSequential(new ResetGyro(), 1);
    	addSequential(new TurnInPlace(180), 5);
    	   	
    }
}
