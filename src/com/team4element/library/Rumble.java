package com.team4element.library;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.Timer;

public class Rumble {

	private Joystick controller;
	private float rumbleMagnitude = .75f;
	private double timerDelay = .15;
	
	public Rumble(Joystick controller){
		this.controller = controller;
	}
	
	public void setRumbleMagnitude(double rumbleMagnitude){
		this.rumbleMagnitude = (float) rumbleMagnitude;
	}
	
	public void setRumbleMagnitude(float rumbleMagnitude){
		this.rumbleMagnitude = rumbleMagnitude;
	}
	
	public float getRumbleMagnitude(){
		return rumbleMagnitude;
	}
	
	public void setTimerDelay(double timerDelay){
		this.timerDelay = timerDelay;
	}
	
	public double getTimerDelay(){
		return timerDelay;
	}
	
	public void runRumble(){
		controller.setRumble(RumbleType.kLeftRumble, rumbleMagnitude);
		controller.setRumble(RumbleType.kRightRumble, rumbleMagnitude);

		Timer.delay(timerDelay);

		controller.setRumble(RumbleType.kLeftRumble, 0);
		controller.setRumble(RumbleType.kRightRumble, 0);
	}
}
