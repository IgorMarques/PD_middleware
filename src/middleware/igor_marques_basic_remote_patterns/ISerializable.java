package middleware.igor_marques_basic_remote_patterns;


public interface ISerializable<T> {
	public Message serialize();
	
	public T demarshall();

}
