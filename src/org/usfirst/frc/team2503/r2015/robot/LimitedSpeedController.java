package org.usfirst.frc.team2503.r2015.robot;

import edu.wpi.first.wpilibj.SpeedController;

public interface LimitedSpeedController extends SpeedController {

	public void set(double targetOutput);
	
}
