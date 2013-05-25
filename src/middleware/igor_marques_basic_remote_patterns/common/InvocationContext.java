package middleware.igor_marques_basic_remote_patterns.common;

import java.util.ArrayList;
import java.util.HashMap;

public class InvocationContext implements ISerializable {
	//AbsoluteObjectReference AOR;
	String objectClass;
	String objectID;
	//outras coisas de AOR

	String method;
	
	public InvocationContext(String object, String objectID, String method) {
		
	}

	@Override
	public Message serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object demarshall() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setParam(String string, String string2) {
		// TODO Auto-generated method stub
		
	}

}
