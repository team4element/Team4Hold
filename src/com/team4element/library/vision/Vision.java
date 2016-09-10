package com.team4element.library.vision;

public class Vision {

	public enum TargetPostion {
		LEFT_SIDE,
		RIGHT_SIDE,
		ON_TARGET
	}
	
	public static double getTargetX(){
		return 0;
	}
	
	public static double getTargetY(){
		return 0;
	}
	
	// TODO: Do Thing
	public static double getTargetHeightPixels() {
		return 0;
	}

	// TODO: Do Thing
	public static double getTargetHeightFeet() {
		return 0;
	}

	// TODO: Do Thing
	public static double getTargetWidthPixels() {
		return 0;
	}

	// TODO: Do Thing
	public static double getTargetWidthFeet() {
		return 0;
	}

	public static double getFeetToPixelRatio() {
		return getTargetHeightFeet() / getTargetHeightPixels();
	} 
	
	public static TargetPostion getTargetSide(){
		double centerX = VisionConstants.CAMERA_CENTER_WIDTH;
		//double centerY = VisionConstants.CAMERA_CENTER_WIDTH;
		
		if (getTargetX() > centerX){
			return TargetPostion.RIGHT_SIDE;
		} else if (getTargetX() < centerX) {
			return TargetPostion.LEFT_SIDE;
		} else {
			return TargetPostion.ON_TARGET;
		}
	}
	
	public static double getHorizantalDistanceToTargetPixels(){
		
		
		return 0;
	}
	
	public static double getHorizantalDistanceToTargetFeet(){
		return 0;
	}
	
	public static double getDistanceToTarget(){
		double distance;
		double fovPixels = VisionConstants.CAMERA_FOV_PIXELS;
		double fovVerticleDegrees = VisionConstants.CAMERA_FOV_VERTICAL_DEGREES;
		
		// We need Vertical Angle
		distance = ((fovPixels * getFeetToPixelRatio())/2)
				/(Math.tan(fovVerticleDegrees/2));
		
		return distance;
	}
	
	public static double getAngleToTarget(){
		return 0;
	}

}
