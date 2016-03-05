package org.usfirst.frc.team4.robot.commands;

import org.usfirst.frc.team4.robot.ControllerConstants;
import org.usfirst.frc.team4.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbStateController extends Command {

	// Stores Button used
	private int button;

	public ClimbStateController(int button) {
		this.button = button;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (button == ControllerConstants.POV_TOP) {
			Robot.climb.currentClimbInt++;
		} else if (button == ControllerConstants.POV_BOT) {
			if (Robot.climb.currentClimbInt != 0) {
				Robot.climb.currentClimbInt--;
			} else {
				Robot.climb.currentClimbInt = 0;
			}
		} else if (button == ControllerConstants.POV_LEFT) {

		} else if (button == ControllerConstants.POV_RIGHT) {

		} else {
			System.out.println("Something went wrong ;(");
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
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
