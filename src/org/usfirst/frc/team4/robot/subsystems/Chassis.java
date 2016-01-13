package org.usfirst.frc.team4.robot.subsystems;

import org.usfirst.frc.team4.robot.RobotMap;
import org.usfirst.frc.team4.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Chassis extends Subsystem {

	// TODO: Change to actual speed controller
	private VictorSP leftFwd, leftBwd, rightFwd, rightBwd;

	private RobotDrive drive;

	private Encoder leftEncoder, rightEncoder;

	private AnalogGyro gyro;

	public Chassis() {
		// Registers Subsytem
		super();

		leftFwd = new VictorSP(RobotMap.CHASSIS_MOTOR_LEFTFRONT);
		leftBwd = new VictorSP(RobotMap.CHASSIS_MOTOR_LEFTREAR);
		rightFwd = new VictorSP(RobotMap.CHASSIS_MOTOR_LEFTFRONT);
		rightBwd = new VictorSP(RobotMap.CHASSIS_MOTOR_LEFTREAR);

		drive = new RobotDrive(leftFwd, leftBwd, rightFwd, rightBwd);

		leftEncoder = new Encoder(RobotMap.CHASSIS_LEFT_ENCODER_FWD, RobotMap.CHASSIS_LEFT_ENCODER_BCK);
		rightEncoder = new Encoder(RobotMap.CHASSIS_RIGHT_ENCODER_FWD, RobotMap.CHASSIS_RIGHT_ENCODER_BCK);

		gyro = new AnalogGyro(RobotMap.GYRO);

		liveWindow();

	}

	private void liveWindow() {
		LiveWindow.addActuator("Chassis", "Front_Left Motor", leftFwd);
		LiveWindow.addActuator("Chassis", "Back Left Motor", leftBwd);
		LiveWindow.addActuator("Chassis", "Front Right Motor", rightFwd);
		LiveWindow.addActuator("Chassis", "Back Right Motor", rightBwd);
		LiveWindow.addSensor("Chassis", "Left Encoder", leftEncoder);
		LiveWindow.addSensor("Chassis", "Right Encoder", rightEncoder);
		LiveWindow.addSensor("Chassis", "Gyro", gyro);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new TankDrive());
	}

	public void tankDrive(GenericHID l, GenericHID r) {
		// Squared to make slower speeds easier
		drive.tankDrive(l, r, true);
	}

	public void arcadeDrive(GenericHID stick) {
		// Squared to make slower speeds easier
		drive.arcadeDrive(stick, true);
	}

	public void stop() {
		drive.stopMotor();
	}

	public void log() {
		SmartDashboard.putNumber("Distance", (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2);
		SmartDashboard.putNumber("Left Speed", leftEncoder.getRate());
		SmartDashboard.putNumber("Right Speed", rightEncoder.getRate());
		SmartDashboard.putNumber("Average Speed", (rightEncoder.getRate() + leftEncoder.getRate()) / 2);
		SmartDashboard.putNumber("Gyro", gyro.getAngle());
	}

}
