package extension;

import java.util.HashMap;

import middleware.igor_marques_basic_remote_patterns.InvocationData;

public class InvocationContext extends InvocationData {
	public InvocationContext(String id, String method, HashMap<String, Object> params) {
		super(id, method, params);
	}
}
