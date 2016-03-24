package org.usfirst.frc.team4.robot.commands;

import org.usfirst.frc.team4.robot.ControllerConstants;
import org.usfirst.frc.team4.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeController extends Command {

	private double armSpeed = 0;

	public IntakeController() {
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		/*
		if (ControllerConstants.operatorStart.get() && ControllerConstants.operatorSelect.get()) {
			Robot.climb.setWinchSpeed(1);
			System.out.println("Start1:" + ControllerConstants.operatorStart.get() + " Select1:" + ControllerConstants.operatorSelect.getSmartDashboardType());
		} else if (ControllerConstants.operatorStart.get() && ControllerConstants.operatorSelect.get() && ControllerConstants.operatorB.get()){
			Robot.climb.setWinchSpeed(-1);
			System.out.println("Start2:" + ControllerConstants.operatorStart.get() + " Select2:" + ControllerConstants.operatorSelect.getSmartDashboardType() + " ControllerB:" + ControllerConstants.operatorB.get());
		} else
			Robot.climb.setBotMotorSpeed(0);
		*/
		
		if (!Robot.climb.isClimbing) {

			armSpeed = ControllerConstants.operatorController.getRawAxis(ControllerConstants.TRIGGER_LEFT_2)
					- ControllerConstants.operatorController.getRawAxis(ControllerConstants.TRIGGER_RIGHT_2);

			Robot.intake.setArmAngle(armSpeed);
		}

		if (ControllerConstants.operatorLeftBumper1.get()) {
			Robot.intake.setRollerSpeed(1);
		} else if (ControllerConstants.operatorRightBumper1.get()) {
			Robot.intake.setRollerSpeed(-1);
		} else {
			Robot.intake.stopRoller();
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
