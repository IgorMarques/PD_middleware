package extension;

import java.util.LinkedList;

import middleware.igor_marques_basic_remote_patterns.InvocationData;


public class InvocationInterceptors {
	private static InvocationInterceptors instance;
	
	public static InvocationInterceptors getInstance() {
		if (instance == null)
			instance = new InvocationInterceptors();
		return instance;
	}
	
	private LinkedList<InvocationInterceptor> interceptors;
	
	private InvocationInterceptors() {
		interceptors = new LinkedList<InvocationInterceptor>();
	}
	
	public void addInterceptor(InvocationInterceptor inter) {
		interceptors.add(inter);
	}
	
	public void removeInterceptor(InvocationInterceptor inter) {
		interceptors.add(inter);
	}
	
	public void beforeInvocation(InvocationData invocation) throws Exception {
		for (InvocationInterceptor inter: interceptors) {
			inter.beforeInvocation(invocation);
		}
	}
	
	public void afterInvocation(InvocationData invocation) throws Exception {
		for (InvocationInterceptor inter: interceptors) {
			inter.afterInvocation(invocation);
		}
	}
}
