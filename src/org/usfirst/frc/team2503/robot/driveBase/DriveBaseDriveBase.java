package org.usfirst.frc.team2503.robot.driveBase;

import edu.wpi.first.wpilibj.Talon;
import org.usfirst.frc.team2503.Constants;

public class DriveBaseDriveBase extends DriveBase {
	public Talon left;
	public Talon right;
	public Talon slip;
	
	public void setLeftPort(final int port) { left = new Talon(port); }
	public void setRightPort(final int port) { right = new Talon(port); }
	public void setSlipPort(final int port) { slip = new Talon(port); }
	
	public void drive(double leftValue, double rightValue, double slipValue) {
		left.set(leftValue);
		right.set(rightValue);
		slip.set(slipValue);
	}
	
	public void drive(double leftValue, double rightValue) {
		left.set(leftValue);
		right.set(rightValue);
		
		slip.set(0.0);
	}
	
	public void drive(double slipValue) {
		left.set(0.0);
		right.set(0.0);
		
		slip.set(slipValue);
	}
	
	public DriveBaseDriveBase() {
		setLeftPort(Constants.leftTalonPort);
		setRightPort(Constants.rightTalonPort);
		setSlipPort(Constants.slipTalonPort);
	}
	
	public DriveBaseDriveBase(final int leftPort, final int rightPort, final int slipPort) {
		setLeftPort(leftPort);
		setRightPort(rightPort);
		setSlipPort(slipPort);
	}
}
