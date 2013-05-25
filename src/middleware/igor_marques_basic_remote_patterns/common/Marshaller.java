package middleware.igor_marques_basic_remote_patterns.common;

public class Marshaller{
	
	private static Marshaller instance = null;
	
	protected Marshaller(){ };
	
	public synchronized static Marshaller getInstance(){
		if (instance==null){
			instance = new Marshaller();
		}
		return instance;
	}
	
	public Message marshall(ISerializable<?> object){
		return object.serialize();
	}

	public Message demarshall(IDemarshallabe object){
		return object.demarshall();
	}

}