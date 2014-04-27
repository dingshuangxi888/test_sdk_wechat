package net.locplus.sdk.wechat.common.model;

/**
 * Created by Dean on 2014/4/27.
 */
public class ClickMenuButton extends MenuButton {

    private String key;

    public ClickMenuButton() {
    }

    public ClickMenuButton(String name, String key) {
        super(name, "click");
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
