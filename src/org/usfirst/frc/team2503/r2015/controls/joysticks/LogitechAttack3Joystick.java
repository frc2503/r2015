package org.usfirst.frc.team2503.r2015.controls.joysticks;

import org.usfirst.frc.team2503.r2015.controls.ControlPort;
import org.usfirst.frc.team2503.r2015.controls.Joystick;

/**
 * The class which represents the Logitech Attack 3 Joystick.
 * 
 * @author Kristofer Rye
 */
public class LogitechAttack3Joystick extends Joystick {
	
	public boolean getStickTriggerButton() { return this.getRawButton(1); }
	public boolean get2Button() { return this.getRawButton(2); }
	public boolean get3Button() { return this.getRawButton(3); }
	public boolean get4Button() { return this.getRawButton(4); }
	public boolean get5Button() { return this.getRawButton(5); }
	public boolean get6Button() { return this.getRawButton(6); }
	public boolean get7Button() { return this.getRawButton(7); }
	public boolean get8Button() { return this.getRawButton(8); }
	public boolean get9Button() { return this.getRawButton(9); }
	public boolean get10Button() { return this.getRawButton(10); }
	public boolean get11Button() { return this.getRawButton(11); }
	
	public boolean isStickTriggerButtonPressed() { return this.getStickTriggerButton(); }
	public boolean isStickTriggerButtonUnpressed() { return !this.isStickTriggerButtonPressed(); }
	public boolean is2ButtonPressed() { return this.get2Button(); }
	public boolean is2ButtonUnpressed() { return !this.is2ButtonPressed(); }
	public boolean is3ButtonPressed() { return this.get3Button(); }
	public boolean is3ButtonUnpressed() { return !this.is3ButtonPressed(); }
	public boolean is4ButtonPressed() { return this.get4Button(); }
	public boolean is4ButtonUnpressed() { return !this.is4ButtonPressed(); }
	public boolean is5ButtonPressed() { return this.get5Button(); }
	public boolean is5ButtonUnpressed() { return !this.is5ButtonPressed(); }
	public boolean is6ButtonPressed() { return this.get6Button(); }
	public boolean is6ButtonUnpressed() { return !this.is6ButtonPressed(); }
	public boolean is7ButtonPressed() { return this.get7Button(); }
	public boolean is7ButtonUnpressed() { return !this.is7ButtonPressed(); }
	public boolean is8ButtonPressed() { return this.get8Button(); }
	public boolean is8ButtonUnpressed() { return !this.is8ButtonPressed(); }
	public boolean is9ButtonPressed() { return this.get9Button(); }
	public boolean is9ButtonUnpressed() { return !this.is9ButtonPressed(); }
	public boolean is10ButtonPressed() { return this.get10Button(); }
	public boolean is10ButtonUnpressed() { return !this.is10ButtonPressed(); }
	public boolean is11ButtonPressed() { return this.get11Button(); }
	public boolean is11ButtonUnpressed() { return !this.is11ButtonPressed(); }
	
	public double getLeftRightAxisValue() { return this.getRawAxis(0); }
	public double getRightLeftAxisValue() { return -this.getRawAxis(0); }
	public double getForwardBackAxisValue() { return this.getRawAxis(1); }
	public double getBackForwardAxisValue() { return -this.getRawAxis(1); }
	public double getScrollUpDownAxisValue() { return this.getRawAxis(2); }
	public double getScrollDownUpAxisValue() { return 1.0 - this.getRawAxis(2); }

	public double getXAxisValue() { return this.getLeftRightAxisValue(); }
	public double getYAxisValue() { return this.getBackForwardAxisValue(); }
	public double getSAxisValue() { return this.getScrollDownUpAxisValue(); }
	
	public LogitechAttack3Joystick(ControlPort controlPort) {
		super(controlPort);
	}
	
	public LogitechAttack3Joystick(final int port) {
		super(port);
	}

}
