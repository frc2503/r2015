package org.usfirst.frc.team2503.r2015.robot.lights;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Relay.Value;

public class LightsController implements Runnable {
	public enum LightsControllerStatus {
		OFF(0),
		ON(1),
		
		SLOW_EPILEPSY(100),
		MEDIUM_EPILEPSY(101),
		FAST_EPILEPSY(102),
		
		SLOW_STROBE(200),
		MEDIUM_STROBE(201),
		FAST_STROBE(202),
		
		SLOW_CYCLE(300),
		MEDIUM_CYCLE(301),
		FAST_CYCLE(302);
		
		private final int value;
		
		private LightsControllerStatus(final int value) {
			this.value = value;
		}
		
		public int getValue() {
			return this.value;
		}
	}
	
	private boolean isRunning = true;

	private Value currentValue;
	private double currentSetEnd;

	private Relay relay;
	private LightsControllerStatus status = LightsControllerStatus.OFF;
	
	public void onStart() {
		System.out.println("[LightsController] Starting!");
	}
	
	public void onStop() {
		System.out.println("[LightsController] Stopping!");
	}
	
	public void stop() {
		if(isRunning) {
			isRunning = false;
		} else {
			System.err.println("Tried to stop, but isRunning is non-truthy!");
		}
	}

	public void setStatus(LightsControllerStatus status) {
		this.status = status;
	}
	
	private void setLights(Value value) {
		currentValue = value;
		if(relay != null)
			relay.set(currentValue);
	}
	
	private void set(Value value) {
		setLights(value);
			
		currentSetEnd = Timer.getFPGATimestamp();
	}
		
	private void toggleLights() {
		if(currentValue == Relay.Value.kOn) {
			setLights(Relay.Value.kOff);
		} else {
			setLights(Relay.Value.kOn);
		}
	}
		
	public void toggle() {
		toggleLights();
	
		currentSetEnd = Timer.getFPGATimestamp();
	}
	
	public void toggle(Value value, double currentTime, double lastTime, double onThreshold, double offThreshold) {
		if(value == Relay.Value.kOn) {
			if(currentTime - lastTime >= onThreshold) {
				toggle();
			}
		} else if(value == Relay.Value.kOff) {
			if(currentTime - lastTime >= offThreshold) {
				toggle();
			}
		}
	}
		
	public void run() {
		/**
		 * When starting LightsController, run `onStart' to do any
		 * necessary setup tasks.
		 */
		onStart();
		
		/**
		 * This loop is called continuously until killed.
		 */
		while(isRunning) {
			switch(status) {
			case OFF:
				set(Relay.Value.kOff);
				break;
			case ON:
				set(Relay.Value.kOn);
				break;
				
			case SLOW_EPILEPSY:
				toggle(currentValue, Timer.getFPGATimestamp(), currentSetEnd, 0.25, 0.25);
				break;
			case MEDIUM_EPILEPSY:
				toggle(currentValue, Timer.getFPGATimestamp(), currentSetEnd, 0.125, 0.125);
				break;
			case FAST_EPILEPSY:
				toggle(currentValue, Timer.getFPGATimestamp(), currentSetEnd, 0.0125, 0.0125);
				break;
				
			case SLOW_STROBE:
				toggle(currentValue, Timer.getFPGATimestamp(), currentSetEnd, 0.0125, 1.0);
				break;
			case MEDIUM_STROBE:
				toggle(currentValue, Timer.getFPGATimestamp(), currentSetEnd, 0.0125, 0.25);
				break;
			case FAST_STROBE:
				toggle(currentValue, Timer.getFPGATimestamp(), currentSetEnd, 0.0125, 0.0625);
				break;
				
			case SLOW_CYCLE:
				toggle(currentValue, Timer.getFPGATimestamp(), currentSetEnd, 2.0, 2.0);
				break;
			case MEDIUM_CYCLE:
				toggle(currentValue, Timer.getFPGATimestamp(), currentSetEnd, 0.5, 0.25);
				break;
			case FAST_CYCLE:
				toggle(currentValue, Timer.getFPGATimestamp(), currentSetEnd, 0.25, 0.1);
				break;
				
			default:
				set(Relay.Value.kOn);
				break;
			}
			
			/* Microsleep to prevent race issues. */
			Timer.delay(0.0025);
		}
		
		/**
		 * When shutting down, do any necessary stuff.
		 */
		onStop();
	}
	
	public LightsController(Relay relay) {
		this.relay = relay;
	}
}
