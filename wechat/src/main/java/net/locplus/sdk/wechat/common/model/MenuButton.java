package net.locplus.sdk.wechat.common.model;

/**
 * Created by dings_000 on 2014/4/27.
 */
public class MenuButton {

    private String name;

    private String type;

    public MenuButton() {
    }

    public MenuButton(String name) {
        this.name = name;
    }

    public MenuButton(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
