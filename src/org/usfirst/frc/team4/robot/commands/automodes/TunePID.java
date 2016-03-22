package org.usfirst.frc.team4.robot.commands.automodes;

import org.usfirst.frc.team4.robot.commands.AutoDriveController;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TunePID extends CommandGroup {
    
    public  TunePID() {
    	
    	addSequential(new AutoDriveController(180, 0), 5);
    	
    	addSequential(new AutoDriveController(0, 180), 5);
    	
    	addSequential(new AutoDriveController(180, 0), 5);
    	
    }
}
