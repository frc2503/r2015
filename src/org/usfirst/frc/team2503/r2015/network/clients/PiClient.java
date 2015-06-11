package org.usfirst.frc.team2503.r2015.network.clients;

import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;
import org.usfirst.frc.team2503.r2015.Constants;

public class PiClient extends WebSocketClient implements Runnable {
	public UUID uuid;
	
	public JSONObject data;
	
	public PiClient(UUID uuid, URI serverUri) {
		/**
		 * Uses Draft_17 by default, which is the current standard.
		 */
		super(serverUri);
		
		this.uuid = uuid;
		this.data = new JSONObject();
	}

	public PiClient(URI serverUri) {
		/**
		 * Uses Draft_17 by default, which is the current standard.
		 */
		super(serverUri);
		
		this.uuid = UUID.randomUUID();
		this.data = new JSONObject();
		}
	
	public void onOpen(ServerHandshake handshakeData) {
		System.out.println("[PiClient] Server handshake completed!");
	}
	
	public JSONObject mergeJSONObjects(JSONObject originalObject, JSONObject newObject) {
		JSONObject merged = new JSONObject(originalObject);
		
		Set<String> newObjectKeySet = newObject.keySet();
		for(String key : newObjectKeySet) {
			Object originalValue = originalObject.opt(key);
			Object newValue = newObject.get(key);
			switch(newValue.getClass().getName()) {
			case "org.json.JSONObject":
				if(originalValue != null) {
					if(originalValue.getClass().getName() == "org.json.JSONObject") {
						merged.put(key, this.mergeJSONObjects((JSONObject)originalValue, (JSONObject)newValue));
						break;
					}
				}
			default:
				merged.put(key, newObject.get(key));
			}
		}
		
		return merged;
	}

	public void onMessage(String message) {
		System.out.println("[PiClient] Received message: " + message);
		JSONObject object = new JSONObject(message);

		if(object.get("type") != null) {
			switch((String)object.get("type")) {
			case "message":
				System.out.println("[PiClient] Got message of type " + object.get("message_type") + ", \"" + object.get("message") + "\"" + object.get("detail"));
				break;
				
			case "data":
				System.out.println("[PiClient] Got data.");
				System.out.println("[PiClient] Merging data with old data " + this.data.toString());
				Set<String> keys = object.keySet();
				keys.remove("type");

				JSONObject pureJSONObject = new JSONObject();

				for(String key : keys) {
					pureJSONObject.put(key, object.get(key));
				}
				
				this.data = this.mergeJSONObjects(this.data, pureJSONObject);
		
				System.out.println("[PiClient] New data " + this.data.toString());
				break;
				
			case "version":
				break;
			}
		} else {
			System.err.println("[PiClient] Message " + object.toString() + " has no type!");
		}
		
		System.out.println("[PiClient] Message as JSON: " + object);
	}

	public void onClose(int code, String reason, boolean remote) {
		System.out.println("[PiClient] Connection closed by " + (remote ? "remote peer" : "us"));
	}

	public void onError(Exception exception) {
		exception.printStackTrace();
	}
	
	public void send(JSONObject object) {
		this.send(object.toString());
	}
	
	public void sendData(JSONObject data) {
		JSONObject object = new JSONObject();
		object.put("type", "data");
		for(String key : data.keySet()) {
			object.put(key, data.get(key));
		}
		this.send(object);
	}
	
	public void sendMessage(JSONObject message) {
		JSONObject object = new JSONObject();
		object.put("type", "message");
		
		for(String key : message.keySet()) {
			object.put(key, message.get(key));
		}
		
		this.send(object);
	}
	
	public static void main(String[] args) {
		PiClient piClient = null;
		URI uri = null;

		try {
			uri = new URI(Constants.piWsBaseUrl);
		} catch(URISyntaxException e) {
			e.printStackTrace();
			System.exit(1);
		}

		/**
		 * Tell the JVM to GC the old piClient, since we probably don't want it.
		 */
		//if(piClient != null) piClient = null;

		piClient = new PiClient(uri);
		piClient.connect();

		while(!piClient.isOpen()) {
			try {
				if(piClient.isClosed()) System.exit(0);
				
				System.out.println("[PiClient] Not open yet! (closed " + piClient.isClosed() + ")");

				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		while(piClient.isOpen()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
