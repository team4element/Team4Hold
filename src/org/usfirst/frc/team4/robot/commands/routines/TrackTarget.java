package org.usfirst.frc.team4.robot.commands.routines;

import org.usfirst.frc.team4.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TrackTarget extends Command {

	private int center = 320;
	private int tolerance = 20;

	private double targetX = -1;
	private double targetArea = -1;

	public TrackTarget() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		double[] defaultValue = new double[0];

		double[] areas = Robot.visionTable.getNumberArray("trackTarget/area", defaultValue);
		double[] xS = Robot.visionTable.getNumberArray("trackTarget/x", defaultValue);
	
	// TODO Add option to turn when it can't find anything	
		
		for (int i = 0; i < areas.length; i++) {
			if (areas[i] > targetArea) {
				targetArea = areas[i];
				if (targetArea < 0) {
					System.out.println("No target found");
					end();
				} else {
					targetX = xS[i];
				}
			}
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (targetX < center - tolerance) {
			new TurnRight();
		} else if (targetX > center + tolerance) {
			new TurnLeft();
		} else {
			System.out.println("NICE BRUH");
			end();
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.chassis.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
