package org.usfirst.frc.team2503.r2015.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import org.usfirst.frc.team2503.r2015.Constants;

public class Clamp {
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

	public Clamp() {
		setPorts(Constants.driveBaseLeftSolenoidChannel, Constants.driveBaseRightSolenoidChannel);
	}
}
