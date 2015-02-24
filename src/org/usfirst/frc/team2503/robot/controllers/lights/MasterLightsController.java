package org.usfirst.frc.team2503.robot.controllers.lights;

import org.usfirst.frc.team2503.robot.controllers.lights.LightsController.LightsControllerStatus;

public class MasterLightsController {
	public enum MasterLightsControllerStatus {
		ALL_OFF(0),
		ALL_ON(1),
		
		IDLE(2),
		
		DRIVING(3),
		WINCHING(4),
		DRIVING_AND_WINCHING(5),
		
		OVERRIDE_SEIZURE(6),
		
		CAMERA_LIGHTS_ON(7),
		CAMERA_LIGHTS_OFF(8);
		
		private final int value;
		
		private MasterLightsControllerStatus(final int value) {
			this.value = value;
		}
		
		public int getValue() {
			return this.value;
		}
	}
	
	private MasterLightsControllerStatus status = MasterLightsControllerStatus.ALL_ON;
	
	private LightsController upperLightsController;
	private LightsController upperChannelLightsController;
	private LightsController underGlowLightsController;
	public LightsController cameraLightLightsController;
			
	private void setStatus(MasterLightsControllerStatus status) {
		this.status = status;
	}
	
	public void set(MasterLightsControllerStatus status) {
		setStatus(status);
		
		switch(this.status) {
		case ALL_OFF:
			upperLightsController.setStatus(LightsControllerStatus.OFF);
			upperChannelLightsController.setStatus(LightsControllerStatus.OFF);
			underGlowLightsController.setStatus(LightsControllerStatus.OFF);
			break;
		case ALL_ON:
			upperLightsController.setStatus(LightsControllerStatus.ON);
			upperChannelLightsController.setStatus(LightsControllerStatus.ON);
			underGlowLightsController.setStatus(LightsControllerStatus.ON);
			break;
			
		case IDLE:
			upperLightsController.setStatus(LightsControllerStatus.SLOW_CYCLE);
			upperChannelLightsController.setStatus(LightsControllerStatus.SLOW_CYCLE);
			underGlowLightsController.setStatus(LightsControllerStatus.ON);
			break;
			
		case DRIVING:
			upperLightsController.setStatus(LightsControllerStatus.FAST_CYCLE);
			upperChannelLightsController.setStatus(LightsControllerStatus.FAST_CYCLE);
			underGlowLightsController.setStatus(LightsControllerStatus.SLOW_CYCLE);
			break;
			
		case WINCHING:
			upperLightsController.setStatus(LightsControllerStatus.FAST_CYCLE);
			upperChannelLightsController.setStatus(LightsControllerStatus.FAST_CYCLE);
			underGlowLightsController.setStatus(LightsControllerStatus.ON);
			break;
					
		case DRIVING_AND_WINCHING:
			upperLightsController.setStatus(LightsControllerStatus.FAST_CYCLE);
			upperChannelLightsController.setStatus(LightsControllerStatus.FAST_CYCLE);
			underGlowLightsController.setStatus(LightsControllerStatus.SLOW_CYCLE);
			break;
			
		case OVERRIDE_SEIZURE:
    		upperLightsController.setStatus(LightsControllerStatus.SEVERE_EPILEPSY);
    		upperChannelLightsController.setStatus(LightsControllerStatus.MILD_EPILEPSY);
    		underGlowLightsController.setStatus(LightsControllerStatus.FAST_CYCLE);
			break;
			
		case CAMERA_LIGHTS_ON:
			cameraLightLightsController.setStatus(LightsControllerStatus.ON);
			break;
			
		case CAMERA_LIGHTS_OFF:
			cameraLightLightsController.setStatus(LightsControllerStatus.OFF);
			break;
			
		default:
    		upperLightsController.setStatus(LightsControllerStatus.SLOW_CYCLE);
    		upperChannelLightsController.setStatus(LightsControllerStatus.SLOW_CYCLE);
    		underGlowLightsController.setStatus(LightsControllerStatus.FAST_CYCLE);
    		break;
		}
	}
			
	public MasterLightsController(LightsController upperLightsController, LightsController upperChannelLightsController, LightsController underGlowLightsController, LightsController cameraLightLightsController) {
		this.upperLightsController = upperLightsController;
		this.upperChannelLightsController = upperChannelLightsController;
		this.underGlowLightsController = underGlowLightsController;
		this.cameraLightLightsController = cameraLightLightsController;
	}
}