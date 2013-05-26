package extension;

import middleware.igor_marques_basic_remote_patterns.common.Invocation;

public interface InvocationInterceptor {
	public void intercept(Invocation invocation);
}
