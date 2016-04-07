package org.usfirst.frc.team4.robot.commands.automodes;

import org.usfirst.frc.team4.robot.commands.routines.BringArmsDown;
import org.usfirst.frc.team4.robot.commands.routines.BringIntakeArmsDown;
import org.usfirst.frc.team4.robot.commands.routines.DriveSpeed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BangBangAutoMode extends CommandGroup {
    
	double time;
	
    public  BangBangAutoMode(double t) {

    	t = time;
    	
    	addSequential(new BringIntakeArmsDown(), .5);
		
		addSequential(new BringArmsDown(), 1);
		
		addSequential(new DriveSpeed(1), t);
    }
}
