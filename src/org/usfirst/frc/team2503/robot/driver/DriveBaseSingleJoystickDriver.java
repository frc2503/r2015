package org.usfirst.frc.team2503.robot.driver;

import org.usfirst.frc.team2503.joystick.interfaces.LeftRightForwardBackStickTriggerJoystick;
import org.usfirst.frc.team2503.robot.driveBase.DriveBaseDriveBase;

public class DriveBaseSingleJoystickDriver extends DriveBaseDriveBase implements Driver {
	private LeftRightForwardBackStickTriggerJoystick joystick;
	
	public void drive() {
		if(joystick.getStickTriggerButton()) {
			drive(joystick.getLeftRightAxisValue() * 0.10, joystick.getRightLeftAxisValue() * 0.10, joystick.getLeftRightAxisValue());
		} else {
			drive(joystick.getBackForwardAxisValue(), joystick.getBackForwardAxisValue(), joystick.getLeftRightAxisValue());
		}
	}

	public DriveBaseSingleJoystickDriver(LeftRightForwardBackStickTriggerJoystick _joystick) {
		joystick = _joystick;
	}
	
}
