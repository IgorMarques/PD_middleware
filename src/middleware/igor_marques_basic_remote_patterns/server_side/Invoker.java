package middleware.igor_marques_basic_remote_patterns.server_side;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import middleware.igor_marques_basic_remote_patterns.InvocationContext;
import extension.InvocationInterceptor;
import extended_infraestructure.IQoSObserver;


public class Invoker {

	private ArrayList<InvocationInterceptor> interceptors = new ArrayList<InvocationInterceptor>();
	
	private ArrayList<IQoSObserver> qosObserver = new ArrayList<IQoSObserver>();
	
	public void invoke(InvocationContext invocation){
		for (InvocationInterceptor ii : interceptors)
			ii.intercept(invocation);
		
		for(IQoSObserver iqs : qosObserver)
			iqs.callStarted();
		
		
		//TODO invocacao marota
		
		for(IQoSObserver iqs : qosObserver)
			iqs.callFinished();
	}
}
