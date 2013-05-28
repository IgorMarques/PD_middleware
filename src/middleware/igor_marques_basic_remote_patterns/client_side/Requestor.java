package middleware.igor_marques_basic_remote_patterns.client_side;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import middleware.igor_marques_basic_remote_patterns.Invocation;
import middleware.igor_marques_basic_remote_patterns.InvocationContext;
import middleware.igor_marques_basic_remote_patterns.Marshaller;
import middleware.igor_marques_basic_remote_patterns.Message;
import middleware.igor_marques_basic_remote_patterns.client_side.SOAPClientRequestHandler;
import extension.InvocationInterceptor;
import extended_infraestructure.IQoSObserver;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class Requestor {
	
	//mudar para pegar de um arquivo de configuracao
	static String protocol = "SOAP";
	Class<?> protocolClass = SOAPClientRequestHandler.class;
	
	private static Requestor instance = null;
	static Marshaller marshaller = Marshaller.getInstance();
	
	static ClientRequestHandlerFactory fac = new ClientRequestHandlerFactory();
	
	private static ArrayList<InvocationInterceptor> interceptors = new ArrayList<InvocationInterceptor>();
	
	private static ArrayList<IQoSObserver> qosObserver = new ArrayList<IQoSObserver>();
	
	private Requestor() {
		fac.registerClass(protocol, protocolClass); 
	};

	public static Requestor getInstance() {
		if (instance == null) {
			instance = new Requestor();
		}
		return instance;
	}

	public static void invoke(String object, String objectID, String method, HashMap<String, Object> params) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SOAPException {
		
		for(IQoSObserver iqs : qosObserver)
			iqs.callStarted();

		Message msg = new Message(object, objectID, method, params);
			
		for (InvocationInterceptor ii : interceptors)
			ii.intercept(invocation);
					
		IClientRequestHandler clientHandler = fac.getImplementation(protocol);
		
		clientHandler.sendMessage(invocation, null); //FALTA BOTAR O ENDPOINT
		
		for(IQoSObserver iqs : qosObserver)
			iqs.callFinished();
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

