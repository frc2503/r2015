package org.usfirst.frc.team2503.r2015;

import edu.wpi.first.wpilibj.*;

import org.usfirst.frc.team2503.r2015.controls.ControlPort;
import org.usfirst.frc.team2503.r2015.controls.joysticks.MadCatzV1Joystick;
import org.usfirst.frc.team2503.r2015.robot.ClampStatus;
import org.usfirst.frc.team2503.r2015.robot.DriveBaseMadCatzV1MadCatzV1Driver;
import org.usfirst.frc.team2503.r2015.robot.lights.MasterLightsController.MasterLightsControllerStatus;

public class Robot extends IterativeRobot {
	public DriveBaseMadCatzV1MadCatzV1Driver madCatzDriveBaseDriver;
	public DriverStation driverStation;

	public void robotInit() {
	}

	public void disabledInit() {
	}

	public void disabledPeriodic() {
		
	}

	public void autonomousInit() {
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
	
	public void autonomousPeriodic() {
	}
	
	public void teleopInit() {
	}
	
	public void teleopPeriodic() {
		madCatzDriveBaseDriver.drive();
	}
	
	public void testInit() {
	}
	
	public void testPeriodic() {
		madCatzDriveBaseDriver.masterLightsController.set(MasterLightsControllerStatus.ALL_SLOW_CYCLE);
	}
	
	public Robot() {
		madCatzDriveBaseDriver = new DriveBaseMadCatzV1MadCatzV1Driver(new MadCatzV1Joystick(ControlPort.LEFT_PRIMARY), new MadCatzV1Joystick(ControlPort.RIGHT_PRIMARY));
		driverStation = DriverStation.getInstance();
	}
}
