package net.locplus.sdk.wechat.common.model;

/**
 * Created by Dean on 2014/4/27.
 */
public class ViewMenuButton extends MenuButton {

    private String url;

    public ViewMenuButton() {
    }

    public ViewMenuButton(String name, String url) {
        super(name, "view");
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
