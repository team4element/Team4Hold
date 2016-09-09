package org.usfirst.frc.team4.robot.commands.routines;

import org.usfirst.frc.team4.robot.commands.TurnPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TurnRight extends CommandGroup {
    
    public  TurnRight() {
    	addSequential(new TurnPID(4));
    }
}
