package org.usfirst.frc.team2503.r2015;

public class Constants {
	
	public enum TeleopControlLayout {
		
		DRIVE_BASE_MADCATZ_V1_JOYSTICK_MADCATZ_V1_JOYSTICK_CONTROL_LAYOUT(0),
		DRIVE_BASE_LOGITECH_F310_GAMEPAD_CONTROL_LAYOUT(1);
		
		private int number;
		
		public int getValue() {
			return this.number;
		}
		
		private TeleopControlLayout(int number) {
			this.number = number;
		}
		
	}
	
	public static final int leftTalonPort = 0;
	public static final int rightTalonPort = 1;
	public static final int winchTalonPort = 2;
	
	public static final int leftEncoderDioAChannel = 8;
	public static final int leftEncoderDioBChannel = 9;
	public static final int rightEncoderDioAChannel = 6;
	public static final int rightEncoderDioBChannel = 7;
	
	public static final int upperLightsRelayPort = 0;
	public static final int upperChannelLightsRelayPort = 1;
	public static final int underGlowLightsRelayPort = 2;
	public static final int cameraLightLightsRelayPort = 3;

	public static int compressorPort = 0;
	
	public static final int winchLowerLimitSwitchChannel = 0;
	public static final int winchUpperLimitSwitchChannel = 1;
	
	public static final int driveBaseLeftSolenoidChannel = 0;
	public static final int driveBaseRightSolenoidChannel = 1;
	
	public static final double inputIndicationNullZone = 0.125;
	
	public static final double drivePrecisionMultiplier = 0.3;
	public static final double masterPowerMultiplier = 1.0;
	public static final double powerMultiplier = 1.0;
	
	public static final String piBaseUrl = "192.168.43.9:5080";
	public static final String piHttpBaseUrl = "http://" + piBaseUrl;
	public static final String piWsBaseUrl = "ws://" + piBaseUrl;
	public static final String piClientVersion = "0.0.0";
	
	public static final boolean PERMISSION_LIGHTS_CONTROL = true;
	public static final boolean PERMISSION_COMPRESSOR_CONTROL = false;
	
	public static final boolean AUTONOMOUS_USE_SYNCHRONOUS = true;
	public static final boolean AUTONOMOUS_HAVE_VISION_DONE_YET = false;
	public static final boolean AUTONOMOUS_HAVE_CAMERA = false;
	
	public static TeleopControlLayout teleopControlLayout = TeleopControlLayout.DRIVE_BASE_MADCATZ_V1_JOYSTICK_MADCATZ_V1_JOYSTICK_CONTROL_LAYOUT;
	//public static TeleopControlLayout teleopControlLayout = TeleopControlLayout.DRIVE_BASE_LOGITECH_F310_GAMEPAD_CONTROL_LAYOUT;
	
}