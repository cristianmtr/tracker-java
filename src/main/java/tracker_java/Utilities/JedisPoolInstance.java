package tracker_java.Utilities;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPool;

/**
 * Created by CristianMitroi on 12-11-2015.
 */
public class JedisPoolInstance {

    public static JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
}
