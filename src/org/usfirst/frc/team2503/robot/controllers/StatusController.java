package org.usfirst.frc.team2503.robot.controllers;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONObject;
import org.usfirst.frc.team2503.Statusable;

public class StatusController {
	private static HashMap<String, Statusable> statusables = new HashMap<String, Statusable>();
	
	public void addStatusable(String name, Statusable statusable) {
		statusables.put(name, statusable);
	}
	
	public void removeStatusable(String name) {
		statusables.remove(name);
	}
	
	public JSONObject getAllStatuses() {
		JSONObject completeStatuses = new JSONObject();

		Iterator<String> statusableKeysIterator = statusables.keySet().iterator();
		while(statusableKeysIterator.hasNext()) {
			String statusableKey = (String)statusableKeysIterator.next();
			
			completeStatuses.put(statusableKey, statusables.get(statusableKey).getStatus());
		}
		
		return completeStatuses;
	}
	
	public StatusController() {
	}
}
