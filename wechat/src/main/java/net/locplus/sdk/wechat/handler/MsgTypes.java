package net.locplus.sdk.wechat.handler;

/**
 * Created by Administrator on 2014/4/22.
 */
public enum MsgTypes {
    TEXT("Text"),
    IMAGE("Image"),
    VOICE("Voice"),
    VIDEO("Video"),
    LOCATION("Location"),
    LINK("Link"),

    LOCATION_EVENT("LocationEvent"),
    MENU_EVENT("MenuEvent"),
    SCAN_EVENT("ScanEvent");

    private String type;

    MsgTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
