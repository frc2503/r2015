package org.usfirst.frc.team2503.r2015.controls;

import org.usfirst.frc.team2503.r2015.Constants;
import org.usfirst.frc.team2503.r2015.controls.joysticks.MadCatzV1Joystick;

public class DriveBaseMadCatzV1MadCatzV1ControlLayout implements DriveBaseControlLayout {

	public MadCatzV1Joystick left;
	public MadCatzV1Joystick right;

	public double[] get() {
		double[] values = new double[3];
		
		{
			boolean precision = left.getGripButton();
			double multiplier = (precision ? 0.4 : 1.0) * Constants.powerMultiplier;
	
			double leftAxisValue = left.getYAxisValue();
			double rightAxisValue = right.getYAxisValue();
			
			double leftValue = ((leftAxisValue * Math.abs(leftAxisValue)) + leftAxisValue) / 2.0;
			double rightValue = ((rightAxisValue * Math.abs(rightAxisValue)) + rightAxisValue) / 2.0;
			
			values[ValueRegisters.LEFT.toInt()] = multiplier * leftValue;
			values[ValueRegisters.RIGHT.toInt()] = multiplier * rightValue;
		}
		
		{
			int winchPov = left.getPov();
			
			double winchDesire = (winchPov >= 0 ? Math.sin(((winchPov + 90) * Math.PI) / 180.0) : 0.0);
			double winchThrottle = Math.abs((1.0 + right.getTAxisValue()) / 2.0);
			
			double winchTargetOutput = winchDesire * winchThrottle;
		
			values[ValueRegisters.WINCH.toInt()] = winchTargetOutput;
		}

		return values;
	}
	
	public DriveBaseMadCatzV1MadCatzV1ControlLayout(MadCatzV1Joystick left, MadCatzV1Joystick right) {
		this.left = left;
		this.right = right;
	}
	
}
