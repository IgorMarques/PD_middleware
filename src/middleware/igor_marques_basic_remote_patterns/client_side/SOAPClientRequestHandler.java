package middleware.igor_marques_basic_remote_patterns.client_side;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeader;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import middleware.igor_marques_basic_remote_patterns.Message;
import middleware.igor_marques_basic_remote_patterns.client_side.AbstractClientRequestHandler;

public class SOAPClientRequestHandler extends AbstractClientRequestHandler{

	private String namespaceURI;
	
	public SOAPClientRequestHandler(String namespaceURI) {
		this.namespaceURI = namespaceURI;
	}
	
	@Override
	public void listenMessage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMessage(StringBuilder message, String method, String endpoint){
		InputStream is = new ByteArrayInputStream(message.toString().getBytes());
		SOAPMessage soapMessage =null;
		
		try {
			soapMessage =  MessageFactory.newInstance().createMessage(null, is);
			MimeHeaders mimeHeader = soapMessage.getMimeHeaders();
			
			mimeHeader.setHeader("SOAPAction", namespaceURI + "/"+ method);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		//criando conexao 
	     SOAPConnectionFactory sfc=null;
		try {
			sfc = SOAPConnectionFactory.newInstance();
		} catch (UnsupportedOperationException | SOAPException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	     SOAPConnection connection=null;
		try {
			connection = sfc.createConnection();
		} catch (SOAPException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     
		//Enviando mensagem
	     SOAPMessage response = null;
		try {
			response = connection.call(soapMessage, endpoint);
		} catch (SOAPException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	     
		 try {
			try {
				response.writeTo(System.out);
			} catch (SOAPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
//	public void NAOFUNCIONASendMessage(Message message, URL endpoint) throws SOAPException {
//		
//		//criando conexao 
//	     SOAPConnectionFactory sfc = SOAPConnectionFactory.newInstance();
//	     SOAPConnection connection = sfc.createConnection();
//	      
//		//criando mensagem		
//		SOAPMessage soapMessage = null;
//		
//		soapMessage = MessageFactory.newInstance().createMessage();	
//			
////		SOAPPart soapPart = soapMessage.getSOAPPart();
////		SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
////		SOAPHeader header = soapEnvelope.getHeader();	
////		SOAPBody soapBody = soapEnvelope.getBody();
//		
//		//soapEnvelope.addNamespaceDeclaration("ns1", "http://tempuri.org/");
//		
//		//header
//
//		//MimeHeaders mimeHeader = soapMessage.getMimeHeaders();
//	
//		//mimeHeader.setHeader("SOAPAction", namespaceURI + message.method);
//		
//		
//		StringBuilder result = new StringBuilder();
//		
//		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<soap:Envelope xmlns:xsi=\"http://w3.org/2001/XMLSchema-instance\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">"
//		        + "<soap:Header>"
//		        + "</soap:Header>\n");
//		
//		result.append("<soap:Body>\n");
//		
//		result.append("<" + message.method + " xmlns=\"" + namespaceURI + "\">\n");
//		
//		for(String p:message.getParamsName()){
//			result.append("<" + p + ">" + message.getParam(p) + "</" + p + ">\n");
//		}
//		
//		result.append("</" + message.method + ">\n");
//		result.append("</soap:Body></soap:Envelope>");
//
//		
//		InputStream is = new ByteArrayInputStream(result.toString().getBytes());
//		try {
//			soapMessage = MessageFactory.newInstance().createMessage(null, is);
//			MimeHeaders mimeHeader = soapMessage.getMimeHeaders();
//			
//			mimeHeader.setHeader("SOAPAction", namespaceURI + "/"+ message.method);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 
//		//Enviando mensagem
//	     SOAPMessage response = connection.call(soapMessage, endpoint);		
//	     
//		 try {
//			response.writeTo(System.out);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    
//		
//	}


	
}
