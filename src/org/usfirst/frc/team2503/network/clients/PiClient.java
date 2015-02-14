package org.usfirst.frc.team2503.network.clients;

import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;

import org.json.JSONObject;
import org.usfirst.frc.team2503.Constants;
import org.usfirst.frc.team2503.network.HttpGet;
import org.usfirst.frc.team2503.network.HttpPost;

public class PiClient implements Runnable {
	private URL visionUrl;
	private URL statusUrl;
	private URL webUrl;

	public void run() {
		while(true) {
			{
				try {
					HttpGet get = new HttpGet(visionUrl, null);
					
					StringBuffer response = get.fire();
					
					JSONObject object = new JSONObject(response.toString());
					System.out.println(object.toString());					
					
					VisionClient.data = object;
				} catch (SocketException e) {
					e.printStackTrace();
				}
			}
			
			{
				try {
					HttpPost post = new HttpPost(statusUrl, StatusClient.localStatus.toString(), null);
					
					StringBuffer response = post.fire();
					
					JSONObject remoteStatus = new JSONObject(response.toString());
					
					StatusClient.remoteStatus = remoteStatus;
				} catch (SocketException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public PiClient() throws MalformedURLException {
		this.webUrl = new URL(Constants.piWebUrl);
		this.visionUrl = new URL(Constants.piVisionUrl);
		this.statusUrl = new URL(Constants.piStatusUrl);
	}
	
	public PiClient(URL webUrl, URL visionUrl, URL statusUrl) {
		this.webUrl = webUrl;
		this.visionUrl = visionUrl;
		this.statusUrl = statusUrl;
	}
}
