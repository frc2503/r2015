package org.usfirst.frc.team2503.r2015.controls;

import java.util.HashMap;

import org.usfirst.frc.team2503.r2015.Constants;
import org.usfirst.frc.team2503.r2015.controls.joysticks.MadCatzV1Joystick;
import org.usfirst.frc.team2503.r2015.robot.ClampStatus;
import org.usfirst.frc.team2503.r2015.robot.lights.MasterLightsController.MasterLightsControllerStatus;

public class DriveBaseMadCatzV1MadCatzV1ControlLayout implements DriveBaseControlLayout {

	public MadCatzV1Joystick left;
	public MadCatzV1Joystick right;

	public static final String name = "DriveBaseMadCatzV1MadCatzV1ControlLayout";
	
	public final String getName() {
		return name;
	}
	
	public HashMap<String, Object> get() {
		HashMap<String, Object> values = new HashMap<String, Object>();
		
		{
			boolean precision = left.getGripButton();
			double multiplier = (precision ? 0.4 : 1.0) * Constants.powerMultiplier;
	
			double leftAxisValue = left.getBackForwardAxisValue();
			double rightAxisValue = right.getForwardBackAxisValue();
			
			double leftValue = ((leftAxisValue * Math.abs(leftAxisValue)) + leftAxisValue) / 2.0;
			double rightValue = ((rightAxisValue * Math.abs(rightAxisValue)) + rightAxisValue) / 2.0;
			
			leftValue *= multiplier;
			rightValue *= multiplier;
			
			values.put("left", leftValue);
			values.put("right", rightValue);
		}
		
		{
			int winchPov = left.getPov();
			
			double winchDesire = (winchPov >= 0 ? Math.sin(((winchPov + 90) * Math.PI) / 180.0) : 0.0);
		
			values.put("winch", winchDesire);
		}
		
		{
			if(right.getStickTriggerButton()){
				values.put("clamp", ClampStatus.CLOSE);
			} else if(right.get2Button()) {
				values.put("clamp", ClampStatus.OPEN);
			}
		}
		
		{
			if(left.get2Button()) {
				values.put("masterLightsControllerStatus", MasterLightsControllerStatus.ALL_FAST_STROBE);
			} else {
				values.put("masterLightsControllerStatus", null);
			}
		}

		return values;
	}
	
	public DriveBaseMadCatzV1MadCatzV1ControlLayout(MadCatzV1Joystick left, MadCatzV1Joystick right) {
		this.left = left;
		this.right = right;
	}
	
}
