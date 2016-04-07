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
public class AutoDriveController extends Command {

	private PIDController rotatePID, distancePID;

	private final double ROTATE_kP = .34, ROTATE_kI = 0, ROTATE_kD = .9;
	
	private final double DISTANCE_kP = .87, DISTANCE_kI = 0, DISTANCE_kD = 3.5, DISTANCE_kF = 1/118;
	
	private double speed = 0;
	
	public AutoDriveController(double distance, double angle) {

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
				speed = -output;
			}
		});
		distancePID.setSetpoint(distance);
		distancePID.setAbsoluteTolerance(1);
		
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
				Robot.chassis.arcadeDrive(speed, angle);
			}
		});
		rotatePID.setAbsoluteTolerance(1);
		rotatePID.setSetpoint(angle);
	}

	// Called just before this Command runs the first time,meme
	protected void initialize() {
        Robot.chassis.reset();
    	distancePID.reset();
        rotatePID.reset();
        distancePID.enable();
    	rotatePID.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putData("Distance PID", distancePID);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return distancePID.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
    	rotatePID.disable();
    	distancePID.disable();
        Robot.chassis.arcadeDrive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}