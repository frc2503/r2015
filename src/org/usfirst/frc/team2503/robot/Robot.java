package org.usfirst.frc.team2503.robot;

import java.net.MalformedURLException;
import java.util.Map;

import edu.wpi.first.wpilibj.*;

import org.usfirst.frc.team2503.Statusable;
import org.usfirst.frc.team2503.network.clients.PiClient;
import org.usfirst.frc.team2503.network.clients.StatusClient;
import org.usfirst.frc.team2503.network.clients.VisionClient;
import org.usfirst.frc.team2503.robot.driver.DriveBaseMadCatzV1MadCatzV1DriveBaseDriver;
import org.usfirst.frc.team2503.joystick.MadCatzV1Joystick;
import org.usfirst.frc.team2503.joystick.WarriorJoystick.WarriorJoystickSide;

public class Robot extends IterativeRobot implements Statusable {
	public DriveBaseMadCatzV1MadCatzV1DriveBaseDriver madCatzDriveBaseDriver;
	public DriverStation driverStation;
	public DigitalInput lowerLimitSwitch;
		
	public Map<String, String> modeInformation;
	
    public void robotInit() {}
    
    public void disabledInit() {
    	/**
    	 * Now initializing for disabled mode.
    	 */
    	modeInformation.put("name", "disabled");
    	modeInformation.put("status", "initializing");
    	StatusClient.localStatus.put("mode", modeInformation);
   
    	
    	
    	/**
    	 * Now entering disabled mode.
    	 */
    	modeInformation.put("name", "disabled");
    	modeInformation.put("status", "periodic");
    	StatusClient.localStatus.put("mode", modeInformation);	
    }
    
    public void disabledPeriodic() {
	
    }

    public void autonomousInit() {
    	/**
    	 * Now initializing for autonomous mode.
    	 */
    	modeInformation.put("name", "autonomous");
    	modeInformation.put("status", "initializing");
    	StatusClient.localStatus.put("mode", modeInformation);
   
    	if(VisionClient.data != null) {
    		/**
    		 * We have the vision! :D
    		 */
    		
    		madCatzDriveBaseDriver.drive(0.0, 0.0);
    	} else {
    		/**
    		 * We're flying blind... :'(
    		 */
    	}
    	
    	/**
    	 * Now entering autonomous mode.
    	 */
    	modeInformation.put("name", "autonomous");
    	modeInformation.put("status", "periodic");
    	StatusClient.localStatus.put("mode", modeInformation);	
    }
    
    public void autonomousPeriodic() {
    }
    
    public void teleopInit() {
    	/**
    	 * Now initializing for autonomous mode.
    	 */
    	modeInformation.put("name", "teleoperated");
    	modeInformation.put("status", "initializing");
    	StatusClient.localStatus.put("mode", modeInformation);
   
    	
    	
    	/**
    	 * Now entering autonomous mode.
    	 */
    	modeInformation.put("name", "teleoperated");
    	modeInformation.put("status", "periodic");
    	StatusClient.localStatus.put("mode", modeInformation);	
    }
    
    public void teleopPeriodic() {
    	madCatzDriveBaseDriver.drive();
    }
    
    public void testInit() {
    	/**
    	 * Now initializing for test mode.
    	 */
    	modeInformation.put("name", "test");
    	modeInformation.put("status", "initializing");
    	StatusClient.localStatus.put("mode", modeInformation);
   
    	/**
    	 * Now entering test mode.
    	 */
    	modeInformation.put("name", "test");
    	modeInformation.put("status", "periodic");
    	StatusClient.localStatus.put("mode", modeInformation);	
    }
    
    public void testPeriodic() {
    }
    
    public Robot() {
    	madCatzDriveBaseDriver = new DriveBaseMadCatzV1MadCatzV1DriveBaseDriver(new MadCatzV1Joystick(WarriorJoystickSide.LEFT_PRIMARY), new MadCatzV1Joystick(WarriorJoystickSide.RIGHT_PRIMARY));
    	driverStation = DriverStation.getInstance();
    	
    	try {
			PiClient piClient = new PiClient();
			Thread thread = new Thread(piClient);
			
			thread.start();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    }
}
