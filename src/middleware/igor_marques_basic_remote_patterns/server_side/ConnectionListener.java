package middleware.igor_marques_basic_remote_patterns.server_side;

public interface ConnectionListener {
	public void newConnection(Connection connection);
	public void connectionClosed(Connection connection);

}
