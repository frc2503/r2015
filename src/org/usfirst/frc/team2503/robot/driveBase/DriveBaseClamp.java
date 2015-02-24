package org.usfirst.frc.team2503.robot.driveBase;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import org.usfirst.frc.team2503.Constants;

public class DriveBaseClamp implements Clamp {
	public DoubleSolenoid solenoid;
	
	public void set(ClampStatus status) {
		switch(status) {
		case INACTIVE:
			break;
		case OPEN:
			solenoid.set(Value.kForward);
			break;
		case CLOSE:
			solenoid.set(Value.kReverse);
			break;
			
		default:
			System.err.println("What the hell is going on?");	
		}
	}
	
	public void setPorts(final int forwardChannel, final int reverseChannel) { solenoid = new DoubleSolenoid(forwardChannel, reverseChannel); }

	public DriveBaseClamp() {
		setPorts(Constants.driveBaseLeftSolenoidChannel, Constants.driveBaseRightSolenoidChannel);
	}
}
