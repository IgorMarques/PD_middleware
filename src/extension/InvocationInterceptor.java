package extension;

import middleware.igor_marques_basic_remote_patterns.InvocationData;

public interface InvocationInterceptor {
	public void beforeInvocation(InvocationData invocation) throws Exception;
	public void afterInvocation(InvocationData invocation) throws Exception;
}
