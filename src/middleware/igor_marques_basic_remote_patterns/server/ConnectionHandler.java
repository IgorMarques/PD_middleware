package middleware.igor_marques_basic_remote_patterns.server;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class ConnectionHandler implements HttpHandler{
	
	private ConnectionListener connectionListener;
	private ArrayList<Connection> connections;	
	
	public ConnectionHandler() {
		connections = new ArrayList<Connection>();
	}

	public void addConnection(Connection conn) {
		connections.add(conn);
	}

	public void removeConnection(Connection conn) {
		connections.remove(conn);
	}

	public ArrayList<Connection> getConnections() {
		return connections;
	}

	public ConnectionListener getConnectionListener() {
		return connectionListener;
	}

	public void setConnectionListener(ConnectionListener connectionListener) {
		this.connectionListener = connectionListener;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		exchange.getRequestMethod();
	}
	

}
