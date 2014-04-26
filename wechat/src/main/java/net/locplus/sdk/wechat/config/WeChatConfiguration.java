package net.locplus.sdk.wechat.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2014/4/22.
 */
public class WeChatConfiguration {

    public static String MESSAGE_PROCESSING_HANDLER;

    public static String TOKEN;

    public static String APPID;

    public static String SECRET;

    static {
        InputStream in = null;
        Properties properties = new Properties();
        try {
            in = WeChatConfiguration.class.getClassLoader().getResourceAsStream("wechat.properties");
            properties.load(in);
            MESSAGE_PROCESSING_HANDLER = properties.getProperty("MESSAGE_PROCESSING_HANDLER");
            TOKEN = properties.getProperty("TOKEN");
            APPID = properties.getProperty("APPID");
            SECRET = properties.getProperty("SECRET");
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
