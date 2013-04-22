package middleware.igor_marques_basic_remote_patterns;

public interface IServerRequestHandler {

	public void openConnection(Connection connection);
	
	public void closeConnection(Connection connection);
	
	public void listenConnections();
	
	public void sendMessage();
	
	public void receiveMessage();
	
}
