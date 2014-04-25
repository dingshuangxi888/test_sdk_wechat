package net.locplus.sdk.wechat.config;

import net.locplus.sdk.wechat.handler.MessageProcessingHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2014/4/22.
 */
public class WeChatConfiguration {

    public static String MESSAGE_PROCESSING_HANDLER;

    static {
        InputStream in = null;
        Properties properties = new Properties();
        try {
            in = WeChatConfiguration.class.getClassLoader().getResourceAsStream("wechat.properties");
            properties.load(in);
            MESSAGE_PROCESSING_HANDLER = properties.getProperty("MESSAGE_PROCESSING_HANDLER");
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
