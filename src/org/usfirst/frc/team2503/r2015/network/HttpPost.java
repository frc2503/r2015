package org.usfirst.frc.team2503.r2015.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;

public class HttpPost {
	public URL url;
	public String body;
	
	public HashMap<String, String> headers;
	
	public HttpURLConnection connection;
	
	public StringBuffer fire() throws ConnectException {
		Set<String> headerKeys = null;
		if(headers != null) {
			headerKeys = headers.keySet();
		}
		
		try {
			connection = (HttpURLConnection)this.url.openConnection();
			connection.setRequestMethod("POST");
			
			if(headerKeys != null) {
				for(int i = 0; i < headerKeys.size(); i += 1) {
					String headerKey = (String)headerKeys.toArray()[i];
					
					connection.setRequestProperty(headerKey, headers.get(headerKey));
				}
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
			
			return response;
		} catch(ConnectException e) {
			throw e;
		} catch (ProtocolException e) {
			e.printStackTrace();
			return null;
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public HttpPost(URL url, String body, HashMap<String, String> headers) {
		this.url = url;
		this.body = body;
		this.headers = headers;
	}
}
