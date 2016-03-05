package org.usfirst.frc.team4.robot.subsystems;

import org.usfirst.frc.team4.robot.RobotMap;
import org.usfirst.frc.team4.robot.commands.WinchPull;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Climb extends Subsystem {

	private VictorSP armTopMotor, armBotMotor, winchFrontMotor, winchBackMotor;
	private AnalogPotentiometer potTop, potBot;
	// TODO: Change to Actual Value
	private final double kPotScaleFactor = .04;
	
	public boolean isClimbing = false;

	public Climb() {
		super();

		armTopMotor = new VictorSP(RobotMap.kClimbArmMotorTop);
		armBotMotor = new VictorSP(RobotMap.kClimbArmMotorBot);

		winchFrontMotor = new VictorSP(RobotMap.kClimbWinchMotorFront);
		winchBackMotor = new VictorSP(RobotMap.kClimbWinchMotorBack);

		potTop = new AnalogPotentiometer(RobotMap.kClimbArmPotTop, kPotScaleFactor);
		potBot = new AnalogPotentiometer(RobotMap.kClimbArmPotBot, kPotScaleFactor);
	}

	public void initDefaultCommand() {
	}

	public void setTopMotorSpeed(double speed) {
		armTopMotor.set(speed);
	}

	public void setBotMotorSpeed(double speed) {
		armBotMotor.set(speed);
	}

	public void setWinchSpeed(double speed) {
		if (isClimbing) {
			// Fail safe to prevent gearbox from breaking
			// Squared to make smoother
			double absoluteSpeed = -Math.abs(speed * Math.abs(speed));

			winchFrontMotor.set(absoluteSpeed);
			winchBackMotor.set(absoluteSpeed);
		}
	}

	public double getTopArmAngle() {
		return potTop.get();
	}

	public double getBotArmAngle() {
		return potBot.get();
	}

}
