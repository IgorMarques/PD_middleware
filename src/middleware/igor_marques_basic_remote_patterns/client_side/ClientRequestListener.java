package middleware.igor_marques_basic_remote_patterns.client_side;

import middleware.igor_marques_basic_remote_patterns.Message;

public interface ClientRequestListener {
	public void messageReceived(Message message);
}
