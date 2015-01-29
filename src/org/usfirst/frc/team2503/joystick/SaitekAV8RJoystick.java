package org.usfirst.frc.team2503.joystick;

import org.usfirst.frc.team2503.joystick.interfaces.LeftRightForwardBackRotateDoubleThrottleAxisStickTriggerButtonJoystick;

public class SaitekAV8RJoystick extends WarriorJoystick implements LeftRightForwardBackRotateDoubleThrottleAxisStickTriggerButtonJoystick {
	public enum SaitekAV8RJoystickBaseMode {
		MODE_OFF(0), MODE_A(1), MODE_B(2);
		
		private final int value;
		
		private SaitekAV8RJoystickBaseMode(final int value) {
			this.value = value;
		}
		
		public final int getValue() {
			return value;
		}
	}
	
	public boolean getStickTriggerButton() { return getRawButton(1); }
	public boolean getStickSafetyButton() { return getRawButton(2); }
	public boolean getStickCenterButton() { return getRawButton(3); }
	public boolean getStickRightButton() { return getRawButton(4); }
	public boolean getBaseT1Switch() { return getRawButton(5); }
	public boolean getBaseT2Switch() { return getRawButton(6); }
	public boolean getBaseT3Switch() { return getRawButton(7); }
	public boolean getBaseT4Switch() { return getRawButton(8); }
	public boolean getBaseT5Switch() { return getRawButton(9); }
	public boolean getBaseT6Switch() { return getRawButton(10); }
	public boolean getBaseT7Switch() { return getRawButton(11); }
	public boolean getBaseT8Switch() { return getRawButton(12); }
	public SaitekAV8RJoystickBaseMode getBaseModeSwitch() {
		boolean a = getRawButton(13);
		boolean b = getRawButton(14);
		
		if(!a && !b) {
			return SaitekAV8RJoystickBaseMode.MODE_OFF;
		} else if(a && !b) {
			return SaitekAV8RJoystickBaseMode.MODE_A; 
		} else if(b && !a) {
			return SaitekAV8RJoystickBaseMode.MODE_B;
		} else {
			return SaitekAV8RJoystickBaseMode.MODE_OFF;
		}
		
	}

	public double getLeftRightAxisValue() { return getRawAxis(0); }
	public double getRightLeftAxisValue() { return -getRawAxis(0); }
	public double getForwardBackAxisValue() { return getRawAxis(1); }
	public double getBackForwardAxisValue() { return -getRawAxis(1); }
	public double getRotateLeftRightAxisValue() { return getRawAxis(3); }
	public double getRotateRightLeftAxisValue() { return -getRawAxis(3); }
	public double getLeftThrottleUpDownAxisValue() { return getRawAxis(2); }
	public double getLeftThrottleDownUpAxisValue() { return 1.0 - getRawAxis(2); }
	public double getRightThrottleUpDownAxisValue() { return getRawAxis(4); }
	public double getRightThrottleDownUpAxisValue() { return 1.0 - getRawAxis(4); }
	
	public SaitekAV8RJoystick(WarriorJoystickSide side) {
		super(side);
	}
	
	public SaitekAV8RJoystick(final int port) {
		super(port);
	}
}