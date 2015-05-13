package org.usfirst.frc.team2503.r2015.controls;

/**
 * A class to wrap the WPIlib Joystick class, providing a splice-in endpoint to
 * use ControlPort to designate the port of the Joystick.
 *
 * @author Kristofer Rye
 */
public class Joystick extends edu.wpi.first.wpilibj.Joystick {

	public Joystick(ControlPort controlPort) {
		super(controlPort.getValue());
	}

	public Joystick(final int port) {
		super(port);
	}

}
