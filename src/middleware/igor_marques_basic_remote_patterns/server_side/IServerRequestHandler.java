package middleware.igor_marques_basic_remote_patterns.server_side;


public interface IServerRequestHandler {
	
	public void sendMessage();
	
	public void receiveMessage();
	

	public void listenConnections();
	
}

//public void openConnection();
//
//public void closeConnection(Connection connection);
//
//public void listenConnections();