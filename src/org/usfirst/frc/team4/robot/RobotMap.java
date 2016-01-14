package org.usfirst.frc.team4.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static final int 
    STICK_DRIVE       = 0,

    CONT_A            = 1,
    CONT_B            = 2,
    CONT_X            = 3,
    CONT_Y            = 4,

    CONT_L1           = 5,
    CONT_R1           = 6,

    CONT_L2           = 2,
    CONT_R2           = 3,

    CONT_SELECT       = 7,
    CONT_START        = 8,

    CONT_L3           = 9,
    CONT_R3           =10,

    CONT_LY           = 1,
    CONT_LX           = 0,

    CONT_RY           = 5,
    CONT_RX           = 4,
    	    //Chassis
    CHASSIS_MOTOR_LEFTFRONT   = 3,
    CHASSIS_MOTOR_LEFTREAR    = 2,
    CHASSIS_MOTOR_RIGHTFRONT  = 6,
    CHASSIS_MOTOR_RIGHTREAR   = 7,
    
    //TODO: Put Actual Values
    GYRO = 1,
    
    CHASSIS_LEFT_ENCODER_FWD = 1,
    CHASSIS_LEFT_ENCODER_BCK = 7,

    CHASSIS_RIGHT_ENCODER_FWD = 3,
    CHASSIS_RIGHT_ENCODER_BCK = 8;

	public static Joystick driveCont;
	
	public static JoystickButton
	    driveA,
	    driveB,
	    driveX,
	    driveY,
	
	    driveL1,
	    driveR1,
	    
	    driveL2,
	    driveR2,
	
	    driveSelect,
	    driveStart,
	
	    driveL3,
	    driveR3;
	
	public static void init () {
	    driveCont       = new Joystick(STICK_DRIVE);
	    
	    driveA          = new JoystickButton(driveCont, CONT_A);
	    driveB          = new JoystickButton(driveCont, CONT_B);
	    driveX          = new JoystickButton(driveCont, CONT_X);
	    driveY          = new JoystickButton(driveCont, CONT_Y);
	
	    driveL1         = new JoystickButton(driveCont, CONT_L1);
	    driveR1         = new JoystickButton(driveCont, CONT_R1);
	    
	    driveL2         = new JoystickButton(driveCont, CONT_L2);
	    driveR2         = new JoystickButton(driveCont, CONT_R2);
	    
	    driveSelect     = new JoystickButton(driveCont, CONT_SELECT);
	    driveStart      = new JoystickButton(driveCont, CONT_START);
	
	    driveL3         = new JoystickButton(driveCont, CONT_L3);
	    driveR3         = new JoystickButton(driveCont, CONT_R3);    
	}
	
}
