package org.usfirst.frc.team2503.r2015.drivers;

import java.util.Comparator;
import java.util.HashMap;

import org.usfirst.frc.team2503.r2015.robot.ClampStatus;
import org.usfirst.frc.team2503.r2015.robot.DriveBase;
import org.usfirst.frc.team2503.r2015.robot.lights.MasterLightsController.MasterLightsControllerStatus;

import edu.wpi.first.wpilibj.Timer;

public class BlindAutonomousDriveBaseDriver implements Driver {
	
	public DriveBase driveBase;
	public HashMap<String, Object> values;
	public HashMap<double[], BlindAutonomousScheduleItem> schedule;
	
	private double startTime;
	
	public void init() {
	}
	
	public void start() {
		this.startTime = Timer.getFPGATimestamp();
	}
	
	public void reset() {
	}
	
	public void setSchedule(HashMap<double[], BlindAutonomousScheduleItem> schedule) {
		this.schedule = schedule;
	}
	
	public void tick() {
		double currentTime = Timer.getFPGATimestamp();
		double timeSinceStart = currentTime - this.startTime;
		
		for(double[] array : this.schedule.keySet()) {
			if(array[0] <= timeSinceStart && array[1] > timeSinceStart) {
				this.schedule.get(array).run(timeSinceStart);
			} else {
				System.out.println("Time " + timeSinceStart + " is out of array " + array + "; skipping.");
			}
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
	
	public void stop() {
	}
	
	public BlindAutonomousDriveBaseDriver(DriveBase driveBase) {
		this.driveBase = driveBase;
	}
	
}
