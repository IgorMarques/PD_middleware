package middleware.igor_marques_basic_remote_patterns.client_side;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import protocols.MessageProtocol;
import protocols.RestMessageProtocol;

import middleware.igor_marques_basic_remote_patterns.InvocationData;

public class ClientRequestHandler {
	
	private static ClientRequestHandler instance = null;
	
	private ClientRequestHandler() {
		 
	};

	public static ClientRequestHandler getInstance() {
		if (instance == null) {
			instance = new ClientRequestHandler();
		}
		return instance;
	}
	
	private MessageProtocol messageProtocol;
	
	public void sendMessage(String endpoint, InvocationData invocation) throws IOException {
		messageProtocol = new RestMessageProtocol(this); //mudar pra uma factory
		System.out.println(messageProtocol.sendMessage(endpoint, invocation));
	}
	
	//Teste
	public static void main(String[] args) throws IOException {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("CityName", "Natal");
		params.put("CountryName", "Brazil");
		
		InvocationData data = new InvocationData("globalweather.asmx", "GetWeather", params);
		
		ClientRequestHandler crh = new ClientRequestHandler();
		crh.sendMessage("http://www.webservicex.com", data);
	}

	public static String executePost(String targetURL, String urlParameters) {
		URL url;
		HttpURLConnection connection = null;
		
		try {
			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length",
					"" + Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "pt-BR");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(
				connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
