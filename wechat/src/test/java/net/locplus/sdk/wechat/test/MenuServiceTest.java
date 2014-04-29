package net.locplus.sdk.wechat.test;

import net.locplus.sdk.wechat.common.model.*;
import net.locplus.sdk.wechat.common.service.MenuService;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dings_000 on 2014/4/27.
 */
public class MenuServiceTest {

    @Test
    public void testCreateMenu() {
        ClickMenuButton menuButton1 = new ClickMenuButton("设备列表", "DEVICE_LIST");
        ViewMenuButton menuButton2 = new ViewMenuButton("访问网页", "http://ncui.bblbs.net");

//        ViewMenuButton subMenuButton1 = new ViewMenuButton("绑定账户", "http://wx.bbtec.biz/resources/index.html");
        MenuButton subMenuButton1 = new ClickMenuButton("绑定账户", "BIND_USER");
        MenuButton subMenuButton2 = new ClickMenuButton("解除绑定", "UNBIND_USER");
        MenuButton subMenuButton3 = new ClickMenuButton("获取帮助", "GET_HELP");
        List<MenuButton> subMenuButtons = Arrays.asList(subMenuButton1, subMenuButton2, subMenuButton3);
        SubMenuButton subMenuButton = new SubMenuButton("更多", subMenuButtons);
        Menu menu = new Menu(Arrays.asList(menuButton1, menuButton2, subMenuButton));

        MenuService.getInstance().createMenu(menu);
    }

    @Test
    public void testGetMenu() {
        Menu menu = MenuService.getInstance().getMenu();
    }

    public void testDeleteMenu() {

    }
}
