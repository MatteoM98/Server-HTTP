package it.uniupo.matteo.magri.http;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class HTTPServer {

	public static void main(String[] args) {
		int port = 9000;
		HttpServer server = null;
		try {
			server = HttpServer.create(new InetSocketAddress(port),0);
		}catch (IOException e)  {
			System.out.println("Error creating server");
			System.exit(0);
		}
		System.out.println("Server started at port: " + port);
		server.createContext("/",new RootHandler(port));
		server.createContext("/echoHeader",new EchoHeaderHandler());
		server.createContext("/echoGet",new EchoGetHandler());
		server.createContext("/echoPost",new EchoPostHandler());
		server.setExecutor(null);
		server.start();
	}
}

