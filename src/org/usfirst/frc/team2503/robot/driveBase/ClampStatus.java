package org.usfirst.frc.team2503.robot.driveBase;

public enum ClampStatus {
	INACTIVE(0),
	OPEN(0),
	CLOSE(1);
	
	private final int value;
		
	private ClampStatus(int value) {
		this.value = value;
	}
		
	public final int getValue() {
		return value;
	}
}
