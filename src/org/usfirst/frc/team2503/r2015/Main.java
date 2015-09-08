package org.usfirst.frc.team2503.r2015;

import java.net.MalformedURLException;
import java.net.URI;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;

public class Main {
	public static void main(String[] args) {
		URI uri = new URI("ws://localhost:5080/");
		WebSocketClient wsc = new WebSocketClient(uri);
	}
}
