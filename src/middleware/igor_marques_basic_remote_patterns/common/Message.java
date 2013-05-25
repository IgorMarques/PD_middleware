package middleware.igor_marques_basic_remote_patterns.common;

import java.util.HashMap;
import java.util.Set;

public class Message{

	public String object;
	public String objectID;
	public String method;
	//public String[] params;
	
	private HashMap<String, Object> params = new HashMap<String, Object>();
	
	public Message(String object, String objectID, String method, String... params){
		this.object= object;
		this.objectID = objectID;
		this.method = method;
		this.params = params;
		
	}
	
	public Message(Message message) {
		this.object= message.object;
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
