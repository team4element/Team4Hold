package org.usfirst.frc.team4.robot.commands;

import java.util.function.Supplier;

import org.usfirst.frc.team4.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BangBang extends Command {
	private Supplier<Double> distanceP = () -> SmartDashboard.getNumber("Distance P Value", 0.06);

	private final double DISTANCE_TOLERANCE = 1;
	private final double DISTANCE_SETPOINT;

	private Supplier<Double> distanceInput = Robot.chassis::getDistance;

	private Supplier<Double> angleP = () -> SmartDashboard.getNumber("Angle P Value", 0.03);

	private final double ANGLE_TOLERANCE = 1;
	private final double ANGLE_SETPOINT;

	private Supplier<Double> angleInput = Robot.chassis::getAngle;

	public BangBang(double distanceSetpoint, double angleSetpoint) {
		DISTANCE_SETPOINT = distanceSetpoint;
		ANGLE_SETPOINT = angleSetpoint;
		
		Robot.chassis.reset();
	}

	@Override
	protected void initialize() {
		Robot.chassis.reset();
	}

	private double getDistanceError() {
		return DISTANCE_SETPOINT - distanceInput.get();
	}

	private double getAngleError() {
		return ANGLE_SETPOINT - angleInput.get();
	}

	private boolean onTarget() {
		double distanceTarget = Math.abs(getDistanceError());
		boolean distance = -DISTANCE_TOLERANCE < distanceTarget && distanceTarget < DISTANCE_TOLERANCE;

		double angleTarget = Math.abs(getAngleError());
		boolean angle = -ANGLE_TOLERANCE < angleTarget && angleTarget < ANGLE_TOLERANCE;

		return distance && angle;
	}

	@Override
	protected void execute() {
		if (!onTarget()) {
			Robot.chassis.arcadeDrive2(getDistanceError() * distanceP.get(), getAngleError() * angleP.get());
		} else {
			Robot.chassis.arcadeDrive2(0, 0);
		}

		SmartDashboard.putNumber("Distance error", getDistanceError());
		SmartDashboard.putNumber("Angle error", getAngleError());
		SmartDashboard.putBoolean("On Target", onTarget());
	}

	@Override
	protected void end() {
		Robot.chassis.arcadeDrive2(0, 0);
	}

	@Override
	protected void interrupted() {
		Robot.chassis.arcadeDrive2(0, 0);
		end();
	}

	@Override
	protected boolean isFinished() {
		return onTarget();
	}

}
