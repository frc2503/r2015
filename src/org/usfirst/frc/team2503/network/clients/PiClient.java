package org.usfirst.frc.team2503.network.clients;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.usfirst.frc.team2503.Constants;
import org.usfirst.frc.team2503.network.HttpGet;
import org.usfirst.frc.team2503.network.HttpPost;

public class PiClient implements Runnable {	
	public HashMap<String, String> getHeaders;
	public HashMap<String, String> postHeaders;
	
	public StringBuffer getResponse;
	public StringBuffer postResponse;
	
	public void setGetHeaders(HashMap<String, String> headers) { this.getHeaders = headers; }
	public void setPostHeaders(HashMap<String, String> headers) { this.postHeaders = headers; }
	
	public void run() {
		getHeaders.put("User-Agent", "pi-get/" + Constants.piClientVersion);
		postHeaders.put("User-Agent", "pi-get/" + Constants.piClientVersion);

		while(true) {
			try {
				this.getResponse = new HttpGet(new URL(Constants.piVisionUrl), getHeaders).fire();
			} catch(ConnectException e) {
				System.out.println("[PiClient HttpGet] Connect fail: " + e.getMessage());
			} catch(MalformedURLException e) {
				e.printStackTrace();
			}
			
			try {
				this.postResponse = new HttpPost(new URL(Constants.piVisionUrl), "", postHeaders).fire();
			} catch(ConnectException e) {
				System.out.println("[PiClient HttpPost] Connect fail: " + e.getMessage());
			} catch(MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public PiClient() {
		getHeaders = new HashMap<String, String>();
		postHeaders = new HashMap<String, String>();
	}
}
