package org.usfirst.frc.team2503.joystick;

import org.usfirst.frc.team2503.joystick.WarriorJoystick;

public class GamepadF310Controller extends WarriorJoystick {
	public double getLeftLeftRightAxisValue() { return getRawAxis(0); }
	public double getLeftRightLeftAxisValue() { return -getRawAxis(0); }
	
	public double getLeftForwardBackAxisValue() { return getRawAxis(1); }
	public double getLeftBackForwardAxisValue() { return -getRawAxis(1); }
	
	public double getLeftTriggerDepressedPressedAxisValue() { return Math.abs(getRawAxis(2)); }
	public double getLeftTriggerPressedDepressedAxisValue() { return 1.0 - Math.abs(getRawAxis(2)); }

	public double getRightTriggerDepressedPressedAxisValue() { return Math.abs(getRawAxis(3)); }
	public double getRightTriggerPressedDepressedAxisValue() { return 1.0 - Math.abs(getRawAxis(3)); }
	
	public double getRightLeftRightAxisValue() { return getRawAxis(4); }
	public double getRightRightLeftAxisValue() { return -getRawAxis(4); }
	
	public double getRightForwardBackAxisValue() { return getRawAxis(5); }
	public double getRightBackForwardAxisValue() { return -getRawAxis(5); }
	
	public boolean getAButton() { return getRawButton(1); }
	public boolean getBButton() { return getRawButton(2); }
	public boolean getXButton() { return getRawButton(3); }
	public boolean getYButton() { return getRawButton(4); }
	public boolean getLeftBumperButton() { return getRawButton(5); }
	public boolean getRightBumperButton() { return getRawButton(6); }	
	public boolean getBackButton() { return getRawButton(7); }
	public boolean getStartButton() { return getRawButton(8); }
	public boolean getLeftStickPressed() { return getRawButton(9); }
	public boolean getRightStickPressed() { return getRawButton(10); }
	
	public int getPov() { return getPOV(0); }
	
	public GamepadF310Controller(final WarriorJoystickSide side) {
		super(side.getValue());
	}
	
	public GamepadF310Controller(final int port) {
		super(port);
	}
}
