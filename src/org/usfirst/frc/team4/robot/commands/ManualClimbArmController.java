package org.usfirst.frc.team4.robot.commands;

import org.usfirst.frc.team4.robot.ControllerConstants;
import org.usfirst.frc.team4.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualClimbArmController extends Command {

	public ManualClimbArmController() {
		requires(Robot.climb);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (ControllerConstants.operatorStart.get() && ControllerConstants.operatorSelect.get()) {
			Robot.climb.setWinchSpeed(1);
		} else {
			Robot.climb.stopWinch();
		}
		
		// Manual Control
		Robot.climb
				.setBotMotorSpeed(ControllerConstants.operatorController.getRawAxis(ControllerConstants.AXIS_LEFT_Y));
		Robot.climb
				.setTopMotorSpeed(ControllerConstants.operatorController.getRawAxis(ControllerConstants.AXIS_RIGHT_Y));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.climb.stopWinch();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
