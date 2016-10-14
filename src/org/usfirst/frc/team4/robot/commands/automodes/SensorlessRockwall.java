// @ Author Brandon Comins
package org.usfirst.frc.team4.robot.commands.automodes;

import org.usfirst.frc.team4.robot.commands.AutoDrive;
import org.usfirst.frc.team4.robot.commands.routines.AutoDriveController;
import org.usfirst.frc.team4.robot.commands.routines.BringArmsDown;
import org.usfirst.frc.team4.robot.commands.routines.BringIntakeArmsDown;
import org.usfirst.frc.team4.robot.commands.routines.DriveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SensorlessRockwall extends CommandGroup {

	public SensorlessRockwall() {
	addSequential(new BringIntakeArmsDown(), .5);
		addSequential(new BringArmsDown(), 1);
		addSequential(new AutoDrive(), 2);
	}
}


