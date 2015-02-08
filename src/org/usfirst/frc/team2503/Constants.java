package org.usfirst.frc.team2503;

public class Constants {
	public static final int leftTalonPort = 0;
	public static final int rightTalonPort = 1;
	public static final int slipTalonPort = 2;
	public static final int winchTalonPort = 3;
	
	public static final int lightsRelayPort = 0;
	
	public static final int winchLowerLimitSwitchChannel = 0;
	public static final int winchUpperLimitSwitchChannel = 1;
	
	public static final double inputIndicationNullZone = 0.125;
	
	public static final double drivePrecisionMultiplier = 0.3;
	public static final double masterPowerMultiplier = 1.0;
	
	public static final String piUrl = "http://palochka.local:5800/data";
	public static final String piWebUrl = "http://palochka.local:5800/web";
	public static final String piClientVersion = "0.0.0";
	
	public static final boolean epilepsyMode = true;
}