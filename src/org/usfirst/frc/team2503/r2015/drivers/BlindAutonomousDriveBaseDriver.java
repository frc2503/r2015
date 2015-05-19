package org.usfirst.frc.team2503.r2015.drivers;

import java.util.HashMap;

import org.usfirst.frc.team2503.r2015.robot.DriveBase;

import edu.wpi.first.wpilibj.Timer;

/**
 * Public: A human-less Driver that doesn't really care about sensor inputs.
 * 
 * driveBase - The Robot's DriveBase.  This is passed in through the
 *             constructor.
 * schedule  - The Autonomous Schedule.  This is passed and set via
 *             setSchedule();
 *             
 * startTime - The time at which the schedule was to've been started.
 * 
 * @author Kristofer Rye
 */
public class BlindAutonomousDriveBaseDriver implements Driver {
	
	public DriveBase driveBase;
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
				System.out.println("Time " + timeSinceStart + " is out of array " + array + " for item " + this.schedule.get(array) + "; skipping.");
			}
		}
	}
	
	public void stop() {
	}
	
	public BlindAutonomousDriveBaseDriver(DriveBase driveBase) {
		this.driveBase = driveBase;
	}
	
}
