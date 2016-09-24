// @ Author Brandon Comins
package org.usfirst.frc.team4.robot.commands.automodes;

import org.usfirst.frc.team4.robot.commands.AutoDrive;
import org.usfirst.frc.team4.robot.commands.routines.AutoDriveController;
import org.usfirst.frc.team4.robot.commands.routines.BringArmsDown;
import org.usfirst.frc.team4.robot.commands.routines.BringIntakeArmsDown;
import org.usfirst.frc.team4.robot.commands.routines.DriveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShootHigh extends CommandGroup {

	public ShootHigh() {
	addSequential(new BringIntakeArmsDown(), .5);
		addSequential(new BringArmsDown(), .8);
		addSequential(new AutoDrive(), 3);
	}
}
//	public void log(){
		//SmartDashboard.putString("Is running High Shoot", "High Shoot working");
	//}
//}

