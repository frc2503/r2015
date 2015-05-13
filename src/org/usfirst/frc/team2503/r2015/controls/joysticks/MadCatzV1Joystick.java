package org.usfirst.frc.team2503.r2015.controls.joysticks;

import org.usfirst.frc.team2503.r2015.controls.ControlPort;
import org.usfirst.frc.team2503.r2015.controls.Joystick;

/**
 * The class which represents the MadCatz V1 Joystick.
 *
 * @author Kristofer Rye
 */
public class MadCatzV1Joystick extends Joystick {

	public boolean getStickTriggerButton() { return this.getRawButton(1); }
	public boolean get2Button() { return this.getRawButton(2); }
	public boolean get3Button() { return this.getRawButton(3); }
	public boolean get4Button() { return this.getRawButton(4); }
	public boolean get5Button() { return this.getRawButton(5); }
	public boolean get6Button() { return this.getRawButton(6); }
	public boolean getGripButton() { return this.getRawButton(7); }

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
	public boolean isGripButtonPressed() { return this.getGripButton(); }
	public boolean isGripButtonUnpressed() { return !this.isGripButtonPressed(); }

	/**
	 * Get the value of the POV switch on this Joystick.
	 *
	 * Note that getPOV (and hence this method) returns an integer value, where
	 * a value of 0 means that the POV is pointed NORTH, not EAST.
	 *
	 * If you're looking for a double value, use the below getPovDegrees() method.
	 *
	 * If you're looking for a double value, which could be passed to trigonometric
	 * functions (say Math.sin(double)), use the below getStandardPovDegrees()
	 * method.
	 */
	public int getPov() { return this.getPOV(0); }

	public double getPovDegrees() { return (double)this.getPov(); }
	public double getPovRadians() { return (Math.PI * this.getPovDegrees()) / 180.0d; }
	public double getStandardPovDegrees() { return 90.0d - this.getPovDegrees(); }
	public double getStandardPovRadians() { return (Math.PI * this.getStandardPovDegrees()) / 180.0d; }

	public double getLeftRightAxisValue() { return this.getRawAxis(0); }
	public double getRightLeftAxisValue() { return -this.getRawAxis(0); }
	public double getForwardBackAxisValue() { return this.getRawAxis(1); }
	public double getBackForwardAxisValue() { return -this.getRawAxis(1); }
	public double getRotateLeftRightAxisValue() { return this.getRawAxis(3); }
	public double getRotateRightLeftAxisValue() { return -this.getRawAxis(3); }
	public double getThrottleUpDownAxisValue() { return this.getRawAxis(2); }
	public double getThrottleDownUpAxisValue() { return 1.0 - this.getRawAxis(2); }

	public double getXAxisValue() { return this.getLeftRightAxisValue(); }
	public double getYAxisValue() { return this.getBackForwardAxisValue(); }
	public double getRAxisValue() { return this.getRotateLeftRightAxisValue(); }
	public double getTAxisValue() { return this.getThrottleDownUpAxisValue(); }

	public MadCatzV1Joystick(ControlPort controlPort) {
		super(controlPort);
	}

	public MadCatzV1Joystick(final int port) {
		super(port);
	}

}
