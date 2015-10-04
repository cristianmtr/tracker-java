package tracker_java;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class rootHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange arg0) throws IOException {
		arg0.sendResponseHeaders(200,"dickbut".getBytes().length);
		OutputStream res = arg0.getResponseBody();
		res.write("dickbut".getBytes());
		res.close();
		arg0.close();
		
	}

}
