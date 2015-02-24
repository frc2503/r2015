package org.usfirst.frc.team2503.robot.driver;

import org.usfirst.frc.team2503.Constants;
import org.usfirst.frc.team2503.joystick.MadCatzV1Joystick;
import org.usfirst.frc.team2503.robot.controllers.lights.LightsController;
import org.usfirst.frc.team2503.robot.controllers.lights.MasterLightsController;
import org.usfirst.frc.team2503.robot.controllers.lights.MasterLightsController.MasterLightsControllerStatus;
import org.usfirst.frc.team2503.robot.driveBase.ClampStatus;
import org.usfirst.frc.team2503.robot.driveBase.DriveBaseDriveBase;

public class DriveBaseMadCatzV1MadCatzV1DriveBaseDriver extends DriveBaseDriveBase implements Driver {
	public MadCatzV1Joystick leftJoystick;
	public MadCatzV1Joystick rightJoystick;

	public LightsController upperLightsController;
	public LightsController upperChannelLightsController;
	public LightsController underGlowLightsController;
	public LightsController cameraLightLightsController;
	public MasterLightsController masterLightsController;
	
	private double multiplier;

	private boolean precision = false;
	private boolean compressorControlSwitch = false;
	private boolean compressorControlSwitchPrevious = false;
	
	public void drive() {
		/**
		 * Driving code.
		 */
		{
			precision = leftJoystick.getGripButton();
			multiplier = (precision ? 0.4 : 1.0);

			drive(multiplier * leftJoystick.getBackForwardAxisValue(), multiplier * rightJoystick.getForwardBackAxisValue());
		}
		
		/**
		 * Winching code.
		 */
		{
			int winchPov = rightJoystick.getPov();
			
			double winchDesire = (winchPov >= 0 ? Math.sin(((winchPov + 90) * Math.PI) / 180.0) : 0.0);
			double winchThrottle = Math.abs((1.0 + rightJoystick.getThrottleUpDownAxisValue()) / 2.0);
	
			if(winchDesire > 0.0) {
				if(winchUpperLimitSwitch.get()) {
					winch(winchDesire * winchThrottle);
				} else {
					winch(0.0);
				}
			} else if(winchDesire < 0.0) {
				if(winchLowerLimitSwitch.get()) {
					winch(winchDesire * winchThrottle);
				} else {
					winch(0.0);
				}
			} else {
				winch(0.0);
			}
		}
		
		/**
		 * Clamping code.
		 */
		{
			if(rightJoystick.get2Button()){
				clamp.set(ClampStatus.CLOSE);
			} else if(rightJoystick.getStickTriggerButton()) {
				clamp.set(ClampStatus.OPEN);
			}
		}
		
		{
			/**
			 * Compressor code.
			 */
			if(Constants.PERMISSION_COMPRESSOR_CONTROL) {
				compressorControlSwitch = leftJoystick.get5Button();
				
				if(compressorControlSwitch != compressorControlSwitchPrevious) {
					if(compressorControlSwitch) {
						if(compressor.getClosedLoopControl()) {
							compressor.stop();
						} else {
							compressor.start();
						}
					}
					
					compressorControlSwitchPrevious = compressorControlSwitch;
				}
			}
		}
		
		{
			/**
			 * Post-op lights code.
			 */
			
			if(leftJoystick.get2Button()) {
				masterLightsController.set(MasterLightsControllerStatus.OVERRIDE_SEIZURE);
			} else if(indicateDriving && !indicateWinching) {
				masterLightsController.set(MasterLightsControllerStatus.DRIVING);
			} else if(!indicateDriving && indicateWinching) {
				masterLightsController.set(MasterLightsControllerStatus.WINCHING);
			} else if(!indicateDriving && !indicateWinching) {
				masterLightsController.set(MasterLightsControllerStatus.IDLE);
			}
		}
	}
	
	public DriveBaseMadCatzV1MadCatzV1DriveBaseDriver(MadCatzV1Joystick left, MadCatzV1Joystick right) {
		leftJoystick = left;
		rightJoystick = right;
		
		/**
		 * Lights controllers
		 */
		{
			upperLightsController = new LightsController(upperLights);
			new Thread(upperLightsController).start();

			upperChannelLightsController = new LightsController(upperChannelLights);
			new Thread(upperChannelLightsController).start();
		
			underGlowLightsController = new LightsController(underGlowLights);
			new Thread(underGlowLightsController).start();
			
			cameraLightLightsController = new LightsController(cameraLightLights);
			new Thread(cameraLightLightsController).start();
			
			masterLightsController = new MasterLightsController(upperLightsController, upperChannelLightsController, underGlowLightsController, cameraLightLightsController);
		}
		
		/**
		 * Set up compressor.
		 */
		compressor.setClosedLoopControl(true);	
		compressor.start();
	}
}
