package net.locplus.sdk.wechat.handler;

/**
 * Created by Administrator on 2014/4/22.
 */
public enum MsgTypes {
    TEXT("text"),
    IMAGE("image"),
    NEWS("news"),
    VOICE("voice"),
    VIDEO("video"),
    LOCATION("location"),
    LINK("link"),

    LOCATION_EVENT("locationEvent"),
    MENU_EVENT("menuEvent"),
    SCAN_EVENT("scanEvent");

    private String type;

    MsgTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
