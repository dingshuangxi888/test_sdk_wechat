package biz.bbtec.ncwc.service.ncws.impl;

import biz.bbtec.ncwc.service.ncws.LoginService;
import biz.bbtec.ncwc.util.NCWS_URL;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bbtech.ncws.JSONFormatter;
import com.bbtech.ncws.JSONParser;
import com.bbtech.ncws.UserLogin;
import net.locplus.sdk.wechat.util.HttpUtil;
import org.json.JSONException;
import org.springframework.stereotype.Service;

/**
 * Created by Dean on 2014/4/29.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public UserLogin login(String username, String passwd) {
        UserLogin result = null;
        try {
            String req = JSONFormatter.formatUserLogin(username, passwd);
            String resp = HttpUtil.getHttpInstance().post(NCWS_URL.USER_LOGIN, req);
            result = JSONParser.parseUserLogin(resp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
