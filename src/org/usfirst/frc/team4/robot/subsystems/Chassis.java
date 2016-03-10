package org.usfirst.frc.team4.robot.subsystems;

import org.usfirst.frc.team4.robot.ControllerConstants;
import org.usfirst.frc.team4.robot.RobotMap;
import org.usfirst.frc.team4.robot.commands.Drive;

import com.team4element.library.JerkFilter;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Chassis extends Subsystem {
	
	// Toggle Drive
	public enum DriveState {
		ARCADE, TANK;
	}

	// Drive Speeds
	public enum DriveSpeed {
		HIGH, LOW;
	}

	public DriveSpeed currentGear = DriveSpeed.HIGH;
	public DriveState driveState = DriveState.TANK;
	public boolean isDriveInverse = false;

	private Double kJERK_REDUCTION = .2;

	// TODO: Change to actual speed controller
	private VictorSP leftFwd, leftBwd, rightFwd, rightBwd;
	private RobotDrive drive;
	private Encoder leftEncoder, rightEncoder;
	private JerkFilter jerkFilter;
	private AnalogGyro gyro;

	public Chassis() {
		// Registers Subsystem
		super();

		leftFwd = new VictorSP(RobotMap.kChassisMotorLeftFront);
		leftBwd = new VictorSP(RobotMap.kChassisMotorLeftRear);
		rightFwd = new VictorSP(RobotMap.kChassisMotorRightFront);
		rightBwd = new VictorSP(RobotMap.kChassisMotorRightRear);

		leftFwd.setInverted(true);
		leftBwd.setInverted(true);
		rightFwd.setInverted(true);
		rightBwd.setInverted(true);

		drive = new RobotDrive(leftFwd, leftBwd, rightFwd, rightBwd);

		leftEncoder = new Encoder(RobotMap.kChassisLeftEncoderForward, RobotMap.kChassisLeftEncoderReverse);
		rightEncoder = new Encoder(RobotMap.kChassisRightEncoderForward, RobotMap.kChassisRightEncoderReverse);

		gyro = new AnalogGyro(RobotMap.kGyro);
		
	}
 
	public void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	public void tankDrive(GenericHID c) {
		// Squared to make slower easier
		if (!isDriveInverse) {
			drive.tankDrive(
					driveFilter(c.getRawAxis(ControllerConstants.AXIS_LEFT_Y) * gearSetter(currentGear),
							kJERK_REDUCTION),
					driveFilter(c.getRawAxis(ControllerConstants.AXIS_RIGHT_Y) * gearSetter(currentGear),
							kJERK_REDUCTION),
					true);
		} else {
			drive.tankDrive(
					-driveFilter(c.getRawAxis(ControllerConstants.AXIS_RIGHT_Y) * gearSetter(currentGear),
							kJERK_REDUCTION),
					-driveFilter(c.getRawAxis(ControllerConstants.AXIS_LEFT_Y) * gearSetter(currentGear),
							kJERK_REDUCTION),
					true);
		}
	}

	public void arcadeDrive(GenericHID stick) {
		
		// Squared to make slower speeds easier
		if (!isDriveInverse) {
			drive.arcadeDrive(
					driveFilter(stick.getRawAxis(ControllerConstants.AXIS_LEFT_Y) * gearSetter(currentGear),
							kJERK_REDUCTION),
					driveFilter(stick.getRawAxis(ControllerConstants.AXIS_RIGHT_X) * gearSetter(currentGear),
							kJERK_REDUCTION),
					true);
		} else {
			drive.arcadeDrive(
					-driveFilter(stick.getRawAxis(ControllerConstants.AXIS_LEFT_Y) * gearSetter(currentGear),
							kJERK_REDUCTION),
					driveFilter(stick.getRawAxis(ControllerConstants.AXIS_RIGHT_X) * gearSetter(currentGear),
							kJERK_REDUCTION),
					true);
		}
	}
	
	public void arcadeDrive(double speed, double angle){
		drive.arcadeDrive(speed, angle);
	}

	public void stop() {
		drive.stopMotor();
	}

	// Reduces Jerk
	public double driveFilter(double n, double t) {
		return Math.abs(n) > t ? n : 0;
	}

	public double gearSetter(DriveSpeed s) {
		return s == DriveSpeed.HIGH ? 1.0 : .75;
	}

	public double getDistance() {
		return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
	}

	public double getAngle() {
		return gyro.getAngle();
	}

	public void reset() {
		gyro.reset();
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	private String getCurrentGear(){
		return currentGear.toString();
	}
	
	private String getCurrentDriveMode(){
		return driveState.toString();
	}
	
	private String getCurrentDriveState(){
		return isDriveInverse ? "Normal Drive" : "Reverse Drive";
	}
	
	private double getRate(){
		return (rightEncoder.getRate() + leftEncoder.getRate()) / 2;
	}

	public void log() {
		SmartDashboard.putNumber("Robot Speed", getRate());
		SmartDashboard.putString("Drive Mode", getCurrentDriveMode());
		SmartDashboard.putString("Current Gear", getCurrentGear());
		SmartDashboard.putString("Current State", getCurrentDriveState());
		SmartDashboard.putNumber("Gyro", gyro.getAngle());
	}
}
