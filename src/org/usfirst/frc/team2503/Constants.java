package org.usfirst.frc.team2503;

import java.net.URL;

public class Constants {
	public static final int leftTalonPort = 0;
	public static final int rightTalonPort = 1;
	public static final int winchTalonPort = 2;
	
	public static final int upperLightsRelayPort = 0;
	public static final int lowerLightsRelayPort = 1;
	
	public static int compressorPort = 0;
	
	public static final int winchLowerLimitSwitchChannel = 0;
	public static final int winchUpperLimitSwitchChannel = 1;
	
	public static final int driveBaseLeftSolenoidChannel = 1;
	public static final int driveBaseRightSolenoidChannel = 0;
	
	public static final double inputIndicationNullZone = 0.125;
	
	public static final double drivePrecisionMultiplier = 0.3;
	public static final double masterPowerMultiplier = 1.0;

	
	public static final String piBaseUrl = "http://10.25.3.22:5800";
	public static final String piStatusUrl = piBaseUrl + "/status";
	public static final String piWebUrl = piBaseUrl + "/web";
	public static final String piVisionUrl = piBaseUrl + "/vision";
	public static final String piClientVersion = "0.0.0";
	
	public static final boolean epilepsyMode = true;

}