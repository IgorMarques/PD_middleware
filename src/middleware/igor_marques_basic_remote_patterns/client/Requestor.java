package middleware.igor_marques_basic_remote_patterns.client;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import middleware.igor_marques_basic_remote_patterns.common.InvocationContext;
import middleware.igor_marques_basic_remote_patterns.common.Marshaller;
import middleware.igor_marques_basic_remote_patterns.common.Message;
import middleware.igor_marques_basic_remote_patterns.soap.SOAPClientRequestHandler;
import middleware.josiel_extension_patterns.InvocationInterceptor;
import middleware.raul_extended_infraestructure_patterns.IQoSObserver;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

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

	public static void invoke(String object, String objectID, String method, String... params) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SOAPException {
		
		for(IQoSObserver iqs : qosObserver)
			iqs.callStarted();
		
		InvocationContext invocation = new InvocationContext(object, objectID, method);
		invocation.setParam("bolado", "huehueheuheue");
		
		for (InvocationInterceptor ii : interceptors)
			ii.intercept(invocation);
					
		IClientRequestHandler clientHandler = fac.getImplementation(protocol);
		
		Message message = new Message();
		
		message= marshaller.marshall(invocation);
		
		clientHandler.sendMessage(message, null); //FALTA BOTAR O ENDPOINT
		
		for(IQoSObserver iqs : qosObserver)
			iqs.callFinished();
	}

}