package org.usfirst.frc.team2503.robot.controllers.lights;

public interface RunnableLightsController extends Runnable {
	public void onStart();
	public void onStop();
	public void stop();
}
