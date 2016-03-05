package org.usfirst.frc.team4.robot.commands;

import org.usfirst.frc.team4.robot.ControllerConstants;
import org.usfirst.frc.team4.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeController extends Command {

	private double armSpeed = 0, armSpeedSquared = 0;
	// Jerk Reduction
	private double armSpeedFiltered = 0;
	private final double JERK_FILTER = .4;

	public IntakeController() {
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		if (!Robot.climb.isClimbing) {

			armSpeed = ControllerConstants.operatorController.getRawAxis(ControllerConstants.TRIGGE_RIGHT_2)
					- ControllerConstants.operatorController.getRawAxis(ControllerConstants.TRIGGER_LEFT_2);
			// Cut speed in half
			armSpeedSquared = armSpeed * -Math.abs(armSpeed) * .50;
			armSpeedFiltered = jerkFilter(armSpeedSquared, JERK_FILTER);

			Robot.intake.setArmAngle(armSpeedFiltered);
		}

		if (ControllerConstants.operatorLeftBumper1.get()) {
			Robot.intake.setRollerSpeed(1);
		} else if (ControllerConstants.operatorRightBumper1.get()) {
			Robot.intake.setRollerSpeed(-1);
		} else {
			Robot.intake.stopRoller();
			System.out.println("No button pressed.");
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

	private double jerkFilter(double n, double t) {
		return Math.abs(n) > t ? n : 0;
	}
}
