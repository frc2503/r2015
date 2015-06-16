package org.usfirst.frc.team2503.r2015.drivers;

import java.util.HashMap;

import org.usfirst.frc.team2503.r2015.controls.DriveBaseControlLayout;
import org.usfirst.frc.team2503.r2015.robot.ClampStatus;
import org.usfirst.frc.team2503.r2015.robot.DriveBase;
import org.usfirst.frc.team2503.r2015.robot.lights.MasterLightsController.MasterLightsControllerStatus;

public class HumanDriveBaseDriver implements Driver {

	public DriveBase driveBase;
	public DriveBaseControlLayout controlLayout;

	public void init() {

	}

	public void start() {
		reset();
	}

	public void reset() {

	}

	public void tick() {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> values = (HashMap<String, Object>)controlLayout.get();

		{
			driveBase.drive((double)(values.get("left")), (double)(values.get("right")));
			driveBase.winch((double)(values.get("winch")));
		}

		{
			ClampStatus clampStatus = (ClampStatus)values.get("clamp");

			if(clampStatus != null) {
				driveBase.clamp.set(clampStatus);
			}
		}

		{
			MasterLightsControllerStatus masterLightsControllerStatus = (MasterLightsControllerStatus)values.get("masterLightsControllerStatus");

			driveBase.updateLights(masterLightsControllerStatus);
		}
	}

	public void stop() {

	}

	public HumanDriveBaseDriver(DriveBase driveBase, DriveBaseControlLayout controlLayout) {
		this.driveBase = driveBase;
		this.controlLayout = controlLayout;
	}

}
