package org.usfirst.frc.team2503.robot.driver;

import org.usfirst.frc.team2503.Constants;
import org.usfirst.frc.team2503.joystick.MadCatzV1Joystick;
import org.usfirst.frc.team2503.robot.driveBase.ClampStatus;
import org.usfirst.frc.team2503.robot.driveBase.DriveBaseDriveBase;
import org.usfirst.frc.team2503.robot.lights.LightsController;
import org.usfirst.frc.team2503.robot.lights.UnderGlowLightsController;
import org.usfirst.frc.team2503.robot.lights.UpperLightsController;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Timer;

public class DriveBaseMadCatzV1MadCatzV1DriveBaseDriver extends DriveBaseDriveBase implements Driver {
	private MadCatzV1Joystick leftJoystick;
	private MadCatzV1Joystick rightJoystick;
	private UpperLightsController upperLightsController;
	private UnderGlowLightsController underGlowLightsController;
	
	private double multiplier;
	
	private int winchPov;
	private double winchDesire = 0.0;
	private double winchThrottle = 0.0;

	private boolean precision = false;
	private boolean clampClose;
	private boolean clampOpen;
	private boolean compressorControlSwitch = false;
	private boolean compressorControlSwitchPrevious = false;

	public void drive() {
		/**
		 * Driving code.
		 */
		{
			precision = leftJoystick.getGripButton() | rightJoystick.getGripButton();
			multiplier = (precision ? 0.4 : 1.0);

			drive(multiplier * leftJoystick.getBackForwardAxisValue(), multiplier * rightJoystick.getForwardBackAxisValue());
		}
		
		/**
		 * Winching code.
		 */
		{
			winchPov = rightJoystick.getPov();	
			winchDesire = (winchPov > 0 ? Math.sin(((winchPov + 90) * Math.PI) / 180.0) : 0.0);
			winchThrottle = Math.abs((1.0 + rightJoystick.getThrottleUpDownAxisValue()) / 2.0);		
	
			if(winchDesire > 0.0) {
				if(winchUpperLimitSwitch.get()) {
					winch(winchDesire * winchThrottle);
				} else {
					winch(0.0);
				}
			} else if(winchDesire < 0.0) {
				if(winchLowerLimitSwitch.get()) {
					winch(winchDesire * winchThrottle);
				}
			} else {
				winch(0.0);
			}
		}
		
		/**
		 * Clamping logic.
		 */
		{
			clampClose = leftJoystick.get4Button() | rightJoystick.get3Button();
			clampOpen = leftJoystick.get3Button() | rightJoystick.get4Button();
			
			if(clampClose) {
				clamp.set(ClampStatus.CLOSE);
			} else if(clampOpen) {
				clamp.set(ClampStatus.OPEN);
			}
		}
		
		/**
		 * Compressor logic
		 */
		if(Constants.PERMISSION_PNEUMATICS_CONTROL) {
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
	
	public DriveBaseMadCatzV1MadCatzV1DriveBaseDriver(MadCatzV1Joystick left, MadCatzV1Joystick right) {
		leftJoystick = left;
		rightJoystick = right;
		
		/**
		 * Lights controllers
		 */
		{
			upperLightsController = new UpperLightsController(upperLights);
			new Thread(upperLightsController).start();
		
			underGlowLightsController = new UnderGlowLightsController(underGlowLights);
			new Thread(underGlowLightsController).start();
		}
		
		/**
		 * Set up compressor.
		 */
		compressor.setClosedLoopControl(true);	
		compressor.start();
	}
}
