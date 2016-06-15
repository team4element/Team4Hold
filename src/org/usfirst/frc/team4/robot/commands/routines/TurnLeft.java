package org.usfirst.frc.team4.robot.commands.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TurnLeft extends CommandGroup {
    
    public  TurnLeft() {
    	addSequential(new AutoDriveController(0, -2));
    }
}