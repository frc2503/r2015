package org.usfirst.frc.team2503.robot.driver;

import org.usfirst.frc.team2503.joystick.MadCatzV1Joystick;
import org.usfirst.frc.team2503.robot.driveBase.DriveBaseDriveBase;

public class DriveBaseMadCatzV1MadCatzV1DriveBaseDriver extends DriveBaseDriveBase implements Driver {
	private MadCatzV1Joystick leftJoystick;
	private MadCatzV1Joystick rightJoystick;
	
	private double multiplier;
	
	public void drive() {
		if(leftJoystick.getGripButton() || rightJoystick.getGripButton()) {
			multiplier = 0.4;
		} else {
			multiplier = 1.0;
		}
		
		if(leftJoystick.getStickTriggerButton()) {
			drive(multiplier * leftJoystick.getLeftRightAxisValue());
		} else {
			drive(multiplier * leftJoystick.getBackForwardAxisValue(), multiplier * rightJoystick.getForwardBackAxisValue());
		}
		
		if(rightJoystick.getStickTriggerButton()) {
			winch(1.0);
		} else if(rightJoystick.get2Button()) {
			winch(-1.0);
		} else {
			winch(0.0);
		}
		
		if(leftJoystick.get2Button()) {
			lights(true);
		} else {
			lights(false);
		}
	}
	
	public DriveBaseMadCatzV1MadCatzV1DriveBaseDriver(MadCatzV1Joystick left, MadCatzV1Joystick right) {
		leftJoystick = left;
		rightJoystick = right;
	}
}
