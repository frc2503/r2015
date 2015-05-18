package org.usfirst.frc.team2503.r2015;

import java.net.MalformedURLException;

import edu.wpi.first.wpilibj.*;

import org.json.JSONObject;
import org.usfirst.frc.team2503.r2015.controls.ControlPort;
import org.usfirst.frc.team2503.r2015.controls.joysticks.MadCatzV1Joystick;
import org.usfirst.frc.team2503.r2015.network.clients.PiClient;
import org.usfirst.frc.team2503.r2015.network.clients.StatusClient;
import org.usfirst.frc.team2503.r2015.network.clients.VisionClient;
import org.usfirst.frc.team2503.r2015.robot.ClampStatus;
import org.usfirst.frc.team2503.r2015.robot.DriveBaseMadCatzV1MadCatzV1Driver;
import org.usfirst.frc.team2503.r2015.robot.controllers.MasterLightsController;
import org.usfirst.frc.team2503.r2015.robot.controllers.MasterLightsController.MasterLightsControllerStatus;

public class Robot extends IterativeRobot implements Statusable {
	public DriveBaseMadCatzV1MadCatzV1Driver madCatzDriveBaseDriver;
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
			madCatzDriveBaseDriver.drive(0.0, 0.0);
		} else {
			{
				madCatzDriveBaseDriver.drive(0.0, 0.0);
				madCatzDriveBaseDriver.masterLightsController.set(MasterLightsControllerStatus.ALL_ON);
				madCatzDriveBaseDriver.clamp.set(ClampStatus.CLOSE);
				
				Timer.delay(1.5);
			}

			{
				madCatzDriveBaseDriver.drive(0.0, 0.0);

				Timer.delay(5.0);
			}
			
			{
				madCatzDriveBaseDriver.clamp.set(ClampStatus.CLOSE);
				madCatzDriveBaseDriver.winch(0.8);
				
				Timer.delay(2.0);
			}
			
			{
				madCatzDriveBaseDriver.drive(0.5, 0.0);
				
				Timer.delay(1.75);
			}
			
			{
				madCatzDriveBaseDriver.winch(0.0);	
				madCatzDriveBaseDriver.drive(0.0, 0.0);
				
				Timer.delay(0.1);
			}
			
			{
				madCatzDriveBaseDriver.drive(0.5, -0.5);
				
				Timer.delay(1.9);
			}

			{
				madCatzDriveBaseDriver.drive(0.0, 0.0);
				madCatzDriveBaseDriver.winch(0.0);
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
	
		madCatzDriveBaseDriver = new DriveBaseMadCatzV1MadCatzV1Driver(new MadCatzV1Joystick(ControlPort.LEFT_PRIMARY), new MadCatzV1Joystick(ControlPort.RIGHT_PRIMARY));
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
