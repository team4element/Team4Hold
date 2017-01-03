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

	private final double ROTATE_kP = 0.15, ROTATE_kI = 0, ROTATE_kD = 0.15;

	private final double DISTANCE_P = 0.25, DISTANCE_I = 0, DISTANCE_D = 1;

	private double speed = 0;
	
	private final double distanceSetpoint, angleSetpoint;

	public AutoDriveController(double distance, double angle) {
		distanceSetpoint = distance;
		angleSetpoint = angle;

		distancePID = new PIDController(DISTANCE_P, DISTANCE_I, DISTANCE_D, new PIDSource() {
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
		distancePID.setAbsoluteTolerance(1.);

		rotatePID = new PIDController(ROTATE_kP, ROTATE_kI, ROTATE_kD, new PIDSource() {
			PIDSourceType m_sourceType = PIDSourceType.kDisplacement;

			public double pidGet() {
				// Robot.chassis.updateGyro();
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
				Robot.chassis.arcadeDrive2(speed, angle);
			}
		});
		
		rotatePID.setContinuous(true);
		rotatePID.setInputRange(-180, 180);		
		rotatePID.setAbsoluteTolerance(1.);
		rotatePID.setSetpoint(angle);
	}

	// Called just before this Command runs the first time,
	protected void initialize() {
		Robot.chassis.reset();
		distancePID.reset();
		rotatePID.reset();

		distancePID.enable();
		rotatePID.enable();
	}
	
	private double lastValidResult = 0;

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putData("Distance PID", distancePID);
		SmartDashboard.putData("Angle Tune", rotatePID);
		
		SmartDashboard.putBoolean("Distance on target", distancePID.onTarget());
		SmartDashboard.putBoolean("Rotate on target", rotatePID.onTarget());
		
		double custom = Math.abs(Robot.chassis.getAngle() - angleSetpoint);
		boolean customTolerance = -10 < custom && custom < 10;
		
		SmartDashboard.putNumber("rotate part 1", custom);
		SmartDashboard.putBoolean("rotate part 2", customTolerance);
		
		double distance = Math.abs(Robot.chassis.getLeftDistance() - distanceSetpoint);
		boolean distanceOnTarget = -10 < distance && distance < 10;
		
		SmartDashboard.putNumber("distance part 1", distance);
		SmartDashboard.putBoolean("distance part 2", distanceOnTarget);
		
		if (customTolerance) {
			lastValidResult = Robot.chassis.getAngle();
		}
		
		SmartDashboard.putNumber("last valid result", lastValidResult);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		final double tolerance = 1.;
		double angle = Math.abs(Robot.chassis.getAngle() - angleSetpoint);
		boolean angleOnTarget = -tolerance < angle && angle < tolerance;
		
		double distance = Math.abs(Robot.chassis.getLeftDistance() - distanceSetpoint);
		boolean distanceOnTarget = -tolerance < distance && distance < tolerance;
		
		return angleOnTarget && distanceOnTarget;
//		return (distancePID);
	}

	// Called once after isFinished returns true
	protected void end() {
		rotatePID.disable();
		distancePID.disable();
		Robot.chassis.arcadeDrive2(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}