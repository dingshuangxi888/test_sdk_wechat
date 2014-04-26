package net.locplus.sdk.wechat.test;

import net.locplus.sdk.wechat.common.service.AccessTokenService;
import net.locplus.sdk.wechat.config.WeChatConfiguration;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dings_000 on 2014/4/26.
 */
public class AccessTokenServiceTest {

    @Test
    public void testAccessTokenService() throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            String result = AccessTokenService.getInstance().getAccessToken();
            System.out.println(result);
        }
    }
}
