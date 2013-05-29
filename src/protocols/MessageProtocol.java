package protocols;

import java.io.IOException;

import middleware.igor_marques_basic_remote_patterns.InvocationData;
import middleware.igor_marques_basic_remote_patterns.client_side.ClientRequestHandler;

public abstract class MessageProtocol {
	
	private ClientRequestHandler clientRequestHandler;
	
	public MessageProtocol(ClientRequestHandler handler) {
		this.clientRequestHandler = handler;
	}
	
	
	public abstract String sendMessage(String endpoint, InvocationData invocation) throws IOException;
}
