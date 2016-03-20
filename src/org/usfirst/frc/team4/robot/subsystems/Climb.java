package org.usfirst.frc.team4.robot.subsystems;

import org.usfirst.frc.team4.robot.RobotMap;
import org.usfirst.frc.team4.robot.commands.ManualClimbArmController;

import com.team4element.library.ElementMath;

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
	//private final double kPotScaleFactor = 1;

	public boolean isPortculisUp = true;

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
		armTopMotor.set(-ElementMath.squareNumber(speed) * .75);
	}

	public void setBotMotorSpeed(double speed) {
		// Motor's are reversed
		armBotMotor.set(-ElementMath.squareNumber(speed));
	}

	public void setWinchSpeed(double speed) {
		if (isClimbing) {
			double absoluteSpeed = ElementMath.squareNumber(speed);

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

	public void stopArms(){
		armBotMotor.stopMotor();
		armTopMotor.stopMotor();
	}

	private String winchStatus() {
		return isClimbing ? "Enabled" : "Disabled";
	}
	
	public void log() {
		SmartDashboard.getString("Winch", winchStatus());
	}

}
