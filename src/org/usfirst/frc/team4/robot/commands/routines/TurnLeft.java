package org.usfirst.frc.team4.robot.commands.routines;

import org.usfirst.frc.team4.robot.commands.TurnPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TurnLeft extends CommandGroup {
    
    public  TurnLeft() {
    	addSequential(new TurnPID(-5));
    }
}