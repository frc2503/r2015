package org.usfirst.frc.team2503.robot.driveBase;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;

import org.usfirst.frc.team2503.Constants;

public class DriveBaseDriveBase extends DriveBase {
	public Talon left;
	public Talon right;
	public Talon slip;
	public Talon winch;
	
	public Relay lights;
	
	public void setLeftPort(final int port) { left = new Talon(port); }
	public void setRightPort(final int port) { right = new Talon(port); }
	public void setSlipPort(final int port) { slip = new Talon(port); }
	public void setWinchPort(final int port) { winch = new Talon(port); }
	public void setLightsPort(final int port) { lights = new Relay(port, Relay.Direction.kReverse); }
	
	public void drive(double leftValue, double rightValue, double slipValue) {
		left.set(leftValue * Constants.masterPowerMultiplier);
		right.set(rightValue * Constants.masterPowerMultiplier);
		slip.set(slipValue);
	}
	
	public void drive(double leftValue, double rightValue) {
		left.set(leftValue * Constants.masterPowerMultiplier);
		right.set(rightValue * Constants.masterPowerMultiplier);
		
		slip.set(0.0);
	}
	
	public void drive(double slipValue) {
		left.set(0.0);
		right.set(0.0);
		
		slip.set(slipValue);
	}

	public void winch(double winchValue) {
		winch.set(winchValue);
	}
	
	public void lights(boolean lightsOn) {
		lights.set(lightsOn ? Relay.Value.kOn : Relay.Value.kOff);
	}
	
	public DriveBaseDriveBase() {
		setLeftPort(Constants.leftTalonPort);
		setRightPort(Constants.rightTalonPort);
		setSlipPort(Constants.slipTalonPort);
		setWinchPort(Constants.winchTalonPort);
		setLightsPort(Constants.lightsRelayPort);
	}
	
	public DriveBaseDriveBase(final int leftPort, final int rightPort, final int slipPort, final int winchPort, final int lightsPort) {
		setLeftPort(leftPort);
		setRightPort(rightPort);
		setSlipPort(slipPort);
		setSlipPort(winchPort);
		setLightsPort(lightsPort);
	}
}
