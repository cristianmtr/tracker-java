package tracker_java;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.*;
import tracker_java.Controllers.*;

public class MainServer {

	public static void main(String[] args)  {
		HttpServer server = null;
		try {
			server = HttpServer.create(new InetSocketAddress("localhost", 8000), 100);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ExecutorService excu = Executors.newCachedThreadPool();
		server.setExecutor(excu);

//		server.createContext("/", new rootHandler());
//		server.createContext("/static", new staticHandler());
		server.createContext("/json", new jsonHandler());
		server.createContext("/task", new taskEndpointHandler());


		server.start();
		System.out.println("server started");
	}

}
