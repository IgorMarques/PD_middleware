package middleware.igor_marques_basic_remote_patterns.client_side;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import middleware.igor_marques_basic_remote_patterns.InvocationData;
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

	public void invoke(String object, String objectID, String method, HashMap<String, Object> params) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SOAPException {
		
		String namespaceURI ="";
		
		//for(IQoSObserver iqs : qosObserver)
		//	iqs.callStarted();

		Message msg = new Message(object, objectID, method, params);
			
		InvocationData invocation = new InvocationData(msg);
		
		//for (InvocationInterceptor ii : interceptors)
		//	ii.intercept(invocation);
					
		IClientRequestHandler clientHandler = fac.getImplementation(protocol);
		
		StringBuilder message = marshaller.marshall(invocation, namespaceURI);
		
		clientHandler.sendMessage(message, method, "127.0.0.1"); //FALTA BOTAR O ENDPOINT
		
		for(IQoSObserver iqs : qosObserver)
			iqs.callFinished();
	}
	
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SOAPException {
		Requestor requestor = Requestor.getInstance();
		
		HashMap<String, Object> params = null;
		
		
		requestor.invoke("a", "b", protocol, params);
	}
}

