package org.usfirst.frc.team2503.joystick;

import org.usfirst.frc.team2503.joystick.interfaces.LeftRightForwardBackStickTriggerJoystick;
import org.usfirst.frc.team2503.joystick.interfaces.ScrollJoystick;

public class LogitechAttack3Joystick extends WarriorJoystick implements LeftRightForwardBackStickTriggerJoystick, ScrollJoystick {
	public boolean getStickTriggerButton() { return getRawButton(1); }
	public boolean getStickBackButton() { return getRawButton(2); }
	public boolean getStickFrontButton() { return getRawButton(3); }
	public boolean getStickLeftButton() { return getRawButton(4); }
	public boolean getStickRightButton() { return getRawButton(5); }
	public boolean getBaseLeftForwardButton() { return getRawButton(6); }
	public boolean getBaseLeftBackButton() { return getRawButton(7); }
	public boolean getBaseCenterLeftButton() { return getRawButton(8); }
	public boolean getBaseCenterRightButton() { return getRawButton(9); }
	public boolean getBaseRightBackButton() { return getRawButton(10); }
	public boolean getBaseRightForwardButton() { return getRawButton(11); }

	public double getLeftRightAxisValue() { return getRawAxis(0); }
	public double getRightLeftAxisValue() { return -getRawAxis(0); }
	public double getForwardBackAxisValue() { return getRawAxis(1); }
	public double getBackForwardAxisValue() { return -getRawAxis(1); }
	public double getScrollUpDownAxisValue() { return getRawAxis(2); }
	public double getScrollDownUpAxisValue() { return 1.0 - getRawAxis(2); }

	public LogitechAttack3Joystick(WarriorJoystickSide side) {
		super(side);
	}
	
	public LogitechAttack3Joystick(final int port) {
		super(port);
	}
}
