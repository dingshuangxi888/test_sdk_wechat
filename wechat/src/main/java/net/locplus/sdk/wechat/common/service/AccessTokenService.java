package net.locplus.sdk.wechat.common.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.locplus.sdk.wechat.common.model.AccessToken;
import net.locplus.sdk.wechat.config.WeChatConfiguration;
import net.locplus.sdk.wechat.util.HttpUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Dean on 2014/4/26.
 */
public class AccessTokenService {

    private final String ACCESS_TOKEN_UEL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    private volatile FutureTask<AccessToken> accessTask;

    private AccessTokenService() {
    }

    public static AccessTokenService getInstance() {

        return AccessTokenServiceSingletonHolder.instance;
    }

    public String getAccessToken() {

        AccessToken accessToken = null;
        try {
            if (accessTask == null || accessTask.get() == null || accessTask.get().isExpires()) {
                accessTask = new FutureTask<AccessToken>(new AccessTokenCall<AccessToken>());
                accessTask.run();
            }
            accessToken = accessTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return accessToken.getAccessToken();
    }

    private static class AccessTokenServiceSingletonHolder {
        private static AccessTokenService instance = new AccessTokenService();
    }

    private class AccessTokenCall<V> implements Callable<V> {
        @Override
        public V call() throws Exception {
            AccessToken result = null;

            String url = String.format(ACCESS_TOKEN_UEL, WeChatConfiguration.APPID, WeChatConfiguration.SECRET);
            String str = HttpUtil.getHttpsInstance().get(url);
            JSONObject jsonObject = JSON.parseObject(str);
            if (!jsonObject.containsKey("errcode")) {
                String access_token = jsonObject.getString("access_token");
                int expires_in = jsonObject.getIntValue("expires_in");
                result = new AccessToken(access_token, expires_in);
            }

            return (V) result;
        }
    }
}
