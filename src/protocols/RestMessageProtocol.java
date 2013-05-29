package protocols;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.freeshell.zs.common.HtmlManipulator;

import middleware.igor_marques_basic_remote_patterns.InvocationData;
import middleware.igor_marques_basic_remote_patterns.client_side.ClientRequestHandler;

public class RestMessageProtocol extends MessageProtocol {

	public RestMessageProtocol(ClientRequestHandler handler) {
		super(handler);
	}

	@Override
	public String sendMessage(String endpoint, InvocationData invocation) throws IOException {
		URL url;
		HttpURLConnection connection = null;
		
		String urlParams = generateParams(invocation);
		
		try {
			url = new URL(endpoint + "/" + invocation.getObjectID() + "/" + invocation.getMethod());
			
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length",
				Integer.toString(urlParams.toString().getBytes().length));

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
			
			return HtmlManipulator.replaceHtmlEntities(response.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
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
}
