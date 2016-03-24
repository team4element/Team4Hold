package org.usfirst.frc.team4.robot.commands.routines;

import org.usfirst.frc.team4.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnInPlace extends Command {

	private PIDController rotatePID;
	
	private final double ROTATE_kP = .28, ROTATE_kI = 0, ROTATE_kD = .9;
	
	public TurnInPlace( double angle) {
			rotatePID = new PIDController(ROTATE_kP, ROTATE_kI, ROTATE_kD, new PIDSource() {
			PIDSourceType m_sourceType = PIDSourceType.kDisplacement;

			public double pidGet() {
				return Robot.chassis.getAngle();
			}

			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				m_sourceType = pidSource;
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return m_sourceType;
			}
		}, new PIDOutput() {
			public void pidWrite(double angle) {
				Robot.chassis.arcadeDrive(0, angle);
			}
		});
		rotatePID.setAbsoluteTolerance(1);
		rotatePID.setSetpoint(angle);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
        Robot.chassis.reset();
        rotatePID.reset();
    	rotatePID.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
    	rotatePID.disable();
    	Robot.chassis.arcadeDrive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}