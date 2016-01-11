package org.usfirst.frc.team4.robot.subsystems;

import org.usfirst.frc.team4.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class Chassis extends Subsystem {
    
	//TODO: Change to actual speed controller
    private VictorSP leftFwd, leftBwd, rightFwd, rightBwd;
    
    private RobotDrive drive;
    
    private Encoder leftEncoder, rightEncoder;
    
    private Gyro gyro;
    
    public Chassis(){
    	//Registers Subsytem
    	super();
    	
    	leftFwd = new VictorSP(RobotMap.CHASSIS_MOTOR_LEFTFRONT);
    	leftBwd = new VictorSP(RobotMap.CHASSIS_MOTOR_LEFTREAR);
    	rightFwd = new VictorSP(RobotMap.CHASSIS_MOTOR_LEFTFRONT);
    	rightBwd = new VictorSP(RobotMap.CHASSIS_MOTOR_LEFTREAR);
    	
    	drive = new RobotDrive(leftFwd, leftBwd, rightFwd, rightBwd);
    	
    	leftEncoder = new Encoder(RobotMap.CHASSIS_LEFT_ENCODER_FWD, RobotMap.CHASSIS_LEFT_ENCODER_BCK);
    	rightEncoder = new Encoder(RobotMap.CHASSIS_RIGHT_ENCODER_FWD, RobotMap.CHASSIS_RIGHT_ENCODER_BCK);
    	
    	gyro = new AnalogGyro(RobotMap.GYRO);
    	
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

