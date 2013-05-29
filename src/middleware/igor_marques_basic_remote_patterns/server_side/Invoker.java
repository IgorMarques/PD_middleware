package middleware.igor_marques_basic_remote_patterns.server_side;

import java.lang.reflect.Method;
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
		
		//pega o objeto da pool
		AbstractRemoteObject aro = objectPool.getObject(invocation.getObjectID());
		
		//pega a assinatura do metodo
		Class<?>[] methodSignature = new Class<?>[invocation.getParamsType().length];
		
		try {
			//pega a assinatura do metodo de acordo com a invocation
			for (int i = 0; i < methodSignature.length; i++) {
				methodSignature[i] = Class.forName(invocation.getParamsType()[i]);
			}
			
			//pega o metodo
			Method method = aro.getClass().getMethod(invocation.getMethod(), methodSignature);

			
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		
		
		for(IQoSObserver iqs : qosObserver)
			iqs.callFinished();
		
		try {
			interceptors.afterInvocation(invocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
