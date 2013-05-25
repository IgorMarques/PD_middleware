package middleware.igor_marques_basic_remote_patterns.common;

import java.util.HashMap;
import java.util.Set;

public class Message{

	public String object;
	public String objectID;
	public String method;
	
	private HashMap<String, Object> params = new HashMap<String, Object>();
	
	public Set<String> getParamsName() {
		return params.keySet();
	}
	
	public Object getParam(String name){
		return params.get(name);
	}
	
	public void setParam(String key, Object value){
		params.put(key, value);
	}
	
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
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
