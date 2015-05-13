package org.usfirst.frc.team2503.r2015;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team2503.r2015.network.clients.PiClient;
import org.usfirst.frc.team2503.r2015.network.clients.StatusClient;

public class Main {
	public static void main(String[] args) throws MalformedURLException {
		Map<String, String> modeInformation = new HashMap<String, String>();
		modeInformation.put("name", "standalone");
		
		PiClient piClient = new PiClient();
		Thread thread = new Thread(piClient);
		thread.start();
	
		while(true) {
			StatusClient.localStatus.put("mode", modeInformation);

			System.out.println("Status: local <" + StatusClient.localStatus.toString() + "> remote <" + StatusClient.remoteStatus.toString() + ">");

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
