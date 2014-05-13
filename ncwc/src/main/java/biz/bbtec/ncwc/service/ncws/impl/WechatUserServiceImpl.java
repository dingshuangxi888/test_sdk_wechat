package biz.bbtec.ncwc.service.ncws.impl;

import biz.bbtec.ncwc.service.ncws.WechatUserService;
import biz.bbtec.ncwc.util.MemcachedUtil;
import biz.bbtec.ncwc.util.NCWS_URL;
import com.bbtech.ncws.JSONFormatter;
import com.bbtech.ncws.JSONParser;
import com.bbtech.ncws.Status;
import com.bbtech.ncws.WeChatSession;
import net.locplus.sdk.wechat.util.HttpUtil;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Dean on 2014/4/29.
 */
@Service
public class WechatUserServiceImpl implements WechatUserService {
    private static final Logger logger = LoggerFactory.getLogger(WechatUserServiceImpl.class);
    @Override
    public boolean bind(String openid, String session) {
        boolean result = false;
        try {
            String req = JSONFormatter.formatWeChatRegister(openid, session);
            String resp = HttpUtil.getHttpInstance().post(NCWS_URL.WECHAT_REGISTER, req);
            String parseResp = JSONParser.parseWeChatRegister(resp);
            if (Status.STATUS_OK.equalsIgnoreCase(parseResp)) {
                result = true;
            }
        } catch (JSONException e) {
            logger.error("bind user error: {}", e.getMessage());
        }
        return result;
    }

    @Override
    public boolean unbind(String openid) {
        boolean result = false;
        try {
            String req = JSONFormatter.formatWeChatUnregister(openid);
            String resp = HttpUtil.getHttpInstance().post(NCWS_URL.WECHAT_UNREGISTER, req);
            String parseResp = JSONParser.parseWeChatUnregister(resp);
            if (Status.STATUS_OK.equalsIgnoreCase(parseResp)) {
                result = true;
            }
        } catch (JSONException e) {
            logger.error("unbind user error: {}", e.getMessage());
        }
        return result;
    }

    @Override
    public String getSession(String openid) {
        String result = (String) MemcachedUtil.getInstance().get("NCWC_SESSION_" + openid);
        if (result == null) {
            try {
                String req = JSONFormatter.formatWeChatSession(openid);
                String resp = HttpUtil.getHttpInstance().post(NCWS_URL.WECHAT_SESSION, req);
                WeChatSession weChatSession = JSONParser.parseWeChatSession(resp);
                if (weChatSession != null) {
                    result = weChatSession.getSession();
                }
            } catch (JSONException e) {
                logger.error("get session from ncws error: {}", e.getMessage());
            }
            if (result != null && !result.isEmpty()) {
                MemcachedUtil.getInstance().set("NCWC_SESSION_" + openid, result, 24 * 60 * 60);
            }
        }

        return result;
    }
}
