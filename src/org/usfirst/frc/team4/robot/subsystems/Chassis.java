package org.usfirst.frc.team4.robot.subsystems;

import org.usfirst.frc.team4.robot.ControllerConstants;
import org.usfirst.frc.team4.robot.RobotMap;
import org.usfirst.frc.team4.robot.commands.Drive;

import com.kauailabs.navx.frc.AHRS;
import com.team4element.library.DeadZone;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Chassis extends Subsystem {

	public boolean drive_slow = false;
	public boolean isDriveInverse = false;
	private final double kDriveFilter = .1;
	private final double kWheelDiameter = 8;
	private final double kPulsePerRev = 128;
	private final double distance_per_pulse = (kWheelDiameter * Math.PI) / kPulsePerRev;

	private VictorSP leftFront, leftRear, rightFront, rightRear;
	private RobotDrive drive;
	private Encoder leftEncoder, rightEncoder;
	public AHRS gyro;

	public Chassis() {
		// Registers Subsystem
		super();

		leftFront = new VictorSP(RobotMap.kChassisMotorLeftFront);
		leftRear = new VictorSP(RobotMap.kChassisMotorLeftRear);
		rightRear = new VictorSP(RobotMap.kChassisMotorRightRear);
		rightFront = new VictorSP(RobotMap.kChassisMotorRightFront);
		
		

		leftFront.setInverted(true);
		leftRear.setInverted(true);
		rightFront.setInverted(true);
		rightRear.setInverted(true);

		drive = new RobotDrive(leftFront, leftRear, rightFront, rightRear);

		leftEncoder = new Encoder(RobotMap.kChassisLeftEncoderForward, RobotMap.kChassisLeftEncoderReverse);
		rightEncoder = new Encoder(RobotMap.kChassisRightEncoderForward, RobotMap.kChassisRightEncoderReverse);

		leftEncoder.setDistancePerPulse(-distance_per_pulse);
		rightEncoder.setDistancePerPulse(distance_per_pulse);

		try {
			gyro = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
		}
	}

	public void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	// CALLED BY COMMANDS
	public void arcadeDrive(GenericHID controller) {
		double speed = DeadZone.inputFilter(controller.getRawAxis(ControllerConstants.AXIS_LEFT_Y), kDriveFilter);
		double angle = DeadZone.inputFilter(controller.getRawAxis(ControllerConstants.AXIS_RIGHT_X), kDriveFilter);
		filter_arcade_inputs(speed, angle);
	}

	// FILTER HELPER METHOD. NO NEED TO OVERLOAD
	private void filter_arcade_inputs(double speed, double angle) {
		double speed_const = drive_slow ? 1 : .75;
		arcadeDrive2(speed * speed_const, angle);
	}

	// THIS IS THE MAIN ARCADE DRIVE METHOD Also used by auto commands
	public void arcadeDrive2(double speed, double angle) {
		drive.arcadeDrive(speed, angle);
	}

	public void driveStraight(double speed) {
		drive.arcadeDrive(speed, 0);
	}

	public void stop() {
		drive.stopMotor();
	}

	public double getDistance() {
		SmartDashboard.putNumber("Left encoder", getLeftDistance());
		SmartDashboard.putNumber("Right encoder", getRightDistance());
		return (getLeftDistance() + getRightDistance()) / 2;
	}

	public double getLeftDistance() {
		return leftEncoder.getDistance();
	}

	public double getRightDistance() {
		return rightEncoder.getDistance();
	}

	public double getAngle() {
		return gyro.getYaw();
	}

	private String getCurrentDriveState() {
		return !isDriveInverse ? "Normal Drive" : "Reverse Drive";
	}

	public void reset() {
		gyro.reset();
//		gyro.resetDisplacement();
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public double updateGyro() {
		return gyro.getAngle();
	}

	private double getRate() {
		// Negative encoder values because we want driving backwards to have a
		// positive setpoint
		return -(rightEncoder.getRate() + leftEncoder.getRate()) / 2;
	}

	public void log() {
		SmartDashboard.putNumber("Robot Speed", getRate());
		// SmartDashboard.putNumber("Left Distance", getLeftDistance());
		// SmartDashboard.putNumber("Right Distance", getRightDistance());
		
		SmartDashboard.putNumber("Robot Distance", getDistance());
		SmartDashboard.putNumber("Gyro", getAngle());
		SmartDashboard.putBoolean("isSlower", drive_slow);
		
		
//		SmartDashboard.putNumber("Left motor", (leftFront.get() + leftRear.get()) / 2);
//		SmartDashboard.putNumber("Right motor", (rightFront.get() + rightRear.get()) / 2);
	}
}
