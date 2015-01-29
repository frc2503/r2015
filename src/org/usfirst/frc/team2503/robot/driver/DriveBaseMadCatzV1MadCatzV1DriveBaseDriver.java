package org.usfirst.frc.team2503.robot.driver;

import org.usfirst.frc.team2503.Constants;
import org.usfirst.frc.team2503.joystick.MadCatzV1Joystick;
import org.usfirst.frc.team2503.robot.driveBase.DriveBaseDriveBase;

public class DriveBaseMadCatzV1MadCatzV1DriveBaseDriver extends DriveBaseDriveBase implements Driver {
	private MadCatzV1Joystick leftJoystick;
	private MadCatzV1Joystick rightJoystick;
	
	double multiplier;
	
	public void drive() {
		multiplier = (leftJoystick.getThrottleUpDownAxisValue() + rightJoystick.getThrottleUpDownAxisValue()) / 2.0;
		
		boolean leftTrigger = leftJoystick.getStickTriggerButton();
		boolean rightTrigger = rightJoystick.getStickTriggerButton();
		
		if(leftTrigger ^ rightTrigger) {
			drive(multiplier * (leftJoystick.getLeftRightAxisValue() + rightJoystick.getLeftRightAxisValue()) / 2.0);
		} else if(leftTrigger && rightTrigger) {
			drive(multiplier * (leftJoystick.getBackForwardAxisValue()) * Constants.drivePrecisionMultiplier, multiplier * (rightJoystick.getBackForwardAxisValue()));
		} else {
			drive(multiplier * (leftJoystick.getBackForwardAxisValue()), multiplier * (rightJoystick.getBackForwardAxisValue()));
		}
	}
	
	public DriveBaseMadCatzV1MadCatzV1DriveBaseDriver(MadCatzV1Joystick left, MadCatzV1Joystick right) {
		leftJoystick = left;
		rightJoystick = right;
	}
}
