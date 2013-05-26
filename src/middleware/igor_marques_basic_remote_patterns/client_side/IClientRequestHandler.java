package middleware.igor_marques_basic_remote_patterns.client_side;

import java.net.URL;

import javax.xml.soap.SOAPException;

import middleware.igor_marques_basic_remote_patterns.Message;

public interface IClientRequestHandler {
	
	public void sendMessage(Message message, URL endpoint) throws SOAPException;
	
	public void listenMessage();
}