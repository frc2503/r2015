package org.usfirst.frc.team2503.r2015.controls;

import java.util.HashMap;

import org.usfirst.frc.team2503.r2015.Constants;
import org.usfirst.frc.team2503.r2015.Maths;
import org.usfirst.frc.team2503.r2015.controls.gamepads.LogitechF310Gamepad;
import org.usfirst.frc.team2503.r2015.robot.ClampStatus;
import org.usfirst.frc.team2503.r2015.robot.lights.MasterLightsController.MasterLightsControllerStatus;

public class DriveBaseLogitechF310GamepadControlLayout implements DriveBaseControlLayout {

	public LogitechF310Gamepad gamepad;

	public static final String name = "DriveBaseLogitechF310GamepadControlLayout";
	
	public final String getName() {
		return name;
	}
	
	public HashMap<String, Object> get() {
		HashMap<String, Object> values = new HashMap<String, Object>();
		
		{
			boolean precision = gamepad.getLeftStickButton() && gamepad.getRightStickButton();
			double multiplier = (precision ? 0.4 : 1.0) * Constants.powerMultiplier;
	
			double leftAxisValue = Maths.signedSquareInput(gamepad.getLeftBackForwardAxisValue());
			double rightAxisValue = Maths.signedSquareInput(gamepad.getRightForwardBackAxisValue());
			
			double leftValue = ((leftAxisValue * Math.abs(leftAxisValue)) + leftAxisValue) / 2.0;
			double rightValue = ((rightAxisValue * Math.abs(rightAxisValue)) + rightAxisValue) / 2.0;
			
			leftValue *= multiplier;
			rightValue *= multiplier;
			
			values.put("left", leftValue);
			values.put("right", rightValue);
		}
		
		{
			double winchDesire = gamepad.getRightTriggerUnpressedPressedAxisValue() - gamepad.getLeftTriggerUnpressedPressedAxisValue();
		
			values.put("winch", winchDesire);
		}
		
		{
			if(gamepad.getLeftBumperButton()) {
				values.put("clamp", ClampStatus.CLOSE);
			} else if(gamepad.getRightBumperButton()) {
				values.put("clamp", ClampStatus.OPEN);
			}
		}
		
		{
			if(gamepad.getBButton()) {
				values.put("masterLightsControllerStatus", MasterLightsControllerStatus.ALL_FAST_STROBE);
			} else {
				values.put("masterLightsControllerStatus", null);
			}
		}

		return values;
	}
	
	public DriveBaseLogitechF310GamepadControlLayout(LogitechF310Gamepad gamepad) {
		this.gamepad = gamepad;
	}
	
}
