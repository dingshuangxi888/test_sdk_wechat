package net.locplus.sdk.wechat.common.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.locplus.sdk.wechat.common.model.AccessToken;
import net.locplus.sdk.wechat.common.model.Menu;
import net.locplus.sdk.wechat.config.WeChatConfiguration;
import net.locplus.sdk.wechat.util.HttpUtil;

/**
 * Created by dings_000 on 2014/4/27.
 */
public class MenuService {

    private String URL_MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
    private String URL_MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%s";
    private String URL_MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s";

    private MenuService() {}

    public static MenuService getInstance() {
        return MenuServiceSingletonHolder.instance;
    }

    public String createMenu(Menu menu) {
        String url = String.format(URL_MENU_CREATE, AccessTokenService.getInstance().getAccessToken());
        String json = JSON.toJSONString(menu);
        return HttpUtil.getHttpsInstance().post(url, json);
    }

    public Menu getMenu() {
        String url = String.format(URL_MENU_GET, AccessTokenService.getInstance().getAccessToken());
        String response = HttpUtil.getHttpsInstance().get(url);
        JSONObject json = JSON.parseObject(response);
        Menu menu = JSON.parseObject(json.getString("menu"), Menu.class);
        return menu;
    }

    public String deleteMenu() {
        String url = String.format(URL_MENU_DELETE, AccessTokenService.getInstance().getAccessToken());
        return HttpUtil.getHttpsInstance().get(url);
    }

    private static class MenuServiceSingletonHolder {
        private static MenuService instance = new MenuService();
    }
}
