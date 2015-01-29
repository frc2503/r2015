package org.usfirst.frc.team2503.robot.driver;

import org.usfirst.frc.team2503.joystick.GamepadF310Controller;
import org.usfirst.frc.team2503.robot.driveBase.DriveBaseDriveBase;

public class DriveBaseGamepadF310ControllerDriveBaseDriver extends DriveBaseDriveBase implements Driver {
	private GamepadF310Controller controller;
	
	public void drive()
	{
		int pov = controller.getPov();
		
		if(pov >= 0) {
			if(pov == 0) {
				drive(1.0 * 0.5, 1.0 * 0.5, 0.0 * 0.5);
			} else if(pov == 45) {
				drive(0.5 * 0.5, 0.5 * 0.5, 0.5 * 0.5);
			} else if(pov == 90) {
				drive(0.0 * 0.5, 0.0 * 0.5, 1.0 * 0.5);
			} else if(pov == 135) {
				drive(-0.5 * 0.5, -0.5 * 0.5, 0.5 * 0.5);
			} else if(pov == 180) {
				drive(-1.0 * 0.5, -1.0 * 0.5, 0.0 * 0.5);
			} else if(pov == 225) {
				drive(-0.5 * 0.5, -0.5 * 0.5, -0.5 * 0.5);
			} else if(pov == 270) {
				drive(0.0 * 0.5, 0.0 * 0.5, -1.0 * 0.5);
			} else if(pov == 315) {
				drive(0.5 * 0.5, 0.5 * 0.5, -0.5 * 0.5);
			} else {
				drive(0.0, 0.0, 0.0);
			}
		} else {
			drive(controller.getLeftBackForwardAxisValue(), controller.getRightBackForwardAxisValue(), (controller.getLeftLeftRightAxisValue() + controller.getRightLeftRightAxisValue()) / 2.0);
		}
	}
	
	public DriveBaseGamepadF310ControllerDriveBaseDriver(GamepadF310Controller controller) {
		this.controller = controller;
	}
}
