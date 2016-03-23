package org.usfirst.frc.team4.robot.commands.automodes;

import org.usfirst.frc.team4.robot.commands.TurnInPlace;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TunePID extends CommandGroup {
    
    public  TunePID() {
    	
    	addSequential(new TurnInPlace(180), 5);
    	   	
    }
}
