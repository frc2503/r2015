package org.usfirst.frc.team2503.r2015.robot;

import org.usfirst.frc.team2503.r2015.Constants;

public class Winch {

	public LimitedTalon limitedTalon;
	
	public void set(double targetOutput) {
		limitedTalon.setTarget(targetOutput);
	}
	
	public Winch() {
		this.limitedTalon = new LimitedTalon(Constants.winchTalonPort, Constants.winchLowerLimitSwitchChannel, Constants.winchUpperLimitSwitchChannel);
	}
	
	public Winch(final int talonChannel) {
		this.limitedTalon = new LimitedTalon(talonChannel, Constants.winchLowerLimitSwitchChannel, Constants.winchUpperLimitSwitchChannel);
	}
	
	public Winch(final int talonChannel, final int lowerLimitSwitchChannel, final int upperLimitSwitchChannel) {
		this.limitedTalon = new LimitedTalon(talonChannel, lowerLimitSwitchChannel, upperLimitSwitchChannel);
	}
	
}
