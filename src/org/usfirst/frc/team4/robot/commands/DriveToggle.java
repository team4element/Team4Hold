package org.usfirst.frc.team4.robot.commands;

import org.usfirst.frc.team4.robot.Robot;
import org.usfirst.frc.team4.robot.subsystems.Chassis.DriveState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToggle extends Command {
	
    public DriveToggle() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.chassis.driveState == DriveState.TANK){
    		Robot.chassis.driveState = DriveState.ARCADE;
    	} else {
    		Robot.chassis.driveState = DriveState.TANK;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Changed Drive Mode");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
