package org.usfirst.frc.team2503.robot.lights;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Relay.Value;

public class UpperLightsController implements LightsController {
	public enum UpperLightsControllerStatus {
		OFF(0),
		ON(1),
		
		MILD_EPILEPSY(2),
		MODERATE_EPILEPSY(3),
		SEVERE_EPILEPSY(4),
		
		SLOW_CYCLE(5),
		MEDIUM_CYCLE(6),
		FAST_CYCLE(7);
		
		private final int value;
		
		private UpperLightsControllerStatus(final int value) {
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
	private UpperLightsControllerStatus status;
	
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

	public void setStatus(UpperLightsControllerStatus status) {
		this.status = status;
	}
	
	private void setLights(Value value) {
		currentValue = value;
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
		
	private void toggle() {
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
				
			case MILD_EPILEPSY:
			case MODERATE_EPILEPSY:
				toggle(currentValue, Timer.getFPGATimestamp(), currentSetEnd, 0.1, 0.1);
				break;
			case SEVERE_EPILEPSY:
				toggle(currentValue, Timer.getFPGATimestamp(), currentSetEnd, 0.0125, 0.0125);
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
			Timer.delay(0.005);
		}
		
		/**
		 * When shutting down, do any necessary stuff.
		 */
		onStop();
	}
	
	public UpperLightsController(Relay relay) {
		this.relay = relay;
	}
}