package middleware.igor_marques_basic_remote_patterns;

import java.util.ArrayList;
import java.util.HashMap;


public class Invocation extends Message{

	//AbsoluteObjectReference AOR;
	//outras coisas de AOR
	
	private HashMap<String, Object> additionalParams = new HashMap<String, Object>();

	public Invocation(Message message) {
		super(message);
	}

	public void setAdditionalParams(String key, Object value){
		additionalParams.put(key, value);
	}
	

}
