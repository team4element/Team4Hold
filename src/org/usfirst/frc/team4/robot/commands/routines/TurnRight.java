package org.usfirst.frc.team4.robot.commands.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TurnRight extends CommandGroup {
    
    public  TurnRight() {
    	addSequential(new AutoDriveController(0, 2));
    }
}
