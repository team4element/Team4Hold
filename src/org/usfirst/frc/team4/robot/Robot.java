
package org.usfirst.frc.team4.robot;

import org.usfirst.frc.team4.robot.commands.automodes.SensorlessAutoLowBar_RockyTerrain;
import org.usfirst.frc.team4.robot.commands.automodes.SensorlessRockwall;
import org.usfirst.frc.team4.robot.commands.automodes.TuneDistance;
import org.usfirst.frc.team4.robot.commands.automodes.TuneTurn;
import org.usfirst.frc.team4.robot.commands.routines.AutoDriveController;
import org.usfirst.frc.team4.robot.subsystems.Chassis;
import org.usfirst.frc.team4.robot.subsystems.Climb;
import org.usfirst.frc.team4.robot.subsystems.Intake;
import org.usfirst.frc.team4.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Team4Element | Phoenix XI
 * 
 * @author Justin Punzalan
 * @author Giulliano Millan
 */
public class Robot extends IterativeRobot {

	public static Chassis chassis;
	public static Intake intake;
	public static Climb climb;
	public static Shooter shooter;
	public static OI oi;

	private Command autonomousCommand;
	private SendableChooser chooser;
	
	public static NetworkTable visionTable;
	
	/*
	private CameraServer cameraServer;
	
	public Robot(){
		cameraServer = CameraServer.getInstance();
		cameraServer.setQuality(50);
		cameraServer.startAutomaticCapture("cam0");
	}
	*/
	
	public Robot(){
		visionTable = NetworkTable.getTable("GRIP/trackTarget");
	}
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		ControllerConstants.init();
		chassis = new Chassis();
		intake = new Intake();
		climb = new Climb();
		shooter = new Shooter();
		oi = new OI();

		// Shows that the subsystems are working
		SmartDashboard.putData(chassis);
		SmartDashboard.putData(intake);
		SmartDashboard.putData(climb);

		// Adds Automodes
		 chooser = new SendableChooser();
		//chooser.addObject("Breach and Stop", new BreachAndStop());
		//chooser.addObject("Breach Defence", new BangBangAutoMode(2));
		//chooser.addObject("Track Target", new TrackTarget());
		chooser.addObject("LowBar and RuffTerrian", new SensorlessAutoLowBar_RockyTerrain());
		chooser.addObject("RockWall", new SensorlessRockwall());
		chooser.addObject("woo", new AutoDriveController(0, 180));
		//chooser.addObject("Breach Defence 2", new BangBangAutoMode(2));
		//chooser.addObject("Do Nothing", new DoNothingMode());
		//chooser.addObject("Score Low", new FirstAutonomous());
		
	chooser.addObject("Tune Drive", new TuneDistance());
	chooser.addObject("Tune Turn", new TuneTurn()); 
		SmartDashboard.putData("Auto Modes", chooser);
	//	SmartDashboard.putData("Auto mode", chooser);
	
	}
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional FUNCTIONS
	 * comparisons to the switch structure below with additional strings &
	 * commands.
	 */
	public void autonomousInit() {
		// Checks the Selected 
		autonomousCommand = (Command) chooser.getSelected();
		//autonomousCommand = new Rockwall();
		//autonomousCommand = new AutoLowBar_RockyTerrain();
		
		//autonomousCommand = new TrackTarget();
		if (autonomousCommand != null)
			//visionTable = NetworkTable.getTable("GRIP/trackTarget");
			autonomousCommand.start();
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
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		Robot.chassis.reset();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		log();
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
		shooter.log();
	}

}
