package org.usfirst.frc.team2503;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.json.*;
import org.usfirst.frc.team2503.network.*;
import org.usfirst.frc.team2503.network.clients.PiClient;

public class Main {
	public static void main(String[] args) throws MalformedURLException {
		PiClient piClient = new PiClient();
		
		Thread thread = new Thread(piClient);
		thread.start();
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
