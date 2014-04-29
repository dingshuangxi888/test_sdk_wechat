package biz.bbtec.ncwc.util;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

import java.io.IOException;

/**
 * Created by Dean on 2014/4/29.
 */
public class MemcachedUtil {

    private MemcachedClient memcachedClient = null;

    private MemcachedUtil() {
        try {
            memcachedClient = new MemcachedClient(AddrUtil.getAddresses(Configuration.MEMCACHED_SERVERS));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MemcachedUtil getInstance() {
        return MemcachedUtilSingletonHolder.instance;
    }

    public void set(String key, Object value, int exp) {
        memcachedClient.set(key, exp, value);
    }
    public Object get(String key) {
        return memcachedClient.get(key);
    }

    public void remove(String key) {
        memcachedClient.delete(key);
    }

    private static class MemcachedUtilSingletonHolder {
        private static MemcachedUtil instance = new MemcachedUtil();
    }
}
