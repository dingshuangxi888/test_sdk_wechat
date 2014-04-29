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
        MenuButton subMenuButton11 = new ClickMenuButton("获取列表", "GET_LIST");
        MenuButton subMenuButton12 = new ClickMenuButton("更多列表", "MORE_LIST");
        List<MenuButton> subMenuButtonList1 = Arrays.asList(subMenuButton11, subMenuButton12);
        SubMenuButton subMenuButton1 = new SubMenuButton("设备列表", subMenuButtonList1);

        ViewMenuButton menuButton2 = new ViewMenuButton("访问网页", "http://ncui.bblbs.net");

        MenuButton subMenuButton31 = new ClickMenuButton("绑定账户", "BIND_USER");
        MenuButton subMenuButton32 = new ClickMenuButton("解除绑定", "UNBIND_USER");
        MenuButton subMenuButton33 = new ClickMenuButton("获取帮助", "GET_HELP");
        List<MenuButton> subMenuButtonList3 = Arrays.asList(subMenuButton31, subMenuButton32, subMenuButton33);
        SubMenuButton subMenuButton3 = new SubMenuButton("更多", subMenuButtonList3);
        Menu menu = new Menu(Arrays.asList(subMenuButton1, menuButton2, subMenuButton3));

        MenuService.getInstance().createMenu(menu);
    }

    @Test
    public void testGetMenu() {
        Menu menu = MenuService.getInstance().getMenu();
    }

    public void testDeleteMenu() {

    }
}
