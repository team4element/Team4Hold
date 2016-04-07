package org.usfirst.frc.team4.robot.commands.routines;

import org.usfirst.frc.team4.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveDistance extends Command {

	private PIDController distancePID;

	private final double DISTANCE_kP = 0.37, DISTANCE_kI = 0, DISTANCE_kD = 1, DISTANCE_kF = 1 / 120;

	public DriveDistance(double distance) {
		distancePID = new PIDController(DISTANCE_kP, DISTANCE_kI, DISTANCE_kD, DISTANCE_kF, new PIDSource() {
			PIDSourceType m_sourceType = PIDSourceType.kDisplacement;

			public double pidGet() {
				return Robot.chassis.getDistance();
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
			public void pidWrite(double output) {
				Robot.chassis.arcadeDrive(output);
			}
		});
		distancePID.setSetpoint(distance);
		distancePID.setAbsoluteTolerance(.5);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.chassis.reset();
		distancePID.reset();
		distancePID.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putData("Distance Tune", distancePID);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return distancePID.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
    	distancePID.disable();
        Robot.chassis.arcadeDrive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
