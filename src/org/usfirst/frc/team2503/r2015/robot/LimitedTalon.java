package org.usfirst.frc.team2503.r2015.robot;

import org.usfirst.frc.team2503.r2015.robot.LimitSwitch;
import org.usfirst.frc.team2503.r2015.robot.LimitSwitch.LimitSwitchType;

import edu.wpi.first.wpilibj.Talon;

public class LimitedTalon extends Talon implements LimitedSpeedController {

	public LimitSwitch lowerLimitSwitch;
	public LimitSwitch upperLimitSwitch;
	
	public void setTarget(double targetOutput) {
		if(targetOutput < 0.0) {
			if(lowerLimitSwitch.isActivated()) {
				set(0.0);
			} else if(!lowerLimitSwitch.isActivated()) {
				set(targetOutput);
			}
		} else if(targetOutput > 0.0) {
			if(upperLimitSwitch.isActivated()) {
				set(0.0);
			} else if(!upperLimitSwitch.isActivated()) {
				set(targetOutput);
			}
		} else {
			set(0.0);
		}
	}
	
	public LimitedTalon(final int channel, final int lowerLimitSwitchChannel, final LimitSwitchType lowerLimitSwitchType, final int upperLimitSwitchChannel, final LimitSwitchType upperLimitSwitchType) {
		super(channel);
		
		this.lowerLimitSwitch = new LimitSwitch(lowerLimitSwitchChannel, lowerLimitSwitchType);
		this.upperLimitSwitch = new LimitSwitch(upperLimitSwitchChannel, lowerLimitSwitchType);
	}
	
	public LimitedTalon(final int channel, final int lowerLimitSwitchChannel, final int upperLimitSwitchChannel) {
		super(channel);
		
		this.lowerLimitSwitch = new LimitSwitch(lowerLimitSwitchChannel);
		this.upperLimitSwitch = new LimitSwitch(upperLimitSwitchChannel);
	}

}
