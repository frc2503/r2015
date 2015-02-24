package org.usfirst.frc.team2503.network.clients;

import org.json.JSONObject;
import org.usfirst.frc.team2503.robot.controllers.StatusController;

public class StatusClient implements SuccessMeasuringNetworkClient {
	public static JSONObject localStatus = new JSONObject();
	public static JSONObject remoteStatus = new JSONObject();
	
	public static boolean previousRequestWasSuccessful = false;
	
	public static StatusController statusController = new StatusController();
}