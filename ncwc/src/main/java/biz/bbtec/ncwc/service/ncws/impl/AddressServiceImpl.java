package biz.bbtec.ncwc.service.ncws.impl;

import biz.bbtec.ncwc.service.ncws.AddressService;
import biz.bbtec.ncwc.util.NCWS_URL;
import com.bbtech.ncws.AddressResult;
import com.bbtech.ncws.JSONFormatter;
import com.bbtech.ncws.JSONParser;
import net.locplus.sdk.wechat.util.HttpUtil;
import org.json.JSONException;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2014/4/29.
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Override
    public String getAddress(float lat, float lon) {
        String result = null;
        try {
            String req = JSONFormatter.formatReverseGeocoding(lat, lon, "zh-CN");
            String resp = HttpUtil.getHttpInstance().post(NCWS_URL.REVERSE_GEOCODING, req);
            result = JSONParser.parseReverseGeocoding(resp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
