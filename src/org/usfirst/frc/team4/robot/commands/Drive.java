package org.usfirst.frc.team4.robot.commands;

import org.usfirst.frc.team4.robot.ControllerConstants;
import org.usfirst.frc.team4.robot.Robot;
import org.usfirst.frc.team4.robot.subsystems.Chassis.DriveState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {
	
    public Drive() {
    	requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.chassis.driveState == DriveState.TANK){
    		// Tank Drive
        	Robot.chassis.filteredTankDrive(ControllerConstants.driveController);
    	} else {
    		// Arcade Drive
    		Robot.chassis.filteredArcadeDrive(ControllerConstants.driveController);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
