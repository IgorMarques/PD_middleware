package middleware.igor_marques_basic_remote_patterns.network;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;

import javax.xml.soap.SOAPMessage;


public class TCPConnection extends Connection {

	private Socket socket;

	private BufferedReader in;
	private BufferedWriter out;

	private boolean listening = false;

	public TCPConnection(Socket socket) {
		super(socket.getLocalAddress().getHostAddress(), socket.getPort());
		this.socket = socket; 

		try {
			socket.setSoTimeout(300);
		} catch (SocketException e) {
		}
	}

	@Override
	public void openConnection() {
		try {
			listening = true;
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
		}		
	}

	@Override
	public void sendMessage(SOAPMessage message) {

	}

	@Override
	public void listen() {
		
	}

	@Override
	public void closeConnection() {
		try {
			listening = false;
			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
		}
	}

}