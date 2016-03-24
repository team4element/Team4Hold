package org.usfirst.frc.team4.robot.commands.automodes;

import org.usfirst.frc.team4.robot.commands.routines.BringArmsDown;
import org.usfirst.frc.team4.robot.commands.routines.BringIntakeArmsDown;
import org.usfirst.frc.team4.robot.commands.routines.DriveSpeed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BangBangAutoMode extends CommandGroup {
    
    public  BangBangAutoMode() {

    	addSequential(new BringIntakeArmsDown(), .5);
		
		addSequential(new BringArmsDown(), 1);
		
		addSequential(new DriveSpeed(1), 2.5);
    }
}
