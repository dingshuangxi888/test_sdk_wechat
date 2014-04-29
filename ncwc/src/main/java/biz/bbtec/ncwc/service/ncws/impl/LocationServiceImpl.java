package biz.bbtec.ncwc.service.ncws.impl;

import biz.bbtec.ncwc.service.ncws.LocationService;
import biz.bbtec.ncwc.service.ncws.LoginService;
import biz.bbtec.ncwc.util.NCWS_URL;
import com.bbtech.ncws.JSONFormatter;
import com.bbtech.ncws.JSONParser;
import com.bbtech.ncws.LastUpdate2;
import com.bbtech.ncws.UserLogin;
import net.locplus.sdk.wechat.util.HttpUtil;
import org.json.JSONException;
import org.springframework.stereotype.Service;

/**
 * Created by Dean on 2014/4/29.
 */
@Service
public class LocationServiceImpl implements LocationService {
    @Override
    public LastUpdate2 getLastUpdate(long deviceid, String session) {
        LastUpdate2 result = null;
        try {
            String req = JSONFormatter.formatLastUpdate2(deviceid, session, 8);
            String resp = HttpUtil.getHttpInstance().post(NCWS_URL.LAST_UPDATE2, req);
            result = JSONParser.parseLastUpdate2(resp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
