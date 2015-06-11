package org.usfirst.frc.team2503.r2015.network.clients;

import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

public class PiClient extends WebSocketClient implements Runnable {
	public PiClient(URI serverUri) {
		/**
		 * Uses Draft_17 by default, which is the current standard.
		 */
		super(serverUri);
	}
	
	public void onOpen(ServerHandshake handshakeData) {
		System.out.println("[PiClient] Server handshake completed!");
		
		JSONObject object = new JSONObject();
		JSONObject message = new JSONObject();
		JSONObject data = new JSONObject();
		
		data.put("role", "robot");
		message.put("type", "identification");
		message.put("data", data);
		object.put("message", data);
		
		this.send(object.toString());
	}
	
	public void onMessage(String message) {
		System.out.println("[PiClient] Received message: " + message);
	}
	
	public void onClose(int code, String reason, boolean remote) {
		System.out.println("[PiClient] Connection closed by " + (remote ? "remote peer" : "us"));
	}
	
	public void onError(Exception exception) {
		exception.printStackTrace();
	}
	
	public static void main(String[] args) {
		PiClient piClient = null;
		
		try {
			/**
			 * Tell the JVM to GC the old piClient, since we probably don't want it.
			 */
			if(piClient != null) piClient = null;
				
			piClient = new PiClient(new URI("ws://localhost:5800/"));
			piClient.connect();

			while(!piClient.isOpen()) {
				try {
					System.out.println("[PiClient] Not open yet! (closed " + piClient.isClosed() + ")");
						
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				
			while(piClient.isOpen()) {
				System.out.println("[PiClient] Sending keepalive");

				JSONObject object = new JSONObject();
				JSONObject message = new JSONObject();
				message.put("type", "keepalive");
				object.put("message", message);
					
				System.out.println("[PiClient] Sending " + object.toString());
					
				piClient.send(object.toString());
					
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
