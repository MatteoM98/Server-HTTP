package it.uniupo.matteo.magri.http;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RootHandler implements HttpHandler {

	private int port;
	
	public RootHandler(int port) {
		this.port = port;
	}
	
	@Override
	public void handle(HttpExchange he) throws IOException {
		String response = "<h1>Server started successufully!</h1>" + "<h1>Port: " + port + "</h1>";
		he.sendResponseHeaders(200,response.length());
		OutputStream os = he.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}
	

	
}
