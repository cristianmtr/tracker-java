package tracker_java.Utilities;

import org.aeonbits.owner.Config;

@Config.Sources({"file:~/.tracker.ini"})
public interface ServerConfig extends Config {
    @Key("server.http.port")
    @DefaultValue("8000")
    int port();

    @Key("server.host.name")
    @DefaultValue("localhost")
    String hostname();
}
