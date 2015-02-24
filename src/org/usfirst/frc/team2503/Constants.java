package org.usfirst.frc.team2503;

public class Constants {
	public static final int leftTalonPort = 0;
	public static final int rightTalonPort = 1;
	public static final int winchTalonPort = 2;
	
	public static final int upperLightsRelayPort = 0;
	public static final int upperChannelLightsRelayPort = 1;
	public static final int underGlowLightsRelayPort = 2;
	public static final int cameraLightLightsRelayPort = 3;

	public static int compressorPort = 0;
	
	public static final int winchLowerLimitSwitchChannel = 0;
	public static final int winchUpperLimitSwitchChannel = 1;
	
	public static final int driveBaseLeftSolenoidChannel = 1;
	public static final int driveBaseRightSolenoidChannel = 0;
	
	public static final double inputIndicationNullZone = 0.125;
	
	public static final double drivePrecisionMultiplier = 0.3;
	public static final double masterPowerMultiplier = 1.0;
	
	public static final String piBaseUrl = "http://192.168.1.103:5800";
	public static final String piWebUrl = piBaseUrl + "/web";
	public static final String piVisionUrl = piBaseUrl + "/vision";
	public static final String piStatusUrl = piBaseUrl + "/status?k=robot";
	public static final String piClientVersion = "0.0.0";
	
	public static final boolean PERMISSION_COMPRESSOR_CONTROL = true;
}