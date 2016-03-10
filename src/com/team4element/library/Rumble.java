package com.team4element.library;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.Timer;

public class Rumble {

	public Rumble(Joystick controller){
		controller.setRumble(RumbleType.kLeftRumble, .75f);
		controller.setRumble(RumbleType.kRightRumble, .75f);

		Timer.delay(.15);

		controller.setRumble(RumbleType.kLeftRumble, 0);
		controller.setRumble(RumbleType.kRightRumble, 0);
	}
	
	// Magnitude should be from 0 to 1
	public Rumble(Joystick controller, float rumbleMagnitude){
		controller.setRumble(RumbleType.kLeftRumble, rumbleMagnitude);
		controller.setRumble(RumbleType.kRightRumble, rumbleMagnitude);

		Timer.delay(.15);

		controller.setRumble(RumbleType.kLeftRumble, rumbleMagnitude);
		controller.setRumble(RumbleType.kRightRumble, rumbleMagnitude);
	}
	
	public Rumble(Joystick controller,float rumbleMagnitude, double delay){
		controller.setRumble(RumbleType.kLeftRumble, rumbleMagnitude);
		controller.setRumble(RumbleType.kRightRumble, rumbleMagnitude);

		Timer.delay(delay);

		controller.setRumble(RumbleType.kLeftRumble, rumbleMagnitude);
		controller.setRumble(RumbleType.kRightRumble, rumbleMagnitude);
	}
	
}
