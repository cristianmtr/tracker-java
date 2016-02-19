package tracker_java;

import org.aeonbits.owner.ConfigFactory;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import tracker_java.Utilities.ServerConfig;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class.
 */
public class MainServer {
    // Base URI the Grizzly HTTP server will listen on
    private static final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private static final URI BASE_URI = URI.create("http://" + cfg.hostname() + ":"+cfg.port()+"/");

    public static void main(String[] args) {
        try {
            System.out.println("task tracker enterprise Edition running");

            System.out.format("wadl available at %sapplication.wadl%n", BASE_URI);

            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, createApp(), false);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                server.shutdownNow();
            }));
            server.start();

            System.out.println(String.format("Application started.%nStop the application using CTRL+C"));

            Thread.currentThread().join();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ResourceConfig createApp() {
        return new Configuration();
    }
}


