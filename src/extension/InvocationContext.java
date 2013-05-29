package extension;

import java.util.HashMap;

import middleware.igor_marques_basic_remote_patterns.InvocationData;

public class InvocationContext extends InvocationData {
	private HashMap<String, Object> additionalParams;
	
	public InvocationContext(String id, String method, HashMap<String, Object> params) {
		super(id, method, params);
		additionalParams = new HashMap<String, Object>();
	}
	
	public InvocationContext(InvocationData inv) {
		super(inv);
		additionalParams = new HashMap<String, Object>();
	}
	
	public HashMap<String, Object> getAdditionalParams() {
		return additionalParams;
	}
}
