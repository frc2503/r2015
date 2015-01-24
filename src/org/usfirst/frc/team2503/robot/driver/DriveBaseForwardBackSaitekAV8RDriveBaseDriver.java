package org.usfirst.frc.team2503.robot.driver;

import org.usfirst.frc.team2503.joystick.interfaces.ForwardBackJoystick;
import org.usfirst.frc.team2503.joystick.SaitekAV8RJoystick;
import org.usfirst.frc.team2503.robot.driveBase.DriveBaseDriveBase;

public class DriveBaseForwardBackSaitekAV8RDriveBaseDriver extends DriveBaseDriveBase implements Driver {
	private ForwardBackJoystick leftJoystick;
	private SaitekAV8RJoystick rightJoystick;

	public void drive() {
		switch(rightJoystick.getBaseModeSwitch()) {

		/**
		 * If in Mode A (mode switch set to 'A' setting),
		 * only slip.
		 */
		case MODE_A:
			drive(rightJoystick.getLeftRightAxisValue());
			break;
			
		/**
		 * If in Mode B (mode switch set to 'B' setting),
		 * slip and use joystick values.
		 */
		case MODE_B:
			drive(leftJoystick.getBackForwardAxisValue(),
			      rightJoystick.getBackForwardAxisValue(),
			      rightJoystick.getLeftRightAxisValue());
			break;
			
		/**
		 * If in Mode OFF (mode switch set to 'OFF' setting),
		 * or unrecognized return value from
		 *
		 * @method rightJoystick.getBaseModeSwitch()
		 * 
		 * just use joysticks for safety.
		 */
		default:
		case MODE_OFF:
			drive(leftJoystick.getBackForwardAxisValue(),
			      rightJoystick.getBackForwardAxisValue());
			break;
		}

	}
	
	public DriveBaseForwardBackSaitekAV8RDriveBaseDriver(ForwardBackJoystick left, SaitekAV8RJoystick right) {
		leftJoystick = left;
		rightJoystick = right;
	}
}
