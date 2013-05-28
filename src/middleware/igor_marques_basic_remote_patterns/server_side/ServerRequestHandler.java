package middleware.igor_marques_basic_remote_patterns.server_side;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class ServerRequestHandler implements IServerRequestHandler, HttpHandler {

	private List<AbstractRemoteObject> remoteObjects;
	private HttpServer server;

	public ServerRequestHandler() throws IOException {
		remoteObjects = new ArrayList<AbstractRemoteObject>();
		server = HttpServer.create(new InetSocketAddress(3333), 0);
	}

	public void addRemoteObject(AbstractRemoteObject aro) {
		remoteObjects.add(aro);
	}

	public void start() {
		server.createContext("/", this);
		server.setExecutor(null);
		server.start();
	}

	@Override
	public void sendMessage() {
	}

	@Override
	public void receiveMessage() {
	}

	@Override
	public void handle(HttpExchange t) throws IOException {
		
		//mudar pra resposta de verdade
		String response = ""
				+ "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"
				+ "<soap12:Body>"
				+ "<GetWeatherResponse xmlns=\"http://www.webserviceX.NET\">"
				+ "<GetWeatherResult>string</GetWeatherResult>"
				+ "</GetWeatherResponse>" + "</soap12:Body>"
				+ "</soap12:Envelope>";

		//System.out.println(response);
		t.sendResponseHeaders(200, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
		
	}

	public void httpExchangePrinter(HttpExchange t) throws IOException {
        final InputStream is;
        final OutputStream os;
        StringBuilder buf;
        int b;
        final String request, response;

        buf = new StringBuilder();

        /*
         * Get the request body and decode it. Regardless of what you are
         * actually doing, it is apparently considered correct form to consume
         * all the bytes from the InputStream. If you don't, closing the
         * OutputStream will cause that to occur
         */

        is = t.getRequestBody();

        while ((b = is.read()) != -1) {
            buf.append((char) b);
        }

        is.close();

        if (buf.length() > 0) {
            request = URLDecoder.decode(buf.toString(), "UTF-8");
        } else {
            request = null;
        }

        /*
         * Construct our response:
         */

        buf = new StringBuilder();
        buf.append("<html><head><title>HTTP echo server</title></head><body>");
        buf.append("<p><pre>");
        buf.append(t.getRequestMethod() + " " + t.getRequestURI() + " " + t.getProtocol() + "\n");

        /*
         * Process the request headers. This is a bit involved due to the
         * complexity arising from the fact that headers can be repeated.
         */

        Headers headers = t.getRequestHeaders();

        for (String name : headers.keySet()) {
            List<String> values = headers.get(name);

            for (String value : values) {
                buf.append(name + ": " + value + "\n");
            }
        }

        /*
         * If there was an actual body to the request, add it:
         */

        if (request != null) {
            buf.append("\n");
            buf.append(request);
        }

        buf.append("</pre></p>");
        buf.append("</body></html>\n");

        response = buf.toString();

        /*
         * And now send the response. We could have instead done this
         * dynamically, using 0 as the response size (forcing chunked
         * encoding) and writing the bytes of the response directly to the
         * OutputStream, but building the String first allows us to know the
         * exact length so we can send a response with a known size. Better :)
         */

        t.sendResponseHeaders(200, response.length());

        os = t.getResponseBody();

        os.write(response.getBytes());

        /*
         * And we're done!
         */

        os.close();
        t.close();
    }
	
	public static String executePost(String targetURL, String urlParameters)
	  {
	    URL url;
	    HttpURLConnection connection = null;  
	    try {
	      //Create connection
	      url = new URL(targetURL);
	      connection = (HttpURLConnection)url.openConnection();
	      connection.setRequestMethod("POST");
	      connection.setRequestProperty("Content-Type", 
	           "application/x-www-form-urlencoded");
				
	      connection.setRequestProperty("Content-Length", "" + 
	               Integer.toString(urlParameters.getBytes().length));
	      connection.setRequestProperty("Content-Language", "en-US");  
				
	      connection.setUseCaches (false);
	      connection.setDoInput(true);
	      connection.setDoOutput(true);

	      //Send request
	      DataOutputStream wr = new DataOutputStream (
	                  connection.getOutputStream ());
	      wr.writeBytes (urlParameters);
	      wr.flush ();
	      wr.close ();

	      //Get Response	
	      InputStream is = connection.getInputStream();
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	      String line;
	      StringBuffer response = new StringBuffer(); 
	      while((line = rd.readLine()) != null) {
	        response.append(line);
	        response.append('\r');
	      }
	      rd.close();
	      return response.toString();

	    } catch (Exception e) {

	      e.printStackTrace();
	      return null;

	    } finally {

	      if(connection != null) {
	        connection.disconnect(); 
	      }
	    }
	  }
	
	//isso aqui vai pro main do server de verdade
	public static void main(String[] args) {
		ServerRequestHandler server;
		try {
			server = new ServerRequestHandler();
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
