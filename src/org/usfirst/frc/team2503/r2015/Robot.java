package org.usfirst.frc.team2503.r2015;

import edu.wpi.first.wpilibj.*;

import org.usfirst.frc.team2503.r2015.controls.ControlLayout;
import org.usfirst.frc.team2503.r2015.controls.ControlPort;
import org.usfirst.frc.team2503.r2015.controls.DriveBaseMadCatzV1MadCatzV1ControlLayout;
import org.usfirst.frc.team2503.r2015.controls.joysticks.MadCatzV1Joystick;
import org.usfirst.frc.team2503.r2015.drivers.AutonomousDriveBaseDriver;
import org.usfirst.frc.team2503.r2015.drivers.Driver;
import org.usfirst.frc.team2503.r2015.drivers.HumanDriveBaseDriver;
import org.usfirst.frc.team2503.r2015.robot.ClampStatus;
import org.usfirst.frc.team2503.r2015.robot.DriveBase;
import org.usfirst.frc.team2503.r2015.robot.lights.MasterLightsController.MasterLightsControllerStatus;

public class Robot extends IterativeRobot {
	public DriverStation driverStation;
	public DriveBase driveBase;
	public AutonomousDriveBaseDriver autonomousDriver;
	public HumanDriveBaseDriver teleopDriver;
	
	public void robotInit() {
	}

	public void disabledInit() {
	}

	public void disabledPeriodic() {
		
	}

	public void autonomousInit() {
		{
			driveBase.drive(0.0, 0.0);
			driveBase.masterLightsController.set(MasterLightsControllerStatus.ALL_ON);
			driveBase.clamp.set(ClampStatus.CLOSE);
				
			Timer.delay(1.5);
		}

		{
			driveBase.drive(0.0, 0.0);
		
			Timer.delay(5.0);
		}
			
		{
			driveBase.clamp.set(ClampStatus.CLOSE);
			driveBase.winch(0.8);
			
			Timer.delay(2.0);
		}
			
		{
			driveBase.drive(0.5, 0.0);
				
			Timer.delay(1.75);
		}
			
		{
			driveBase.winch(0.0);	
			driveBase.drive(0.0, 0.0);
			
			Timer.delay(0.1);
		}
			
		{
			driveBase.drive(0.5, -0.5);
				
			Timer.delay(1.9);
		}

		{
			driveBase.drive(0.0, 0.0);
			driveBase.winch(0.0);
			driveBase.masterLightsController.set(MasterLightsControllerStatus.ALL_SLOW_STROBE);
		}
	}
	
	public void autonomousPeriodic() {
	}
	
	public void teleopInit() {
		
	}
	
	public void teleopPeriodic() {
		teleopDriver.tick();
	}
	
	public void testInit() {
	}
	
	public void testPeriodic() {
		driveBase.masterLightsController.set(MasterLightsControllerStatus.ALL_SLOW_CYCLE);
	}
	
	public Robot() {
		driveBase = new DriveBase();

		autonomousDriver = new AutonomousDriveBaseDriver(driveBase);
		teleopDriver = new HumanDriveBaseDriver(driveBase, new DriveBaseMadCatzV1MadCatzV1ControlLayout(new MadCatzV1Joystick(ControlPort.LEFT_PRIMARY), new MadCatzV1Joystick(ControlPort.RIGHT_PRIMARY)));
		driverStation = DriverStation.getInstance();
		
		System.out.println("autonomousDriver" + autonomousDriver);
		System.out.println("teleopDriver" + teleopDriver);
		System.out.println("driverStation" + driverStation);
		
		
	}
}
