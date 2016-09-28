package org.usfirst.frc.team4.robot.subsystems;

import org.usfirst.frc.team4.robot.Robot;
import org.usfirst.frc.team4.robot.RobotMap;
import org.usfirst.frc.team4.robot.commands.DontShoot;
import org.usfirst.frc.team4.robot.commands.Shoot;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {

	private VictorSP shooterTop, shooterBot;
	public boolean isShooting = false;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DontShoot());
	}

	public Shooter() {
		shooterTop = new VictorSP(RobotMap.kShooterTop);
		shooterBot = new VictorSP(RobotMap.kShooterBot);
	}

	public void motorsOn() {
		shooterTop.set(1);
		shooterBot.set(1);
		isShooting = true;

	}

	public void motorsOff() {
		shooterTop.stopMotor();
		shooterBot.stopMotor();
		isShooting = false;

	}
	public void log(){
		if(isShooting == true){
			SmartDashboard.putString("Shooting Motors are:", "on");
		}else{
			SmartDashboard.putString("Shooting Motors are", "off");
			
		}
	}
}