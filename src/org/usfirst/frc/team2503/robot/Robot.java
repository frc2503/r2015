package org.usfirst.frc.team2503.robot;

import java.net.MalformedURLException;

import edu.wpi.first.wpilibj.*;

import org.json.JSONObject;
import org.usfirst.frc.team2503.Statusable;
import org.usfirst.frc.team2503.network.clients.PiClient;
import org.usfirst.frc.team2503.network.clients.StatusClient;
import org.usfirst.frc.team2503.network.clients.VisionClient;
import org.usfirst.frc.team2503.robot.controllers.lights.MasterLightsController;
import org.usfirst.frc.team2503.robot.controllers.lights.MasterLightsController.MasterLightsControllerStatus;
import org.usfirst.frc.team2503.robot.driveBase.ClampStatus;
import org.usfirst.frc.team2503.robot.driver.DriveBaseMadCatzV1MadCatzV1DriveBaseDriver;
import org.usfirst.frc.team2503.joystick.MadCatzV1Joystick;
import org.usfirst.frc.team2503.joystick.WarriorJoystick.WarriorJoystickSide;

public class Robot extends IterativeRobot implements Statusable {
	public DriveBaseMadCatzV1MadCatzV1DriveBaseDriver madCatzDriveBaseDriver;
	public DriverStation driverStation;
	public JSONObject __status__ = new JSONObject();

	public void robotInit() {
		__status__.put("alliance", driverStation.getAlliance().name());
	}

	private AnalogInput pwm;

	public void disabledInit() {
		__status__.put("mode", new JSONObject().put("name", "disabled").put("status", "initializing"));
		
		__status__.put("mode", new JSONObject().put("name", "disabled").put("status", "periodic"));
	}

	public void disabledPeriodic() {
		
	}

	public void autonomousInit() {
		__status__.put("mode", new JSONObject().put("name", "autonomous").put("status", "initializing"));
		
		if(VisionClient.hslImage != null) {
			/**
			 * We have the vision! :D
			 */
			
			madCatzDriveBaseDriver.drive(0.0, 0.0);
		} else {
			/**
			 * We're flying blind... :'(
			 */
			{
				/**
				 * Stop driving.
				 */
				madCatzDriveBaseDriver.drive(0.0, 0.0);
				
				/**
				 * Dazzle the crowds with boring lights.
				 */
				madCatzDriveBaseDriver.masterLightsController.set(MasterLightsControllerStatus.ALL_ON);
				
				/**
				 * Close the clamp.
				 */
				madCatzDriveBaseDriver.clamp.set(ClampStatus.CLOSE);
				
				Timer.delay(1.5);
			}

			/**
			 * Stop driving.
			 */
			{
				madCatzDriveBaseDriver.drive(0.0, 0.0);

				/**
				 * Fall asleep while stopping.
				 */
				Timer.delay(5.0);
			}
			
			/**
			 * Winchy winch for a bit
			 */
			{
				/**
				 * Make darn shure dat clamp be closed.
				 */
				madCatzDriveBaseDriver.clamp.set(ClampStatus.CLOSE);
				
				/**
				 * Winch dat [[redacted]]
				 */
				madCatzDriveBaseDriver.winch(0.8);
				
				/**
				 * Fall asleep while winching...
				 * 
				 * #muchdanger #alsosleepy
				 */
				Timer.delay(2.0);
			}
			
			/**
			 * Turn left.
			 */
			{
				/**
				 * (Pivot around front-right wheel)
				 */
				madCatzDriveBaseDriver.drive(0.5, 0.0);
				
				/**
				 * Fall asleep while turning.
				 */
				Timer.delay(1.75);
			}
			
			/**
			 * Stop turning.
			 */
			{
				/**
				 * Stop the winch.
				 */
				madCatzDriveBaseDriver.winch(0.0);	

				/**
				 * Zero percent powah all around
				 */
				madCatzDriveBaseDriver.drive(0.0, 0.0);
				
				/**
				 * Fall asleep while stopping turning.
				 */
				Timer.delay(0.1);
			}
			
			/**
			 * Drive forward.
			 */
			{
				/**
				 * Use half of the power to drive drivage down the driving driven range of driving.
				 */
				madCatzDriveBaseDriver.drive(0.5, -0.5);
				
				/**
				 * Fall asleep while driving forward.
				 */
				Timer.delay(1.9);
			}

			/**
			 * Stop it all.
			 */		
			{
				/**
				 * Stop driving.
				 */
				madCatzDriveBaseDriver.drive(0.0, 0.0);
				
				/**
				 * Stop winching.
				 */	
				madCatzDriveBaseDriver.winch(0.0);
				
				/**
				 * Dazzle the crowds.
				 */
				madCatzDriveBaseDriver.masterLightsController.set(MasterLightsControllerStatus.ALL_SLOW_STROBE);
			}
		}
		
		__status__.put("mode", new JSONObject().put("name", "autonomous").put("status", "periodic"));
	}
	
	public void autonomousPeriodic() {
	}
	
	public void teleopInit() {
		__status__.put("mode", new JSONObject().put("name", "teleoperated").put("status", "initializing"));
		
		__status__.put("mode", new JSONObject().put("name", "teleoperated").put("status", "periodic"));
	}
	
	public void teleopPeriodic() {
		madCatzDriveBaseDriver.drive();
		
		System.out.println(pwm.getValue());
	}
	
	public void testInit() {
		__status__.put("mode", new JSONObject().put("name", "test").put("status", "initializing"));
		
		
		
		__status__.put("mode", new JSONObject().put("name", "test").put("status", "periodic"));
	}
	
	public void testPeriodic() {
		madCatzDriveBaseDriver.masterLightsController.set(MasterLightsControllerStatus.ALL_SLOW_CYCLE);
	}
	
	public Robot() {
		StatusClient.statusController.addStatusable("robot", this);
	
		madCatzDriveBaseDriver = new DriveBaseMadCatzV1MadCatzV1DriveBaseDriver(new MadCatzV1Joystick(WarriorJoystickSide.LEFT_PRIMARY), new MadCatzV1Joystick(WarriorJoystickSide.RIGHT_PRIMARY));
		driverStation = DriverStation.getInstance();
		
		pwm = new AnalogInput(3);
		
		try {
				PiClient piClient = new PiClient();
				Thread thread = new Thread(piClient);
				
				thread.start();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
	}

	public JSONObject getStatus() {
		return __status__;
	}

	public JSONObject getStatus(String key) {
		return new JSONObject(__status__.get(key));
	}

	public JSONObject putStatus(String key, JSONObject object) {
		return __status__.put(key, object);
	}

	public JSONObject deleteStatus(String key) {
		return __status__.put(key, (Object)null);
	}
}
