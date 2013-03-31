package middleware.igor_marques_basic_remote_patterns.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.xml.soap.SOAPMessage;

public abstract class Connection {

	private InetAddress host;
	private int port;


	public Connection(String host, int port) {
		try {
			this.host = InetAddress.getByName(host);
			this.port = port;
		} catch (UnknownHostException e) {
		}
	}

	public abstract void openConnection();
	public abstract void sendMessage(SOAPMessage message);
	public abstract void listen();
	public abstract void closeConnection();

	public InetAddress getHost() {
		return host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public boolean equals(Connection conn) {
		return conn.getHost().toString().equals(this.getHost().toString()) && conn.getPort() == this.getPort();
	}
}