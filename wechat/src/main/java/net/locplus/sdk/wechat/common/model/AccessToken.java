package net.locplus.sdk.wechat.common.model;

/**
 * Created by Dean on 2014/4/26.
 */
public class AccessToken {

    private String accessToken;
    private int expiresIn;
    private long startTime;

    public AccessToken(String accessToken, int expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.startTime = System.currentTimeMillis();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public boolean isExpires() {
        return System.currentTimeMillis() - startTime > expiresIn * 1000;
    }
}
