package org.usfirst.frc.team2503.joystick;

import edu.wpi.first.wpilibj.Joystick;

public class WarriorJoystick extends Joystick {	
	public enum WarriorJoystickSide {
		LEFT_PRIMARY(0), RIGHT_PRIMARY(1),
		LEFT_SECONDARY(2), RIGHT_SECONDARY(3),
		LEFT_TERTIARY(4), RIGHT_TERTIARY(5);

		private final int value;
		
		private WarriorJoystickSide(int value) {
			this.value = value;
		}
		
		public final int getValue() {
			return value;
		}
	}
	
	public WarriorJoystick(final WarriorJoystickSide side) {
		super(side.getValue());
	}
	
	public WarriorJoystick(final int port) {
		super(port);
	}
}
