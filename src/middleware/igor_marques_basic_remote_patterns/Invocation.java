package middleware.igor_marques_basic_remote_patterns;

import java.util.ArrayList;

public class Invocation implements ISerializable{
	//AbsoluteObjectReference AOR;
	String objectClass;
	String objectID;
	//outras coisas de AOR

	String method;
	ArrayList<String> params;
	
	
	public Invocation(String object, String objectID, String method,
			String[] params) {
		
	}

	@Override	
	public Message serialize() {
		// TODO fazer a parada de transformar os dados numa msg bonitinha
		return null;
	}

}
