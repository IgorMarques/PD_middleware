package middleware.igor_marques_basic_remote_patterns.server;

import java.util.ArrayList;

import middleware.igor_marques_basic_remote_patterns.common.InvocationContext;
import middleware.josiel_extension_patterns.InvocationInterceptor;
import middleware.raul_extended_infraestructure_patterns.IQoSObserver;

<<<<<<< HEAD
public class Invoker{
=======
public class Invoker {
>>>>>>> 1f7b9de1b4d60e1f870f1899a389d62e46bbc0c2
	
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
