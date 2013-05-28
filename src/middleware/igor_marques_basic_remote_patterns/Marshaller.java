package middleware.igor_marques_basic_remote_patterns;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import middleware.igor_marques_basic_remote_patterns.Message;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;


public class Marshaller{
	
	private static Marshaller instance = null;
	
	protected Marshaller(){};
	
	public synchronized static Marshaller getInstance(){
		if (instance==null){
			instance = new Marshaller();
		}
		return instance;
	}
	
	public StringBuilder marshall(Invocation message, String namespaceURI) throws SOAPException{
	
		SOAPMessage soapMessage = null;
					
		soapMessage = MessageFactory.newInstance().createMessage();			
		
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
		
		return result;
	}
}

//	

//protected static SOAPMessage marshall(String object, String objectID, String method, ArrayList<String> params) throws SOAPException{
//	return null;
//} 
//	SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
//	
//    SOAPPart soapPart = soapMessage.getSOAPPart();
//    SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
//    SOAPBody soapBody = soapEnvelope.getBody();
//    
//    //Adicionando objeto
//    
//    Name bodyName = soapEnvelope.createName("Object");
//    
//    SOAPBodyElement requestedObject = soapBody.addBodyElement(bodyName);
//    
//    requestedObject.addTextNode(object);
//    
//    //Adicionando ID do objeto
//    
//    bodyName = soapEnvelope.createName("ObjectID");
//    
//    SOAPBodyElement requestedObjectID = soapBody.addBodyElement(bodyName);
//    
//    requestedObjectID.addTextNode(objectID);
//    
//    //Adicionando m?todo
//    
//    bodyName = soapEnvelope.createName("Method");
//    
//    SOAPBodyElement requestedMethod = soapBody.addBodyElement(bodyName);
//    
//    requestedMethod.addTextNode(method);
//    
//    //Adicionando par?metros
//    
//    bodyName= soapEnvelope.createName("Params");
//    
//    SOAPBodyElement requestedParams = soapBody.addBodyElement(bodyName);
//    
//    for(String p:params){
//    	requestedParams.addTextNode(p);
//    }
//    
//	return soapMessage;
