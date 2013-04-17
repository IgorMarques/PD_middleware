package middleware.igor_marques_basic_remote_patterns;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import java.util.ArrayList;

public class Requestor {
	private static Requestor instance= null;
	static Marshaller marshaller = Marshaller.getInstance();
	static IClientRequestHandler clientHandler = new ClientRequestHandler();
	
	private Requestor(){ 
		
	};

	public static Requestor getInstance() {
	if(instance == null) {
		   instance = new Requestor();
		}
		return instance;
	}
	
	public static void invoke(String object, String objectID, String method, ArrayList<String> params){
//		SOAPMessage message = null;
//		
//		try {
//			message = MessageFactory.newInstance().createMessage();
//		} catch (SOAPException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			message = Marshaller.marshall(object,objectID, method, params);
//		} catch (SOAPException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		clientHandler.sendMessage(message);
	}
	
}
