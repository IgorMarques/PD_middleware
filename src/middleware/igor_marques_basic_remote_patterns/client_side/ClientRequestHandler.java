package middleware.igor_marques_basic_remote_patterns.client_side;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import middleware.igor_marques_basic_remote_patterns.InvocationData;

public class ClientRequestHandler {
	
	public void sendMessage(String endpoint, InvocationData invocation) throws IOException {
		URL url;
		HttpURLConnection connection = null;
		
		String urlParams = generateParams(invocation);
		
		try {
			url = new URL(endpoint + "/" + invocation.getObjectID() + "/" + invocation.getMethod());
			
			System.out.println(endpoint + "/" + invocation.getObjectID() + "/" + invocation.getMethod());
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Type",
				Integer.toString(urlParams.toString().getBytes().length));
			connection.setRequestProperty("Content-Language", "pt-BR");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			// Send request
			DataOutputStream wr = new DataOutputStream(
				connection.getOutputStream());
			wr.writeBytes(urlParams);
			wr.flush();
			wr.close();
			
			/////////////////////////////////////////
			
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			
			System.out.println(response.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private String generateParams(InvocationData invocation) {
		StringBuilder builder = new StringBuilder();
		String prefix = "";
		
		for (String param: invocation.getParamsName()) {
			builder.append(prefix);
			prefix = "&";
			builder.append(param + "=" + invocation.getParam(param));
		}
		
		return builder.toString();
	}
	
	public static void main(String[] args) throws IOException {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("login", "user123");
		params.put("password", "rootadmin");
		
		InvocationData data = new InvocationData("hue", "br", params);
		
		ClientRequestHandler crh = new ClientRequestHandler();
		crh.sendMessage("http://localhost:3333", data);
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
