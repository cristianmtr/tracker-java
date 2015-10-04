package tracker_java;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

import com.sun.net.httpserver.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MainServer {

	public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8000), 100);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		server.createContext("/", new rootHandler());
		server.createContext("/static", new staticHandler());
		server.createContext("/json", new jsonHandler());


		server.start();
		System.out.println("server started");
	}

}
