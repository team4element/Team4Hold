package org.usfirst.frc.team4.robot.commands.routines;

import org.usfirst.frc.team4.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TrackTarget extends Command {

	double[] defaultValue = new double[0];
	
	private TurnRight turnRight;
	private TurnLeft turnLeft;
	
	private int center = 320;
	private int tolerance = 10;

	private double targetX = -1;
	private double targetArea = -1;

	public TrackTarget() {
		turnRight = new TurnRight();
		turnLeft = new TurnLeft();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Uncomment if it execute doesn't work
		//turnToTarget();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
			
		 double[] areas = Robot.visionTable.getNumberArray("area", defaultValue);
		 double[] xS = Robot.visionTable.getNumberArray("centerX", defaultValue);
		 
		 System.out.println("Areas1: " + areas[0]);
		 System.out.println("xs1: " + xS[0]);
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
		
		if (targetX < center - tolerance) {
			turnLeft.start();
		} else if (targetX > center + tolerance) {
			turnRight.start();
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
	/*
	private void turnToTarget(){
		 double[] areas = Robot.visionTable.getNumberArray("area", defaultValue);
		 double[] xS = Robot.visionTable.getNumberArray("centerX", defaultValue);
		 
		 System.out.println("Areas1: " + areas[0]);
		 System.out.println("xs1: " + xS[0]);
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
		
		if (targetX < center - tolerance) {
			turnLeft.start();
			turnToTarget();
		} else if (targetX > center + tolerance) {
			turnRight.start();
			turnToTarget();
		} else {
			System.out.println("NICE BRUH");
			end();
		}
	} 
	*/
}
