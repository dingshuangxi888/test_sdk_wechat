package biz.bbtec.ncwc.service.ncws.impl;

import biz.bbtec.ncwc.service.ncws.ShortUrlService;
import biz.bbtec.ncwc.util.NCWS_URL;
import com.bbtech.ncws.JSONFormatter;
import com.bbtech.ncws.JSONParser;
import net.locplus.sdk.wechat.util.HttpUtil;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Administrator on 2014/5/6.
 */
@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    @Override
    public String toShortUrl(String longUrl) {
        String result = null;
        try {
            String req = JSONFormatter.formatWeChatShortUrl(longUrl);
            String resp = HttpUtil.getHttpInstance().post(NCWS_URL.WECHAT_SHORT_URL, req);
            result = JSONParser.parseWeChatShortUrl(resp);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<String> toShortUrls(List<String> longUrls) {
        List<String> result = null;
        String req = null;
        try {
            req = JSONFormatter.formatWeChatShortUrls(longUrls);
            String resp = HttpUtil.getHttpInstance().post(NCWS_URL.WECHAT_SHORT_URLS, req);
            result = JSONParser.parseWeChatShortUrls(resp);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
