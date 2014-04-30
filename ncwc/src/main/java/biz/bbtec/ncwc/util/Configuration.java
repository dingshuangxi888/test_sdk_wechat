package biz.bbtec.ncwc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2014/4/29.
 */
public class Configuration {

    public static String URL_NCWS;
    public static String MEMCACHED_SERVERS;
    public static int DEVICE_LIST_PAGE_SIZE;

    static {
        InputStream in = null;
        Properties properties = new Properties();
        try {
            in = Configuration.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(in);
            URL_NCWS = properties.getProperty("URL_NCWS");
            MEMCACHED_SERVERS = properties.getProperty("MEMCACHED_SERVERS");
            DEVICE_LIST_PAGE_SIZE = Integer.parseInt(properties.getProperty("DEVICE_LIST_PAGE_SIZE"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
