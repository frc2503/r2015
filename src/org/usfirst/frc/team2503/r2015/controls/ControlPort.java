package org.usfirst.frc.team2503.r2015.controls;

/**
 * An enum designed to remove ambiguity as to where a control is plugged in.
 * 
 * It appears that there are 6 slots for Joysticks, and they are generally added
 * in the order that they are discovered by the Driver Station's host operating
 * system.
 * 
 * This enum is not intelligent; it is simply a wrapper for numbers.
 * 
 * @author Kristofer Rye
 */
public enum ControlPort {
	
	LEFT_PRIMARY(0),
	RIGHT_PRIMARY(1),
	
	LEFT_SECONDARY(2),
	RIGHT_SECONDARY(3),

	LEFT_TERTIARY(4),
	RIGHT_TERTIARY(5);
	
	private final int value;
	
	private ControlPort(int value) {
		this.value = value;
	}
	
	public final int getValue() {
		return value;
	}
	
}
