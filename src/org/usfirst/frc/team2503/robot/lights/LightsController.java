package org.usfirst.frc.team2503.robot.lights;

import org.usfirst.frc.team2503.Constants;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Relay.Value;

public interface LightsController extends Runnable {
	public void onStart();
	public void onStop();
	public void stop();
}
