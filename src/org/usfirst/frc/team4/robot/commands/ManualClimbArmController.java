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
		double botMotorOutput = ControllerConstants.operatorController.getRawAxis(ControllerConstants.AXIS_LEFT_Y);
		double topMotorOutput = ControllerConstants.operatorController.getRawAxis(ControllerConstants.AXIS_RIGHT_Y);

		// Manual Control
		Robot.climb.setBotMotorSpeed(botMotorOutput);
		Robot.climb.setTopMotorSpeed(topMotorOutput);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
