package org.usfirst.frc.team4.robot.subsystems;

import org.usfirst.frc.team4.robot.RobotMap;
import org.usfirst.frc.team4.robot.commands.ManualClimbArmController;

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
	private final double kPotScaleFactor = 1;

	public boolean isPortculisUp = false;

	public enum ClimbState {
		BOTH_ARMS_DOWN, TOP_ARM_RISING, BOTH_ARMS_RISING, PULLING_UP, DONE;
	}

	public ClimbState[] climbStates = ClimbState.values();
	public ClimbState currentClimbState = climbStates[0];

	public boolean isClimbing = false;

	public Climb() {
		super();

		armTopMotor = new VictorSP(RobotMap.kClimbArmMotorTop);
		armBotMotor = new VictorSP(RobotMap.kClimbArmMotorBot);

		winchFrontMotor = new VictorSP(RobotMap.kClimbWinchMotorFront);
		winchBackMotor = new VictorSP(RobotMap.kClimbWinchMotorBack);

		potTop = new AnalogPotentiometer(RobotMap.kClimbArmPotTop);
		potBot = new AnalogPotentiometer(RobotMap.kClimbArmPotBot);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ManualClimbArmController());
	}

	public void setTopMotorSpeed(double speed) {
		// Motor's are reversed
		armTopMotor.set(-squareInput(speed) * .75);
	}

	public void setBotMotorSpeed(double speed) {
		// Motor's are reversed
		armBotMotor.set(-squareInput(speed));
	}

	public void setWinchSpeed(double speed) {
		if (isClimbing) {
			// Fail safe to prevent gearbox from breaking
			double absoluteSpeed = -squareInput(speed);
			
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

	public void stopWinch() {
		winchBackMotor.stopMotor();
		winchFrontMotor.stopMotor();
	}

	private double squareInput(double speed) {
		return speed * Math.abs(speed);
	}

	private String winchStatus() {
		return isClimbing ? "Enabled" : "Disabled";
	}
	
	public void log() {
		SmartDashboard.getString("Winch", winchStatus());
	}

}
