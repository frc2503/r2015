package org.usfirst.frc.team2503.r2015.robot;

import org.usfirst.frc.team2503.r2015.Constants;
import org.usfirst.frc.team2503.r2015.controls.joysticks.MadCatzV1Joystick;
import org.usfirst.frc.team2503.r2015.robot.controllers.LightsController;
import org.usfirst.frc.team2503.r2015.robot.controllers.MasterLightsController;
import org.usfirst.frc.team2503.r2015.robot.controllers.MasterLightsController.MasterLightsControllerStatus;

public class DriveBaseMadCatzV1MadCatzV1Driver extends DriveBase implements Driver {
	public MadCatzV1Joystick leftJoystick;
	public MadCatzV1Joystick rightJoystick;

	public LightsController upperLightsController;
	public LightsController upperChannelLightsController;
	public LightsController underGlowLightsController;
	public LightsController cameraLightLightsController;
	public MasterLightsController masterLightsController;
	
	private double multiplier;

	private boolean precision = false;
	
	public void drive() {
		/**
		 * Driving code.
		 */
		{
			precision = leftJoystick.getGripButton();
			multiplier = (precision ? 0.4 : 1.0) * Constants.teleopPowerMultiplier;

			double leftAxisValue = leftJoystick.getBackForwardAxisValue();
			double rightAxisValue = rightJoystick.getForwardBackAxisValue();
			
			double leftValue = ((leftAxisValue * Math.abs(leftAxisValue)) + leftAxisValue) / 2.0;
			double rightValue = ((rightAxisValue * Math.abs(rightAxisValue)) + rightAxisValue) / 2.0;
			
			drive(multiplier * leftValue, multiplier * rightValue);
		}
		
		/**
		 * Winching code.
		 */
		{
			int winchPov = leftJoystick.getPov();
			
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
				double compressorThrottle = Math.abs((1.0 + leftJoystick.getThrottleDownUpAxisValue()) / 2.0);

				if(compressorThrottle > 0.5) {
					if(compressor.getClosedLoopControl()) {
						compressor.stop();
					}
				} else {
					if(!compressor.getClosedLoopControl()) {
						compressor.start();
					}
				}
			}
		}
		
		{
			/**
			 * Post-op lights code.
			 */
			
			if(leftJoystick.get2Button()) {
				masterLightsController.set(MasterLightsControllerStatus.ALL_FAST_STROBE);
			} else if(indicateDriving && !indicateWinching) {
				masterLightsController.set(MasterLightsControllerStatus.DRIVING);
			} else if(!indicateDriving && indicateWinching) {
				masterLightsController.set(MasterLightsControllerStatus.WINCHING);
			} else if(!indicateDriving && !indicateWinching) {
				masterLightsController.set(MasterLightsControllerStatus.IDLE);
			}
		}
	}
	
	public DriveBaseMadCatzV1MadCatzV1Driver(MadCatzV1Joystick left, MadCatzV1Joystick right) {
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
