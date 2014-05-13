package biz.bbtec.ncwc.util;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by Dean on 2014/4/29.
 */
public class MemcachedUtil {

    private static final Logger logger = LoggerFactory.getLogger(MemcachedUtil.class);

    private MemcachedClient memcachedClient = null;

    private MemcachedUtil() {
        try {
            memcachedClient = new MemcachedClient(AddrUtil.getAddresses(Configuration.MEMCACHED_SERVERS));
        } catch (IOException e) {
            logger.error("connect to memcached error: {}", e.getMessage());
        }
    }

    public static MemcachedUtil getInstance() {
        return MemcachedUtilSingletonHolder.instance;
    }

    public void set(String key, Object value, int exp) {
        logger.info("set data to memcache {}={}", key, value);
        memcachedClient.set(key, exp, value);
    }

    public Object get(String key) {
        logger.info("get data from memcached with key={}", key);
        return memcachedClient.get(key);
    }

    public void remove(String key) {
        logger.info("delete data from memcached with key={}", key);
        memcachedClient.delete(key);
    }

    private static class MemcachedUtilSingletonHolder {
        private static MemcachedUtil instance = new MemcachedUtil();
    }
}
