package middleware.igor_marques_basic_remote_patterns.common;

<<<<<<< HEAD
=======
import java.util.ArrayList;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

>>>>>>> 1f7b9de1b4d60e1f870f1899a389d62e46bbc0c2
public class Marshaller{
	
	private static Marshaller instance = null;
	
	protected Marshaller(){ };
	
	public synchronized static Marshaller getInstance(){
		if (instance==null){
			instance = new Marshaller();
		}
		return instance;
	}
	
<<<<<<< HEAD
	public Message marshall(ISerializable<?> object){
		return object.serialize();
	}

	public Message demarshall(IDemarshallabe object){
		return object.demarshall();
	}

}
=======
	public Message marshall(ISerializable object){
		return object.serialize();
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
>>>>>>> 1f7b9de1b4d60e1f870f1899a389d62e46bbc0c2
