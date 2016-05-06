package org.usfirst.frc.team4.robot.subsystems;

import org.usfirst.frc.team4.robot.ControllerConstants;
import org.usfirst.frc.team4.robot.RobotMap;
import org.usfirst.frc.team4.robot.commands.Drive;

import com.team4element.library.DeadZone;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI.Port;
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

	private final double kDriveFilter = .1, kMaxTurnSpeed = .8;
	private final double kWheelDiameter = 8;
	private final double kPulsePerRev = 128;

	private VictorSP leftFront, leftRear, rightFront, rightRear;
	private RobotDrive drive;
	private Encoder leftEncoder, rightEncoder;
	private ADXRS450_Gyro gyro;

	public Chassis() {
		// Registers Subsystem
		super();

		leftFront = new VictorSP(RobotMap.kChassisMotorLeftFront);
		leftRear = new VictorSP(RobotMap.kChassisMotorLeftRear);
		rightFront = new VictorSP(RobotMap.kChassisMotorRightFront);
		rightRear = new VictorSP(RobotMap.kChassisMotorRightRear);

		leftFront.setInverted(true);
		leftRear.setInverted(true);
		rightFront.setInverted(true);
		rightRear.setInverted(true);

		drive = new RobotDrive(leftFront, leftRear, rightFront, rightRear);

		leftEncoder = new Encoder(RobotMap.kChassisLeftEncoderForward, RobotMap.kChassisLeftEncoderReverse);
		rightEncoder = new Encoder(RobotMap.kChassisRightEncoderForward, RobotMap.kChassisRightEncoderReverse);

		leftEncoder.setDistancePerPulse(-(kWheelDiameter * Math.PI) / kPulsePerRev);
		// Right side is mirrored
		rightEncoder.setDistancePerPulse((kWheelDiameter * Math.PI) / kPulsePerRev);

		gyro = new ADXRS450_Gyro(Port.kOnboardCS0);

	}

	public void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	// NO FILTER
	public void tankDrive(GenericHID controller) {
		double leftMotorOutput = controller.getRawAxis(ControllerConstants.AXIS_LEFT_Y);
		double rightMotorOutput = controller.getRawAxis(ControllerConstants.AXIS_RIGHT_Y);

		drive.tankDrive(leftMotorOutput, rightMotorOutput, false);
	}

	public void tankDrive(GenericHID controller, boolean squareInputs) {
		double leftMotorOutput = controller.getRawAxis(ControllerConstants.AXIS_LEFT_Y);
		double rightMotorOutput = controller.getRawAxis(ControllerConstants.AXIS_RIGHT_Y);

		drive.tankDrive(leftMotorOutput, rightMotorOutput, squareInputs);
	}

	public void tankDrive(double leftInput, double rightInput) {
		drive.tankDrive(leftInput, rightInput, false);
	}

	public void tankDrive(double leftInput, double rightInput, boolean squareInputs) {
		drive.tankDrive(leftInput, rightInput, squareInputs);
	}

	// FILTERED
	public void filteredTankDrive(GenericHID controller) {

		double leftMotorOutput = DeadZone.inputFilter(
				controller.getRawAxis(ControllerConstants.AXIS_LEFT_Y) * gearSetter(currentGear), kDriveFilter);

		double rightMotorOutput = DeadZone.inputFilter(
				controller.getRawAxis(ControllerConstants.AXIS_RIGHT_Y) * gearSetter(currentGear), kDriveFilter);

		if (!isDriveInverse) {
			// True squares outputs
			drive.tankDrive(leftMotorOutput, rightMotorOutput, true);
		} else {
			// Switched to reverse drive
			drive.tankDrive(-rightMotorOutput, -leftMotorOutput, true);
		}
	}

	public void filteredTankDrive(double leftInput, double rightInput) {
		double leftMotorOutput = DeadZone.inputFilter(leftInput * gearSetter(currentGear), kDriveFilter);

		double rightMotorOutput = DeadZone.inputFilter(rightInput * gearSetter(currentGear), kDriveFilter);

		if (!isDriveInverse) {
			// True squares outputs
			drive.tankDrive(leftMotorOutput, rightMotorOutput, true);
		} else {
			// Switched to reverse drive
			drive.tankDrive(-rightMotorOutput, -leftMotorOutput, true);
		}
	}

	public void arcadeDrive(GenericHID controller) {
		double leftMotorOutput = controller.getRawAxis(ControllerConstants.AXIS_LEFT_Y);

		double rightMotorOutput = controller.getRawAxis(ControllerConstants.AXIS_RIGHT_X);

		drive.arcadeDrive(leftMotorOutput, rightMotorOutput);
	}

	public void arcadeDrive(GenericHID controller, boolean squareInputs) {
		double leftMotorOutput = controller.getRawAxis(ControllerConstants.AXIS_LEFT_Y);

		double rightMotorOutput = controller.getRawAxis(ControllerConstants.AXIS_RIGHT_X);

		drive.arcadeDrive(leftMotorOutput, rightMotorOutput, squareInputs);
	}

	public void arcadeDrive(double speed, double angle) {
		drive.arcadeDrive(speed, angle, false);
	}

	public void arcadeDrive(double speed, double angle, boolean squareInputs) {
		drive.arcadeDrive(speed, angle, squareInputs);
	}

	public void filteredArcadeDrive(GenericHID controller) {

		double leftMotorOutput = DeadZone.inputFilter(
				controller.getRawAxis(ControllerConstants.AXIS_LEFT_Y) * gearSetter(currentGear), kDriveFilter);

		double rightMotorOutput = DeadZone.inputFilter(
				controller.getRawAxis(ControllerConstants.AXIS_RIGHT_X) * kMaxTurnSpeed * gearSetter(currentGear),
				kDriveFilter);

		// Squared to make slower speeds easier
		if (!isDriveInverse) {
			drive.arcadeDrive(leftMotorOutput, rightMotorOutput, true);
		} else {
			drive.arcadeDrive(-leftMotorOutput, -rightMotorOutput, true);
		}
	}

	public void filteredArcadeDrive(double speed, double angle) {

		double leftMotorOutput = DeadZone.inputFilter(speed * gearSetter(currentGear), kDriveFilter);

		double rightMotorOutput = DeadZone.inputFilter(angle * kMaxTurnSpeed * gearSetter(currentGear), kDriveFilter);

		// Squared to make slower speeds easier
		if (!isDriveInverse) {
			drive.arcadeDrive(leftMotorOutput, rightMotorOutput, true);
		} else {
			drive.arcadeDrive(-leftMotorOutput, -rightMotorOutput, true);
		}
	}

	public void driveNoTurn(double speed) {
		drive.arcadeDrive(speed, 0);
	}

	public void stop() {
		drive.stopMotor();
	}

	public double gearSetter(DriveSpeed s) {
		double highSpeed = 1;
		double lowSpeed = .75;

		return s == DriveSpeed.HIGH ? highSpeed : lowSpeed;
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

	private String getCurrentGear() {
		return currentGear.toString();
	}

	private String getCurrentDriveMode() {
		return driveState.toString();
	}

	private String getCurrentDriveState() {
		return isDriveInverse ? "Normal Drive" : "Reverse Drive";
	}

	private double getRate() {
		return (rightEncoder.getRate() + leftEncoder.getRate()) / 2;
	}

	public void log() {
		SmartDashboard.putNumber("Robot Speed", getRate());
		SmartDashboard.putString("Drive Mode", getCurrentDriveMode());
		SmartDashboard.putString("Current Gear", getCurrentGear());
		SmartDashboard.putString("Current State", getCurrentDriveState());
		SmartDashboard.putNumber("Robot Distance", getDistance());
		SmartDashboard.putNumber("Gyro", getAngle());
	}
}
