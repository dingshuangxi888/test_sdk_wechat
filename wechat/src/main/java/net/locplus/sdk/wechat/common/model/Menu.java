package net.locplus.sdk.wechat.common.model;

import java.util.List;

/**
 * Created by Dean on 2014/4/27.
 */
public class Menu {

    private List<MenuButton> button;

    public Menu() {
    }

    public Menu(List<MenuButton> button) {
        this.button = button;
    }

    public List<MenuButton> getButton() {
        return button;
    }

    public void setButton(List<MenuButton> button) {
        this.button = button;
    }
}
