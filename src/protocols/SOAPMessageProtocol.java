package protocols;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import org.freeshell.zs.common.HtmlManipulator;

import middleware.igor_marques_basic_remote_patterns.InvocationData;
import middleware.igor_marques_basic_remote_patterns.client_side.ClientRequestHandler;

public class SOAPMessageProtocol extends MessageProtocol {

	public SOAPMessageProtocol(ClientRequestHandler handler) {
		super(handler);
	}
	
	
	//DESCOBRIR O QUE FAZER

	@Override
	public String sendMessage(String endpoint, InvocationData invocation) throws IOException {
		URL url;
		HttpURLConnection connection = null;
		
		String urlParams = generateSoapBody(invocation, endpoint);
		
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

	private String generateSoapBody(InvocationData invocation, String endpoint) {
		String prefix = "";
		    
	    StringBuilder result = new StringBuilder();
	    
	    result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<soap:Envelope xmlns:xsi=\"http:w3.org/2001/XMLSchema-instance\" xmlns:soap=\"http:schemas.xmlsoap.org/soap/envelope/\" xmlns:xs=\"http:www.w3.org/2001/XMLSchema\">"
	            + "<soap:Header>"
	            + "</soap:Header>\n");
	    
	    result.append("<soap:Body>\n");
	    
	    result.append("<" + invocation.getMethod() + " xmlns=\"" + endpoint + "\">\n");
	    
	    for(String p:invocation.getParamsName()){
	      result.append("<" + p + ">" + invocation.getParam(p) + "</" + p + ">\n");
	    }
	    
	    result.append("</" + invocation.getMethod() + ">\n");
	    result.append("</soap:Body></soap:Envelope>");
		
	  
		return result.toString();
	}
}
