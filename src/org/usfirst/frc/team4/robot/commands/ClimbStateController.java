package org.usfirst.frc.team4.robot.commands;

import org.usfirst.frc.team4.robot.ControllerConstants;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbStateController extends Command {

    public ClimbStateController() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (ControllerConstants.operatorController.getPOV() == ControllerConstants.POV_TOP){
    		
    	} else if (ControllerConstants.operatorController.getPOV() == ControllerConstants.POV_BOT){
    		
    	} else if (ControllerConstants.operatorController.getPOV() == ControllerConstants.POV_LEFT){
    		
    	} else if (ControllerConstants.operatorController.getPOV() == ControllerConstants.POV_RIGHT){
    		
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
