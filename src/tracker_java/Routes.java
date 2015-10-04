package tracker_java;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

import com.sun.net.httpserver.*;

public class Routes {

	public static void main(String[] argv) throws IOException {
		
		HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8000), 100);
				
		server.start();
		
		
	}

}
