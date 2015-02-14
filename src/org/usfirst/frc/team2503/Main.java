package org.usfirst.frc.team2503;

import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.*;
import org.usfirst.frc.team2503.network.*;

import org.usfirst.frc.team2503.network.clients.PiClient;
import org.usfirst.frc.team2503.network.clients.StatusClient;
import org.usfirst.frc.team2503.network.clients.VisionClient;

public class Main {
	public static void main(String[] args) throws MalformedURLException {
		Map<String, String> modeInformation = new HashMap<String, String>();
		modeInformation.put("name", "standalone");
		
		PiClient piClient = new PiClient();
		Thread thread = new Thread(piClient);
		thread.start();
	
		while(true) {
			StatusClient.localStatus.put("mode", modeInformation);

			if(VisionClient.data != null) System.out.println("Vision: " + VisionClient.data.toString());
			System.out.println("Status: local <" + StatusClient.localStatus.toString() + "> remote <" + StatusClient.remoteStatus.toString() + ">");

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
