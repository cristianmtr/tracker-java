package tracker_java;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.*;

public class MainServer {

	public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8000), 100);
		
		server.createContext("/", new rootHandler());
		server.createContext("/static", new staticHandler());


		server.start();
		System.out.println("server started");
	}

}
