package middleware.igor_marques_basic_remote_patterns;

import java.util.ArrayList;

public class Invocation implements ISerializable{
	//AbsoluteObjectReference AOR;
	String method;
	ArrayList<String> params;
	
	@Override	
	public Message serialize() {
		// TODO fazer a parada de transformar os dados numa msg bonitinha
		return null;
	}

}
