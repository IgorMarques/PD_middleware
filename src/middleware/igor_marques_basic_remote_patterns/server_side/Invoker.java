package middleware.igor_marques_basic_remote_patterns.server_side;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import lifecycle.RemoteObjectPool;
import middleware.igor_marques_basic_remote_patterns.InvocationData;
import extended_infraestructure.IQoSObserver;
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
		
		Library lib = new Library();
		objectPool.registerObject("library", lib);
		
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

			System.out.println(Arrays.toString(methodSignature));
			
			Object[] params = new Object[invocation.getParamsName().size()];
			int i = 0;
			
			for (String ob: invocation.getParamsName()) {
				params[i++] = invocation.getParam(ob);
			}
			
			method.invoke(aro, params);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
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
	
	public static void main(String[] args) {
		Invoker invoker = new Invoker();
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("hue", "br");
		
		invoker.invoke(new InvocationData("library", "potato", params, String.class.getName()));
	}
	
	private class Library extends AbstractRemoteObject {
		public void potato(String hue) {
			System.out.println("Metodo potato: " + hue);
		}
	}
}
