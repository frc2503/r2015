package org.usfirst.frc.team2503.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import javax.net.ssl.HttpsURLConnection;

import org.usfirst.frc.team2503.Constants;

import edu.wpi.first.wpilibj.Timer;

public class HttpNetworkClient implements Runnable {
	private URL url;
	private HttpURLConnection connection;
	
	private boolean isRunning = true;
	
	public String post(String url, String body, HashMap<String, String> headers) {
		Set<String> headerKeys = headers.keySet();
		
		try {
			this.url = new URL(url);
			
			connection = (HttpURLConnection)this.url.openConnection();
			connection.setRequestMethod("POST");
			
			for(int i = 0; i < headerKeys.size(); i += 1) {
				String headerKey = (String)headerKeys.toArray()[i];
				
				connection.setRequestProperty(headerKey, headers.get(headerKey));
			}

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
					
			DataOutputStream requestStream = new DataOutputStream(connection.getOutputStream());
			requestStream.writeBytes(body);
			requestStream.flush();
			requestStream.close();
				
			InputStream inputStream = connection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				
			String line;
			StringBuffer response = new StringBuffer();
			
			while((line = bufferedReader.readLine()) != null) {
				response.append(line);
			    response.append('\r');
			}
				
			bufferedReader.close();
			return response.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void start() {
		isRunning = true;
	}
	
	public void onStart() {
	}
	
	public void onStop() {
	}
	
	public void stop() {
		isRunning = false;
	}
	
	public void run() {
		while(isRunning) {
			HashMap<String, String> swagMap = new HashMap<String, String>();
			
			swagMap.put("User-Agent", "mothafucka");
			
			System.out.println(post(Constants.piUrl, "ayyy lmao", swagMap));
			
			Timer.delay(0.5);
		}
	}
	
	public HttpNetworkClient() {
	}
}
