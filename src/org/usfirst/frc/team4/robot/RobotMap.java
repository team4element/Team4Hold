package org.usfirst.frc.team4.robot;

import edu.wpi.first.wpilibj.SPI.Port;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static int kChassisMotorLeftFront = 4;
	public static int kChassisMotorLeftRear = 5;
	public static int kChassisMotorRightFront = 3;
	public static int kChassisMotorRightRear = 2;

	// TODO: Put Actual Values
	public static int kGyro = 0;

	public static int kChassisLeftEncoderForward = 2;
	public static int kChassisLeftEncoderReverse = 3;

	public static int kChassisRightEncoderForward = 0;
	public static int kChassisRightEncoderReverse = 1;

	// Intake
	public static int kIntakeMotorRoller = 1;
	public static int kIntakeMotorArm = 0;

	// Climb
	public static int kClimbWinchMotorFront = 6;
	public static int kClimbWinchMotorBack = 7;
	
	public static int kClimbArmMotorBot = 8;
	public static int kClimbArmMotorTop = 9;
	
	public static int kClimbArmPotTop = 1;
	public static int kClimbArmPotBot = 2;

}
