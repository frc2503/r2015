package org.usfirst.frc.team2503.r2015.drivers;

import java.util.HashMap;

import org.usfirst.frc.team2503.r2015.controls.DriveBaseControlLayout;
import org.usfirst.frc.team2503.r2015.robot.ClampStatus;
import org.usfirst.frc.team2503.r2015.robot.DriveBase;
import org.usfirst.frc.team2503.r2015.robot.lights.MasterLightsController.MasterLightsControllerStatus;

public class AutonomousDriveBaseDriver implements Driver {
	
	public DriveBase driveBase;
	public HashMap<String, Object> values;		
	
	public void tick() {
	}
	
	public AutonomousDriveBaseDriver(DriveBase driveBase) {
		this.driveBase = driveBase;
	}
	
}
