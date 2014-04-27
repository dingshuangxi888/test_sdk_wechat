package net.locplus.sdk.wechat.common.model;

import java.util.List;

/**
 * Created by Dean on 2014/4/27.
 */
public class SubMenuButton extends MenuButton {

    private List<MenuButton> sub_button;

    public SubMenuButton() {
    }

    public SubMenuButton(String name, List<MenuButton> sub_button) {
        super(name);
        this.sub_button = sub_button;
    }

    public List<MenuButton> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<MenuButton> sub_button) {
        this.sub_button = sub_button;
    }
}
