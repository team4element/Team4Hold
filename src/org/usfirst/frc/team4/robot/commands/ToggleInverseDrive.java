package org.usfirst.frc.team4.robot.commands;

import org.usfirst.frc.team4.robot.ControllerConstants;
import org.usfirst.frc.team4.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleInverseDrive extends Command {

    public ToggleInverseDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
		ControllerConstants.driveController.setRumble(RumbleType.kLeftRumble, .75f);
		ControllerConstants.driveController.setRumble(RumbleType.kRightRumble, .75f);

		Timer.delay(.15);

		ControllerConstants.driveController.setRumble(RumbleType.kLeftRumble, 0);
		ControllerConstants.driveController.setRumble(RumbleType.kRightRumble, 0);
    	
    	Robot.chassis.isDriveInverse = !Robot.chassis.isDriveInverse;
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
