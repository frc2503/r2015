package org.usfirst.frc.team2503;

import org.json.JSONObject;

public interface Statusable {
	public JSONObject getStatus();
	public JSONObject getStatus(String key);
	public JSONObject putStatus(String key, JSONObject object);
	public JSONObject deleteStatus(String key);
}