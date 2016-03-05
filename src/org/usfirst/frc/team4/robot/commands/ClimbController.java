package org.usfirst.frc.team4.robot.commands;

import org.usfirst.frc.team4.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbController extends Command {

	SetArmAngles setArmAngles;

	// TODO: Change to actual values
	private double kStartingAngle = 0, kFirstTopAngle = 70, kFirstBotAngle = 0, kSecondBotAngle = 70,
			kSecondTopAngle = kFirstTopAngle + kSecondBotAngle;

	public ClimbController() {
		requires(Robot.climb);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		switch (Robot.climb.currentClimbState) {
		case BOTH_ARMS_DOWN:
			setArmAngles = new SetArmAngles(kStartingAngle, kStartingAngle);
			break;
		case TOP_ARM_RISING:
			setArmAngles = new SetArmAngles(kStartingAngle, kFirstTopAngle);
			break;
		case BOTH_ARMS_RISING:
			setArmAngles = new SetArmAngles(kSecondBotAngle, kSecondTopAngle);
			break;
		case PULLING_UP:
			Robot.climb.isClimbing = true;
			break;
		case DONE:
			System.out.println("Hooray!!!");
			break;
		default:
			System.out.println("Something went wrong ;(");
			break;
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
