package org.usfirst.frc.team4.robot.commands;

import org.usfirst.frc.team4.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TogglePortcullisActuator extends Command {

	// TODO: Change to actual values
	private double kBotAngle = 0, kTopAngleDown = 0, kTopAngleUp = 70;
	private SetArmAngles setArmAngles;

	public TogglePortcullisActuator() {

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (Robot.climb.isPortculisUp) {
			setArmAngles.end();
			setArmAngles = new SetArmAngles(kBotAngle, kTopAngleDown);
		} else {
			setArmAngles.end();
			setArmAngles = new SetArmAngles(kBotAngle, kTopAngleUp);
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
		Robot.climb.isPortculisUp = !Robot.climb.isPortculisUp;
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
