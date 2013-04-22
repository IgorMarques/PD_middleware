package middleware.igor_marques_basic_remote_patterns;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Requestor {
	
	//pegar de um arquivo de configuracao
	static String protocol = "SOAP";
	Class<?> protocolClass = SOAPClientRequestHandler.class;
	
	private static Requestor instance = null;
	static Marshaller marshaller = Marshaller.getInstance();
	
	static ClientRequestHandlerFactory fac = new ClientRequestHandlerFactory();

	private Requestor() {
		fac.registerClass(protocol, protocolClass); 
	};

	public static Requestor getInstance() {
		if (instance == null) {
			instance = new Requestor();
		}
		return instance;
	}

	public static void invoke(String object, String objectID, String method, String... params) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Invocation invocation = new Invocation(object, objectID, method, params);
		Message message = marshaller.marshall(invocation);
		IClientRequestHandler clientHandler = fac.getImplementation(protocol);
		
		clientHandler.sendMessage(message);
	}

}


// SOAPMessage message = null;
//
// try {
// message = MessageFactory.newInstance().createMessage();
// } catch (SOAPException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
//
// try {
// message = Marshaller.marshall(object,objectID, method, params);
// } catch (SOAPException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
//
// clientHandler.sendMessage(message);