package org.usfirst.frc.team2503.robot.driver;

import org.usfirst.frc.team2503.Constants;
import org.usfirst.frc.team2503.joystick.MadCatzV1Joystick;
import org.usfirst.frc.team2503.robot.driveBase.DriveBaseDriveBase;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Timer;

public class DriveBaseMadCatzV1MadCatzV1DriveBaseDriver extends DriveBaseDriveBase implements Driver {
	private MadCatzV1Joystick leftJoystick;
	private MadCatzV1Joystick rightJoystick;
	private LightsController lightsController;

	private class LightsController implements Runnable {
		private boolean isRunning = true;
		private Value currentValue;
		private double currentSetEnd;
		private DriverStation driverStation = DriverStation.getInstance();
		
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

		private void setLights(Value value) {
			currentValue = value;
			lights.set(currentValue);
		}
		
		private void toggleLights() {
			if(currentValue == Relay.Value.kOn) {
				setLights(Relay.Value.kOff);
			} else {
				setLights(Relay.Value.kOn);
			}
		}
		
		private void set(Value value) {
			setLights(value);
			
			currentSetEnd = Timer.getFPGATimestamp();
		}
		
		private void toggle() {
			toggleLights();
			
			currentSetEnd = Timer.getFPGATimestamp();
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
				if(driverStation.isDisabled() || driverStation.isBrownedOut()) {
					set(Relay.Value.kOff);
				} else if(driverStation.isAutonomous()) {
					set(Relay.Value.kOn);
				} else if(driverStation.isTest()) {
					if(leftJoystick.get2Button()) {
						set(Relay.Value.kOn);
					} else {
						set(Relay.Value.kOff);
					}
				} else if(driverStation.isOperatorControl()) {
					if(leftJoystick.get2Button()) {
						if(Constants.epilepsyMode) {
							toggle(currentValue, Timer.getFPGATimestamp(), currentSetEnd, 0.0125, 0.0125);
						} else {
							set(Relay.Value.kOn);
						}
					} else {
						if(indicateWinching) {
							toggle(currentValue, Timer.getFPGATimestamp(), currentSetEnd, 0.25, 0.1);
						} else if(indicateSlipping && !indicateDriving) {
							toggle(currentValue, Timer.getFPGATimestamp(), currentSetEnd, 0.25, 0.25);
						} else if(indicateDriving && !indicateSlipping) {
							toggle(currentValue, Timer.getFPGATimestamp(), currentSetEnd, 1.0, 0.25);
						} else if(indicateDriving && indicateSlipping) {
							toggle(currentValue, Timer.getFPGATimestamp(), currentSetEnd, 1.0, 0.25);
						} else {
							toggle(currentValue, Timer.getFPGATimestamp(), currentSetEnd, 2.0, 2.0);
						}
					}
				}
				
				/* Microsleep to prevent race issues. */
				Timer.delay(0.005);
			}
			
			/**
			 * When shutting down, do any necessary stuff.
			 */
			onStop();
		}
	}
	
	private double multiplier;
	
	public void drive() {
		if(leftJoystick.getGripButton() || rightJoystick.getGripButton()) {
			multiplier = 0.4;
		} else {
			multiplier = 1.0;
		}
		
		if(leftJoystick.getStickTriggerButton()) {
			drive(multiplier * leftJoystick.getBackForwardAxisValue(), multiplier * rightJoystick.getForwardBackAxisValue(), multiplier * leftJoystick.getLeftRightAxisValue());
		} else {
			drive(multiplier * leftJoystick.getBackForwardAxisValue(), multiplier * rightJoystick.getForwardBackAxisValue());
		}
		
		int pov = rightJoystick.getPov();
		
		if((pov > 270 && pov <= 360) || (pov >= 0 && pov < 90)) {
			if(winchUpperLimitSwitch.get()) {
				winch(1.0);
			}
		} else if((pov > 90 && pov < 270)) {
			if(winchLowerLimitSwitch.get()) {
				winch(-1.0);
			}
		} else {
			winch(0.0);
		}
	}
	
	public DriveBaseMadCatzV1MadCatzV1DriveBaseDriver(MadCatzV1Joystick left, MadCatzV1Joystick right) {
		leftJoystick = left;
		rightJoystick = right;
		
		lightsController = new LightsController();
		new Thread(lightsController).start();
	}
}
