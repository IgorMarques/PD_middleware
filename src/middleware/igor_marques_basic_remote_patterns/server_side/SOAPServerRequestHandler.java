package middleware.igor_marques_basic_remote_patterns.server_side;
import com.sun.net.httpserver.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.CharBuffer;
import java.util.ArrayList;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import middleware.igor_marques_basic_remote_patterns.common.Message;


public class SOAPServerRequestHandler implements IServerRequestHandler, HttpHandler{

	HttpServer  httpServer;  
	ConnectionHandler connectionHandler;
	
	public SOAPServerRequestHandler(){
		
	}
	
	@Override
	public void sendMessage() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void receiveMessage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listenConnections() {
		try {
			httpServer = HttpServer.create(new InetSocketAddress(8888), 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		httpServer.createContext("/test", connectionHandler);
		
		httpServer.setExecutor(null);
		
		httpServer.start();.
	}
	
//	public static Message SOAPToMessage(SOAPMessage soapMessage){
//		
//		Message message = new Message();
//		
//		SOAPPart soapPart = soapMessage.getSOAPPart();
//		SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
//		SOAPBody soapBody = soapEnvelope.getBody();
//		
////		message.setObject(soapEnvelope.getChildElements());
////		
////		message.setMethod();
////		
////		message.setParams();
//		
//		
//		//Adicionando objeto
//		
//		Name bodyName = soapEnvelope.createName("Object");
//		
//		SOAPBodyElement requestedObject = soapBody.addBodyElement(bodyName);
//		
//		requestedObject.addTextNode(message.object);
//		
//		//Adicionando ID do objeto
//		
//		bodyName = soapEnvelope.createName("ObjectID");
//		
//		SOAPBodyElement requestedObjectID = soapBody.addBodyElement(bodyName);
//		
//		requestedObjectID.addTextNode(message.objectID);
//		
//		//Adicionando metodo
//		
//		bodyName = soapEnvelope.createName("Method");
//		
//		SOAPBodyElement requestedMethod = soapBody.addBodyElement(bodyName);
//		
//		requestedMethod.addTextNode(message.method);
//		
//		//Adicionando parametros
//		
//		bodyName= soapEnvelope.createName("Params");
//		
//		SOAPBodyElement requestedParams = soapBody.addBodyElement(bodyName);
//		
//		for(String p:message.params){
//			requestedParams.addTextNode(p);
		//}
		
	//	return message;	
//}
	
	
//	private class Handler implements HttpHandler{
//
//		@Override
//		public void handle(HttpExchange exchange) throws IOException {
//			InputStreamReader isr = new InputStreamReader(exchange.getRequestBody());			
//			
//			try {
//				SOAPMessage request = MessageFactory.newInstance().createMessage(null, exchange.getRequestBody());
//				
//				//SOAPToMessage(request);
//				
//				
//			} catch (SOAPException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			isr.close();
//		}
//			
//	}
	
	public static void main(String[] args) {
		SOAPServerRequestHandler sHandler = new SOAPServerRequestHandler();
		
		sHandler.listenConnections();
	}

	@Override
	public void handle(HttpExchange request) throws IOException {
		Message message = new Message();
		
		SOAPMessage soapMessage = null;
		
		try {
			soapMessage = MessageFactory.newInstance().createMessage(null, request.getRequestBody());
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		soapMessage.		
		
	}
	
	
}