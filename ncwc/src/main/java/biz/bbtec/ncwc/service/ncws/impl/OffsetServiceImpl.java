package biz.bbtec.ncwc.service.ncws.impl;

import biz.bbtec.ncwc.service.ncws.OffsetService;
import biz.bbtec.ncwc.util.NCWS_URL;
import com.bbtech.ncws.JSONFormatter;
import com.bbtech.ncws.JSONParser;
import com.bbtech.ncws.OffsetResult;
import net.locplus.sdk.wechat.util.HttpUtil;
import org.json.JSONException;
import org.springframework.stereotype.Service;

/**
 * Created by Dean on 2014/4/29.
 */
@Service
public class OffsetServiceImpl implements OffsetService {
    @Override
    public OffsetResult getGoogleOffset(float lat, float lon) {
        OffsetResult result = null;
        try {
            String req = JSONFormatter.formatOffset(lat, lon);
            String resp = HttpUtil.getHttpInstance().post(NCWS_URL.OFFSET, req);
            result = JSONParser.parseOffset(resp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
