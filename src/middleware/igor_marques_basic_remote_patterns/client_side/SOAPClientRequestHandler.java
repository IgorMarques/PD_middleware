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
	public void sendMessage(Message message, URL endpoint) throws SOAPException {
		
		//criando conexao 
	     SOAPConnectionFactory sfc = SOAPConnectionFactory.newInstance();
	     SOAPConnection connection = sfc.createConnection();
	      
		//criando mensagem		
		SOAPMessage soapMessage = null;
		
		soapMessage = MessageFactory.newInstance().createMessage();	
			
//		SOAPPart soapPart = soapMessage.getSOAPPart();
//		SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
//		SOAPHeader header = soapEnvelope.getHeader();	
//		SOAPBody soapBody = soapEnvelope.getBody();
		
		//soapEnvelope.addNamespaceDeclaration("ns1", "http://tempuri.org/");
		
		//header

		//MimeHeaders mimeHeader = soapMessage.getMimeHeaders();
	
		//mimeHeader.setHeader("SOAPAction", namespaceURI + message.method);
		
		
		StringBuilder result = new StringBuilder();
		
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<soap:Envelope xmlns:xsi=\"http://w3.org/2001/XMLSchema-instance\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">"
		        + "<soap:Header>"
		        + "</soap:Header>\n");
		
		result.append("<soap:Body>\n");
		
		result.append("<" + message.method + " xmlns=\"" + namespaceURI + "\">\n");
		
		for(String p:message.getParamsName()){
			result.append("<" + p + ">" + message.getParam(p) + "</" + p + ">\n");
		}
		
		result.append("</" + message.method + ">\n");
		result.append("</soap:Body></soap:Envelope>");

		
		InputStream is = new ByteArrayInputStream(result.toString().getBytes());
		try {
			soapMessage = MessageFactory.newInstance().createMessage(null, is);
			MimeHeaders mimeHeader = soapMessage.getMimeHeaders();
			
			mimeHeader.setHeader("SOAPAction", namespaceURI + "/"+ message.method);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		//Enviando mensagem
	     SOAPMessage response = connection.call(soapMessage, endpoint);		
	     
		 try {
			response.writeTo(System.out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
	}

	@Override
	public void listenMessage() {
		// TODO Auto-generated method stub
		
	}

}

//SOAPMessage message = null;
//
//try {
//message = MessageFactory.newInstance().createMessage();
//} catch (SOAPException e) {
//// TODO Auto-generated catch block
//e.printStackTrace();
//}
//
//try {
//message = Marshaller.marshall(object,objectID, method, params);
//} catch (SOAPException e) {
//// TODO Auto-generated catch block
//e.printStackTrace();
//}
//
//clientHandler.sendMessage(message);


//protected static SOAPMessage marshall(String object, String objectID, String method, ArrayList<String> params) throws SOAPException{
//return null;
//} 
//SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
//
//SOAPPart soapPart = soapMessage.getSOAPPart();
//SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
//SOAPBody soapBody = soapEnvelope.getBody();
//
////Adicionando objeto
//
//Name bodyName = soapEnvelope.createName("Object");
//
//SOAPBodyElement requestedObject = soapBody.addBodyElement(bodyName);
//
//requestedObject.addTextNode(object);
//
////Adicionando ID do objeto
//
//bodyName = soapEnvelope.createName("ObjectID");
//
//SOAPBodyElement requestedObjectID = soapBody.addBodyElement(bodyName);
//
//requestedObjectID.addTextNode(objectID);
//
////Adicionando m?todo
//
//bodyName = soapEnvelope.createName("Method");
//
//SOAPBodyElement requestedMethod = soapBody.addBodyElement(bodyName);
//
//requestedMethod.addTextNode(method);
//
////Adicionando par?metros
//
//bodyName= soapEnvelope.createName("Params");
//
//SOAPBodyElement requestedParams = soapBody.addBodyElement(bodyName);
//
//for(String p:params){
//	requestedParams.addTextNode(p);
//}
//
//return soapMessage;

