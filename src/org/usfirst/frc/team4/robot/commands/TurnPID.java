package org.usfirst.frc.team4.robot.commands;

import org.usfirst.frc.team4.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnPID extends Command {

	private PIDController pid;
	
	private double kP = 1;
	private double kI = 0;
	private double kD = 0;
	
    public TurnPID(double setPoint) {    	
    	requires(Robot.chassis);
    	pid = new PIDController(kP, kI, kD, new PIDSource() {
			
			@Override
			public void setPIDSourceType(PIDSourceType source) {

			}
			
			@Override
			public double pidGet() {
				return Robot.chassis.getAngle();
			}
			
			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
		}, new PIDOutput() {
			
			@Override
			public void pidWrite(double output) {
				Robot.chassis.arcadeDrive(0, output);
			}
		});	
    	pid.setAbsoluteTolerance(1);
    	pid.setSetpoint(setPoint);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	pid.reset();
    	pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	pid.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
