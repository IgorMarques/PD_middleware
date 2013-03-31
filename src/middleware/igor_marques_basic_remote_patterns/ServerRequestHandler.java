package middleware.igor_marques_basic_remote_patterns;

public class ServerRequestHandler {
	
	private static ServerRequestHandler serverRequestHandler= new ServerRequestHandler();

	public static ServerRequestHandler getInstance() {
		return serverRequestHandler;
	}

	private ServerRequestHandler(){ };

}
