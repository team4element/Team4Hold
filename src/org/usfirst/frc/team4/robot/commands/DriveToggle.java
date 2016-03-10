package org.usfirst.frc.team4.robot.commands;

import org.usfirst.frc.team4.robot.ControllerConstants;
import org.usfirst.frc.team4.robot.Robot;
import org.usfirst.frc.team4.robot.subsystems.Chassis.DriveState;

import com.team4element.library.Rumble;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToggle extends Command {

	private Rumble rumble;
	
	public DriveToggle() {
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (Robot.chassis.driveState == DriveState.TANK) {
			
			rumble = new Rumble(ControllerConstants.driveController);
			
			Robot.chassis.driveState = DriveState.ARCADE;
		} else {

			rumble = new Rumble(ControllerConstants.driveController);

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
