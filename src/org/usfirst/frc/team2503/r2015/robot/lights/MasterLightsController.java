package org.usfirst.frc.team2503.r2015.robot.lights;

import org.usfirst.frc.team2503.r2015.robot.lights.LightsController.LightsControllerStatus;

public class MasterLightsController {
	public enum MasterLightsControllerStatus {
		ALL_OFF(0),
		ALL_ON(1),
		
		ALL_SLOW_EPILEPSY(2),
		ALL_MEDIUM_EPILEPSY(3),
		ALL_FAST_EPILEPSY(4),
		
		ALL_SLOW_STROBE(5),
		ALL_MEDIUM_STROBE(6),
		ALL_FAST_STROBE(7),
		
		ALL_SLOW_CYCLE(8),
		ALL_MEDIUM_CYCLE(9),
		ALL_FAST_CYCLE(10),
		
		IDLE(100),
		
		DRIVING(200),
		WINCHING(201),
		DRIVING_AND_WINCHING(202),
		
		SEIZURE(300),
		FULL_SEIZURE(301),
		
		CAMERA_LIGHTS_ON(400),
		CAMERA_LIGHTS_OFF(401);
		
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
			
		case ALL_SLOW_EPILEPSY:
			upperLightsController.setStatus(LightsControllerStatus.SLOW_EPILEPSY);
			upperChannelLightsController.setStatus(LightsControllerStatus.SLOW_EPILEPSY);
			underGlowLightsController.setStatus(LightsControllerStatus.SLOW_EPILEPSY);
			break;
		case ALL_MEDIUM_EPILEPSY:
			upperLightsController.setStatus(LightsControllerStatus.MEDIUM_EPILEPSY);
			upperChannelLightsController.setStatus(LightsControllerStatus.MEDIUM_EPILEPSY);
			underGlowLightsController.setStatus(LightsControllerStatus.MEDIUM_EPILEPSY);
			break;
		case ALL_FAST_EPILEPSY:
			upperLightsController.setStatus(LightsControllerStatus.FAST_EPILEPSY);
			upperChannelLightsController.setStatus(LightsControllerStatus.FAST_EPILEPSY);
			underGlowLightsController.setStatus(LightsControllerStatus.FAST_EPILEPSY);
			break;
			
		case ALL_SLOW_STROBE:
			upperLightsController.setStatus(LightsControllerStatus.SLOW_STROBE);
			upperChannelLightsController.setStatus(LightsControllerStatus.SLOW_STROBE);
			underGlowLightsController.setStatus(LightsControllerStatus.SLOW_STROBE);
			break;
		case ALL_MEDIUM_STROBE:
			upperLightsController.setStatus(LightsControllerStatus.MEDIUM_STROBE);
			upperChannelLightsController.setStatus(LightsControllerStatus.MEDIUM_STROBE);
			underGlowLightsController.setStatus(LightsControllerStatus.MEDIUM_STROBE);
			break;
		case ALL_FAST_STROBE:
			upperLightsController.setStatus(LightsControllerStatus.FAST_STROBE);
			upperChannelLightsController.setStatus(LightsControllerStatus.FAST_STROBE);
			underGlowLightsController.setStatus(LightsControllerStatus.FAST_STROBE);
			break;
			
		case ALL_SLOW_CYCLE:
			upperLightsController.setStatus(LightsControllerStatus.SLOW_CYCLE);
			upperChannelLightsController.setStatus(LightsControllerStatus.SLOW_CYCLE);
			underGlowLightsController.setStatus(LightsControllerStatus.SLOW_CYCLE);
			break;
		case ALL_MEDIUM_CYCLE:
			upperLightsController.setStatus(LightsControllerStatus.MEDIUM_CYCLE);
			upperChannelLightsController.setStatus(LightsControllerStatus.MEDIUM_CYCLE);
			underGlowLightsController.setStatus(LightsControllerStatus.MEDIUM_CYCLE);
			break;
		case ALL_FAST_CYCLE:
			upperLightsController.setStatus(LightsControllerStatus.FAST_CYCLE);
			upperChannelLightsController.setStatus(LightsControllerStatus.FAST_CYCLE);
			underGlowLightsController.setStatus(LightsControllerStatus.FAST_CYCLE);
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
			
		case SEIZURE:
    		upperLightsController.setStatus(LightsControllerStatus.FAST_EPILEPSY);
    		upperChannelLightsController.setStatus(LightsControllerStatus.SLOW_EPILEPSY);
    		underGlowLightsController.setStatus(LightsControllerStatus.FAST_CYCLE);
			break;
			
		case FULL_SEIZURE:
    		upperLightsController.setStatus(LightsControllerStatus.FAST_EPILEPSY);
    		upperChannelLightsController.setStatus(LightsControllerStatus.FAST_EPILEPSY);
    		underGlowLightsController.setStatus(LightsControllerStatus.FAST_EPILEPSY);
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