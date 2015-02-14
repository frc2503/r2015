package org.usfirst.frc.team2503.robot.driver;

import org.usfirst.frc.team2503.joystick.GamepadF310Controller;
import org.usfirst.frc.team2503.robot.driveBase.DriveBaseDriveBase;

public class DriveBaseGamepadF310ControllerDriveBaseDriver extends DriveBaseDriveBase implements Driver {
	private GamepadF310Controller controller;
	
	public void drive()
	{
		int pov = controller.getPOV();

		if(pov >= 0) {
			winch(-((double)(pov - 90) / 90.0));
		} else {
			winch(0.0);
		}
		
		drive(controller.getLeftBackForwardAxisValue(), controller.getRightForwardBackAxisValue());
	}
	
	public DriveBaseGamepadF310ControllerDriveBaseDriver(GamepadF310Controller controller) {
		this.controller = controller;
	}
}
