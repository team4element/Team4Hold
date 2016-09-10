package org.usfirst.frc.team4.robot.commands.automodes;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TrackTarget extends CommandGroup {
    
    public  TrackTarget() {
        addSequential(new TrackTarget());
    }
}
