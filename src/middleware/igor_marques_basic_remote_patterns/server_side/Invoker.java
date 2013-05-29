package middleware.igor_marques_basic_remote_patterns.server_side;

import java.util.ArrayList;

import lifecycle.RemoteObjectPool;
import middleware.igor_marques_basic_remote_patterns.InvocationData;
import extended_infraestructure.IQoSObserver;
import extension.InvocationInterceptor;
import extension.InvocationInterceptors;


public class Invoker {

	private RemoteObjectPool objectPool = RemoteObjectPool.getInstance();
	private InvocationInterceptors interceptors = InvocationInterceptors.getInstance();
	
	private ArrayList<IQoSObserver> qosObserver = new ArrayList<IQoSObserver>();
	
	public void invoke(InvocationData invocation){
		try {
			interceptors.beforeInvocation(invocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(IQoSObserver iqs : qosObserver)
			iqs.callStarted();
		
		
		//TODO invocacao marota
		
		for(IQoSObserver iqs : qosObserver)
			iqs.callFinished();
		
		try {
			interceptors.afterInvocation(invocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
