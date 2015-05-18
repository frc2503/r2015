package org.usfirst.frc.team2503.r2015.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;

import org.usfirst.frc.team2503.r2015.Constants;
import org.usfirst.frc.team2503.r2015.robot.lights.LightsController;
import org.usfirst.frc.team2503.r2015.robot.lights.MasterLightsController;
import org.usfirst.frc.team2503.r2015.robot.lights.MasterLightsController.MasterLightsControllerStatus;

public class DriveBase {
	public Talon left;
	public Talon right;
	public Winch winch;
	
	public Relay upperLights;
	public Relay upperChannelLights;
	public Relay underGlowLights;
	public Relay cameraLightLights;
	
	public LightsController upperLightsController;
	public LightsController upperChannelLightsController;
	public LightsController underGlowLightsController;
	public LightsController cameraLightLightsController;
	public MasterLightsController masterLightsController;
	
	public DigitalInput winchLowerLimitSwitch;
	public DigitalInput winchUpperLimitSwitch;
	
	public Compressor compressor;
	public Clamp clamp;
	
	public void setLeftPort(final int port) { left = new Talon(port); }
	public void setRightPort(final int port) { right = new Talon(port); }
	public void setWinchPort(final int port) { winch = new Winch(port); }
	public void setUpperLightsPort(final int port) { upperLights = new Relay(port, Relay.Direction.kReverse); }
	public void setUpperChannelLightsPort(final int port) { upperChannelLights = new Relay(port, Relay.Direction.kReverse); }
	public void setUnderGlowLightsPort(final int port) { underGlowLights = new Relay(port, Relay.Direction.kReverse); }
	public void setCameraLightLightsPort(final int port) { cameraLightLights = new Relay(port, Relay.Direction.kReverse); }
	public void setupWinchLowerLimitSwitch(final int channel) { winchLowerLimitSwitch = new DigitalInput(channel); }
	public void setupWinchUpperLimitSwitch(final int channel) { winchUpperLimitSwitch = new DigitalInput(channel); }
	public void setCompressorPort(final int port) { compressor = new Compressor(port); }
	
	public boolean winching = false;
	public boolean driving = false;

	public void drive(double leftValue, double rightValue) {
		double totalValue = Math.abs(leftValue) + Math.abs(rightValue);
		
		if(totalValue >= Constants.inputIndicationNullZone || totalValue <= -Constants.inputIndicationNullZone) {
			driving = true;
		} else {
			driving = false;
		}
		
		left.set(leftValue * Constants.masterPowerMultiplier);
		right.set(rightValue * Constants.masterPowerMultiplier);
	}

	public void winch(double winchValue) {
		if(winchValue >= Constants.inputIndicationNullZone || winchValue <= -Constants.inputIndicationNullZone) {
			winching = true;
		} else {
			winching = false;
		}
		
		winch.set(winchValue);
	}
	
	public void updateLights(MasterLightsControllerStatus overrideStatus) {
		if(overrideStatus != null) {
			masterLightsController.set(overrideStatus);
		} else {
			if(driving && !winching) {
				masterLightsController.set(MasterLightsControllerStatus.DRIVING);
			} else if(!driving && winching) {
				masterLightsController.set(MasterLightsControllerStatus.WINCHING);
			} else if(!driving && !winching) {
				masterLightsController.set(MasterLightsControllerStatus.IDLE);
			}
		}
	}
	
	public DriveBase() {
		setLeftPort(Constants.leftTalonPort);
		setRightPort(Constants.rightTalonPort);
		setWinchPort(Constants.winchTalonPort);
		
		setUpperLightsPort(Constants.upperLightsRelayPort);
		setUpperChannelLightsPort(Constants.upperChannelLightsRelayPort);
		setUnderGlowLightsPort(Constants.underGlowLightsRelayPort);
		setCameraLightLightsPort(Constants.cameraLightLightsRelayPort);
		
		setCompressorPort(Constants.compressorPort);
		
		/**
		 * Set up the Clamp.
		 */
		clamp = new Clamp();
		
		/**
		 * Set up compressor.
		 */
		compressor.setClosedLoopControl(true);	
		compressor.start();
		
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
	}
	
}
