package middleware.igor_marques_basic_remote_patterns;

import java.util.HashMap;
import java.util.Set;

public class InvocationData {

	private String objectID;
	private String method;
	//public String[] params;
	
	private HashMap<String, Object> params = new HashMap<String, Object>();
	
	public InvocationData(String objectID, String method, HashMap<String, Object> params){
		this.objectID = objectID;
		this.method = method;
		this.params = params;
		
	}
	
	public InvocationData(InvocationData message) {
		this.objectID = message.objectID;
		this.method = message.method;
		this.params = message.params;
	}


	public Set<String> getParamsName() {
		return params.keySet();
	}
	public Object getParam(String name){
		return params.get(name);
	}
	public void setParam(String key, Object value){
		params.put(key, value);
	}
	public String getObjectID() {
		return objectID;
	}
	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}

	
	//botar invocation context

}
