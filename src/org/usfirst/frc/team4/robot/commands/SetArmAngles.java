package org.usfirst.frc.team4.robot.commands;

import org.usfirst.frc.team4.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetArmAngles extends Command {

	// TODO: Change to actual values
	private final double TOP_kP = .05, TOP_kI = 0, TOP_kD = 0;
	private final double BOT_kP = .05, BOT_kI = 0, BOT_kD = 0;
	private final double kAbsoluteValue = .1;
	
	private PIDController botArmPID, topArmPID;
	
    public SetArmAngles(double botAngle, double topAngle) {
        requires(Robot.climb);
        
		botArmPID = new PIDController(TOP_kP, TOP_kI, TOP_kD, new PIDSource() {
			PIDSourceType m_sourceType = PIDSourceType.kDisplacement;

			public double pidGet() {
				return Robot.climb.getBotArmAngle();
			}

			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				m_sourceType = pidSource;
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return m_sourceType;
			}
		}, new PIDOutput() {
			public void pidWrite(double angle) {
				Robot.climb.setBotMotorSpeed(angle);
			}
		});
		botArmPID.setSetpoint(botAngle);
		botArmPID.setAbsoluteTolerance(kAbsoluteValue);
		
		topArmPID = new PIDController(BOT_kP, BOT_kI, BOT_kD, new PIDSource() {
			PIDSourceType m_sourceType = PIDSourceType.kDisplacement;

			public double pidGet() {
				return Robot.climb.getTopArmAngle();
			}

			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				m_sourceType = pidSource;
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return m_sourceType;
			}
		}, new PIDOutput() {
			public void pidWrite(double angle) {
				Robot.climb.setTopMotorSpeed(angle);
			}
		});
		topArmPID.setSetpoint(botAngle);
		topArmPID.setAbsoluteTolerance(kAbsoluteValue);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	topArmPID.reset();
    	botArmPID.reset();	
    	botArmPID.enable();
    	topArmPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return botArmPID.onTarget() && topArmPID.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	topArmPID.disable();
    	botArmPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
