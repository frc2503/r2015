package org.usfirst.frc.team2503.r2015.robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class LimitSwitch extends DigitalInput {
	
	public enum LimitSwitchType {
		
		NORMALLY_OPEN(false),
		NORMALLY_CLOSED(true);
		
		private boolean value;
		
		public boolean getValue() {
			return this.value;
		}
		
		private LimitSwitchType(boolean value) {
			this.value = value;
		}
		
	}
	
	public LimitSwitchType type;
	
	public boolean get() {
		boolean input = super.get();
		
		/**
		 * If the LimitSwitch is normally open circuit, then input will be
		 * only true when the thing is pressed; otherwise (when the LimitSwitch
		 * is normally closed), then negate to get the appropriate result.
		 */
		return (type == LimitSwitchType.NORMALLY_OPEN ? input : !input);
	}
	
	public LimitSwitch(final int channel, LimitSwitchType type) {
		super(channel);
		
		this.type = type;
	}
	
	public LimitSwitch(final int channel) {
		super(channel);
		
		this.type = LimitSwitchType.NORMALLY_OPEN;
	}
}
