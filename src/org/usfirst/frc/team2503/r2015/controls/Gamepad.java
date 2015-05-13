package org.usfirst.frc.team2503.r2015.controls;

/**
 * This class extends the Joystick class, which is itself an extension of the
 * WPIlib Joystick class.  This mirrors the functionality of the Joystick class,
 * providing a splice-in endpoint to use ControlPort to designate the port of
 * the Joystick.
 *
 * @author Kristofer Rye
 */
public class Gamepad extends Joystick {

	public Gamepad(ControlPort controlPort) {
		super(controlPort);
	}

	public Gamepad(final int port) {
		super(port);
	}
	
}
