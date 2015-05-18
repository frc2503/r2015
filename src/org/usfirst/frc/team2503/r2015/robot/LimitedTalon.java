package org.usfirst.frc.team2503.r2015.robot;

import org.usfirst.frc.team2503.r2015.robot.LimitSwitch;
import org.usfirst.frc.team2503.r2015.robot.LimitSwitch.LimitSwitchType;

import edu.wpi.first.wpilibj.Talon;

public class LimitedTalon extends Talon implements LimitedSpeedController {

	public LimitSwitch lowerLimitSwitch;
	public LimitSwitch upperLimitSwitch;
	
	public void set(double targetOutput) {
		if(targetOutput < 0.0) {
			if(lowerLimitSwitch.get()) {
				set(0.0);
			} else if(!lowerLimitSwitch.get()) {
				set(targetOutput);
			}
		} else if(targetOutput > 1.0) {
			if(upperLimitSwitch.get()) {
				set(0.0);
			} else if(!upperLimitSwitch.get()) {
				set(targetOutput);
			}
		}
	}
	
	public LimitedTalon(final int channel, final int lowerLimitSwitchChannel, final LimitSwitchType lowerLimitSwitchType, final int upperLimitSwitchChannel, final LimitSwitchType upperLimitSwitchType) {
		super(channel);
		
		this.lowerLimitSwitch = new LimitSwitch(lowerLimitSwitchChannel, lowerLimitSwitchType);
		this.upperLimitSwitch = new LimitSwitch(upperLimitSwitchChannel, lowerLimitSwitchType);
	}

}
