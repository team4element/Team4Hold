
package org.usfirst.frc.team4.robot;

import org.usfirst.frc.team4.robot.commands.automodes.DoNothingMode;
import org.usfirst.frc.team4.robot.commands.automodes.FifthAutonomous;
import org.usfirst.frc.team4.robot.commands.automodes.FirstAutonomous;
import org.usfirst.frc.team4.robot.commands.automodes.FourthAutonomous;
import org.usfirst.frc.team4.robot.commands.automodes.SecondAutonomous;
import org.usfirst.frc.team4.robot.commands.automodes.ThirdAutonomous;
import org.usfirst.frc.team4.robot.commands.automodes.TunePID;
import org.usfirst.frc.team4.robot.subsystems.Chassis;
import org.usfirst.frc.team4.robot.subsystems.Climb;
import org.usfirst.frc.team4.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static Chassis chassis;
	public static Intake intake;
	public static Climb climb;
	//private CameraServer cameraServer;
    Command autonomousCommand;
    SendableChooser chooser;
/*
    public Robot(){
    
    	cameraServer = CameraServer.getInstance();
        cameraServer.setQuality(50);
        cameraServer.startAutomaticCapture("cam2");
    }

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		ControllerConstants.init();
    	oi = new OI();
    	chassis = new Chassis();
    	intake = new Intake();
    	climb = new Climb();
    	
    	// Shows that the subsystems are working
        SmartDashboard.putData(chassis);
        SmartDashboard.putData(intake);
        SmartDashboard.putData(climb);
    	
        chooser = new SendableChooser();
        chooser.addObject("First", new FirstAutonomous());
        chooser.addObject("Second", new SecondAutonomous());
        chooser.addObject("Third", new ThirdAutonomous());
        chooser.addObject("Fourth", new FourthAutonomous());
        chooser.addObject("Fifth", new FifthAutonomous());
        chooser.addObject("Tune", new TunePID());
        chooser.addObject("Default", new DoNothingMode());
        SmartDashboard.putData("Auto mode", chooser);
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional FUNCTIONS comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
    	String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "First":
			autonomousCommand = new FirstAutonomous();
			break;
		case "Second":
			autonomousCommand = new SecondAutonomous();
			break;
		case "Third":
			autonomousCommand = new ThirdAutonomous();
			break;
		case "Fourth":
			autonomousCommand = new FourthAutonomous();
			break;
		case "Fifth":
			autonomousCommand = new FifthAutonomous();
			break;
		case "Tune":
			autonomousCommand = new TunePID();
			break;
		case "Default":
		default:
			autonomousCommand = new DoNothingMode();
			break;
		} 
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        log();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        log();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    private void log() {
       chassis.log();
       climb.log();
       intake.log();
    }
}
