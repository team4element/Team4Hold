package org.usfirst.frc.team4.robot.commands;

import org.usfirst.frc.team4.robot.ControllerConstants;
import org.usfirst.frc.team4.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeController extends Command {

	public IntakeController() {
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (ControllerConstants.operatorController.getBumper(Hand.kRight)) {
			Robot.intake
					.setArmAngle(ControllerConstants.operatorController.getRawAxis(ControllerConstants.TRIGGE_RIGHT_2));
		} else if (ControllerConstants.operatorController.getBumper(Hand.kLeft)) {
			Robot.intake
					.setArmAngle(-ControllerConstants.operatorController.getRawAxis(ControllerConstants.TRIGGER_LEFT_2));
		} else {
			Robot.intake.stopArm();
			System.out.println("No button mapped.");
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.intake.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
