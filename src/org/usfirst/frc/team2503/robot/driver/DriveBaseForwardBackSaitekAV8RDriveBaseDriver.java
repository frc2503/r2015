package org.usfirst.frc.team2503.robot.driver;

import org.usfirst.frc.team2503.joystick.interfaces.ForwardBackAxisJoystick;
import org.usfirst.frc.team2503.joystick.SaitekAV8RJoystick;
import org.usfirst.frc.team2503.robot.driveBase.DriveBaseDriveBase;

public class DriveBaseForwardBackSaitekAV8RDriveBaseDriver extends DriveBaseDriveBase implements Driver {
	private ForwardBackAxisJoystick leftJoystick;
	private SaitekAV8RJoystick rightJoystick;

	public void drive() {
		drive(leftJoystick.getBackForwardAxisValue(), rightJoystick.getBackForwardAxisValue());
	}
	
	public DriveBaseForwardBackSaitekAV8RDriveBaseDriver(ForwardBackAxisJoystick left, SaitekAV8RJoystick right) {
		leftJoystick = left;
		rightJoystick = right;
	}
}
