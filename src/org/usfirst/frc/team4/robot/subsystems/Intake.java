package org.usfirst.frc.team4.robot.subsystems;

import org.usfirst.frc.team4.robot.Robot;
import org.usfirst.frc.team4.robot.RobotMap;
import org.usfirst.frc.team4.robot.commands.IntakeController;

import com.team4element.library.DeadZone;
import com.team4element.library.ElementMath;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Intake extends Subsystem {

	private VictorSP intakeRoller, intakeArm;
	private double kMaxArmSpeed = .5, kArmFilter = .1;

	public Intake() {
		super();

		intakeRoller = new VictorSP(RobotMap.kIntakeMotorRoller);
		intakeArm = new VictorSP(RobotMap.kIntakeMotorArm);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new IntakeController());
	}

	public void setRollerSpeed(double speed) {
		intakeRoller.set(speed);
	}

	public void setArmOutput(double angle) {
		double filteredOutput = DeadZone.inputFilter((ElementMath.squareNumber(angle) * kMaxArmSpeed), kArmFilter);
		
		intakeArm.set(filteredOutput);
	}

	public void stopRoller() {
		intakeRoller.stopMotor();
	}

	public void stopArm() {
		intakeArm.stopMotor();
	}

	public void stop() {
		stopArm();
		stopRoller();
	}

	private String intakeStatus() {
		return Robot.climb.isClimbing ? "Disabled" : "Enabled";
	}

	public void log() {
		SmartDashboard.getString("Intake: ", intakeStatus());
	}

}
