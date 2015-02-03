package org.usfirst.frc.team2503.joystick;

import org.usfirst.frc.team2503.joystick.interfaces.LeftRightForwardBackRotateSingleThrottleAxisStickTriggerButtonJoystick;

public class MadCatzV1Joystick extends WarriorJoystick implements LeftRightForwardBackRotateSingleThrottleAxisStickTriggerButtonJoystick {
	public boolean getStickTriggerButton() { return getRawButton(1); }
	public boolean get2Button() { return getRawButton(2); }
	public boolean get3Button() { return getRawButton(3); }
	public boolean get4Button() { return getRawButton(4); }
	public boolean get5Button() { return getRawButton(5); }
	public boolean get6Button() { return getRawButton(6); }
	public boolean getGripButton() { return getRawButton(7); }
	
	public int getPov() { return getPOV(0); }
	
	public double getLeftRightAxisValue() { return getRawAxis(0); }
	public double getRightLeftAxisValue() { return -getRawAxis(0); }
	public double getForwardBackAxisValue() { return getRawAxis(1); }
	public double getBackForwardAxisValue() { return -getRawAxis(1); }
	public double getRotateLeftRightAxisValue() { return getRawAxis(3); }
	public double getRotateRightLeftAxisValue() { return -getRawAxis(3); }
	public double getThrottleUpDownAxisValue() { return getRawAxis(2); }
	public double getThrottleDownUpAxisValue() { return 1.0 - getRawAxis(2); }
	
	public MadCatzV1Joystick(WarriorJoystickSide side) {
		super(side);
	}
	
	public MadCatzV1Joystick(final int port) {
		super(port);
	}
}