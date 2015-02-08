package org.usfirst.frc.team2503.robot.driveBase;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;

import org.usfirst.frc.team2503.Constants;

public class DriveBaseDriveBase extends DriveBase {
	public Talon left;
	public Talon right;
	public Talon slip;
	public Talon winch;
	
	public Relay lights;
	
	public DigitalInput winchLowerLimitSwitch;
	public DigitalInput winchUpperLimitSwitch;
	
	public void setLeftPort(final int port) { left = new Talon(port); }
	public void setRightPort(final int port) { right = new Talon(port); }
	public void setSlipPort(final int port) { slip = new Talon(port); }
	public void setWinchPort(final int port) { winch = new Talon(port); }
	public void setLightsPort(final int port) { lights = new Relay(port, Relay.Direction.kReverse); }
	public void setupWinchLowerLimitSwitch(final int channel) { winchLowerLimitSwitch = new DigitalInput(channel); }
	public void setupWinchUpperLimitSwitch(final int channel) { winchUpperLimitSwitch = new DigitalInput(channel); }
	
	public boolean indicateWinching = false;
	public boolean indicateDriving = false;
	public boolean indicateSlipping = false;
	
	public void drive(double leftValue, double rightValue, double slipValue) {
		double totalValue = Math.abs(leftValue) + Math.abs(rightValue);
		
		if(totalValue >= Constants.inputIndicationNullZone || totalValue <= -Constants.inputIndicationNullZone) {
			indicateDriving = true;
		} else {
			indicateDriving = false;
		}
		
		if(slipValue >= Constants.inputIndicationNullZone || slipValue <= -Constants.inputIndicationNullZone) {
			indicateSlipping = true;
		} else {
			indicateSlipping = false;
		}
		
		left.set(leftValue * Constants.masterPowerMultiplier);
		right.set(rightValue * Constants.masterPowerMultiplier);
		slip.set(slipValue);
	}
	
	public void drive(double leftValue, double rightValue) {
		indicateSlipping = false;
		
		double totalValue = Math.abs(leftValue) + Math.abs(rightValue);
		
		if(totalValue >= Constants.inputIndicationNullZone || totalValue <= -Constants.inputIndicationNullZone) {
			indicateDriving = true;
		} else {
			indicateDriving = false;
		}
		
		left.set(leftValue * Constants.masterPowerMultiplier);
		right.set(rightValue * Constants.masterPowerMultiplier);
		
		slip.set(0.0);
	}
	
	public void drive(double slipValue) {
		indicateDriving = false;
		
		if(slipValue >= Constants.inputIndicationNullZone || slipValue <= -Constants.inputIndicationNullZone) {
			indicateSlipping = true;
		} else {
			indicateSlipping = false;
		}
		
		left.set(0.0);
		right.set(0.0);
		
		slip.set(slipValue);
	}

	public void winch(double winchValue) {
		if(winchValue >= Constants.inputIndicationNullZone || winchValue <= -Constants.inputIndicationNullZone) {
			indicateWinching = true;
		} else {
			indicateWinching = false;
		}
		
		winch.set(winchValue);
	}
	
	public DriveBaseDriveBase() {
		setLeftPort(Constants.leftTalonPort);
		setRightPort(Constants.rightTalonPort);
		setSlipPort(Constants.slipTalonPort);
		setWinchPort(Constants.winchTalonPort);
		setLightsPort(Constants.lightsRelayPort);
		
		setupWinchLowerLimitSwitch(Constants.winchLowerLimitSwitchChannel);
		setupWinchUpperLimitSwitch(Constants.winchUpperLimitSwitchChannel);
	}
	
	public DriveBaseDriveBase(final int leftPort, final int rightPort, final int slipPort, final int winchPort, final int lightsPort, final int winchLowerLimitSwitchChannel, final int winchUpperLimitSwitchChannel) {
		setLeftPort(leftPort);
		setRightPort(rightPort);
		setSlipPort(slipPort);
		setSlipPort(winchPort);
		setLightsPort(lightsPort);
		
		setupWinchLowerLimitSwitch(winchLowerLimitSwitchChannel);
		setupWinchUpperLimitSwitch(winchUpperLimitSwitchChannel);
	}
}
