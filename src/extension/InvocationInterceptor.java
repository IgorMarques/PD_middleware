package extension;

import middleware.igor_marques_basic_remote_patterns.Invocation;
import middleware.igor_marques_basic_remote_patterns.InvocationContext;

public interface InvocationInterceptor {
	public void intercept(Invocation invocation);
}
