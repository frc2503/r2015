package org.usfirst.frc.team2503.r2015.network.clients;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.usfirst.frc.team2503.r2015.Constants;
import org.usfirst.frc.team2503.r2015.network.HttpGet;
import org.usfirst.frc.team2503.r2015.network.HttpPost;

import edu.wpi.first.wpilibj.image.HSLImage;
import edu.wpi.first.wpilibj.image.NIVisionException;

public class PiClient implements Runnable {
	private URL visionUrl;
	private URL statusUrl;

	private void updateVision() {
		try {
			if(((HashMap<String, String>)StatusClient.localStatus.get("mode")).get("name") != "standalone") {
				try {
					HttpGet get = new HttpGet(visionUrl, null);
					
					StringBuffer response = get.fire();
	
					try {
						File temporaryFile = File.createTempFile("pi-vision", "png");
						FileOutputStream temporaryFileOutputStream = new FileOutputStream(temporaryFile);
						
						DataOutputStream outputStream = new DataOutputStream(temporaryFileOutputStream);
						outputStream.writeBytes(response.toString());
						outputStream.close();
						
						VisionClient.hslImage = new HSLImage(temporaryFile.getAbsolutePath());
						VisionClient.previousRequestWasSuccessful = true;
					} catch(IOException e) {
						VisionClient.previousRequestWasSuccessful = false;
						
						e.printStackTrace();
					} catch (NIVisionException e) {
						VisionClient.previousRequestWasSuccessful = false;
						
						e.printStackTrace();
					}
				} catch(SocketException e) {
					VisionClient.previousRequestWasSuccessful = false;
					
					e.printStackTrace();
				}
			}
		} catch(JSONException e) {
			e.printStackTrace();
		}
	}
	
	public void updateStatus() {
		try {
			HttpPost post = new HttpPost(statusUrl, StatusClient.localStatus.toString(), null);
			
			StringBuffer response = post.fire();
			
			JSONObject remoteStatus = new JSONObject(response.toString());
			
			StatusClient.remoteStatus = remoteStatus;
		} catch (SocketException e) {
			StatusClient.previousRequestWasSuccessful = false;
			
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			updateVision();
			updateStatus();
		}
	}
	
	public PiClient() throws MalformedURLException {
		this.visionUrl = new URL(Constants.piVisionUrl);
		this.statusUrl = new URL(Constants.piStatusUrl);
	}
	
	public PiClient(URL visionUrl, URL statusUrl) {
		this.visionUrl = visionUrl;
		this.statusUrl = statusUrl;
	}
}
