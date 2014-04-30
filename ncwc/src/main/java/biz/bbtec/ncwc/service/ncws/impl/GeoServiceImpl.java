package biz.bbtec.ncwc.service.ncws.impl;

import biz.bbtec.ncwc.service.ncws.GeoService;
import biz.bbtec.ncwc.util.NCWS_URL;
import com.bbtech.ncws.*;
import net.locplus.sdk.wechat.util.HttpUtil;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2014/4/29.
 */
@Service
public class GeoServiceImpl implements GeoService {
    @Override
    public String getGeo(float lat, float lon) {
        String result = null;
        try {
            String req = JSONFormatter.formatReverseGeocoding(lat, lon, LanguageCode.CHINESE_SIMPLIFIED);
            String resp = HttpUtil.getHttpInstance().post(NCWS_URL.REVERSE_GEOCODING, req);
            result = JSONParser.parseReverseGeocoding(resp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<AddressResult> getMultiGeo(List<LatLon> latLons) {
        List<AddressResult> result = null;
        try {
            String req = JSONFormatter.formatReverseMultiGeocoding(latLons, LanguageCode.CHINESE_SIMPLIFIED);
            String resp = HttpUtil.getHttpInstance().post(NCWS_URL.REVERSE_MULTI_GEOCODING, req);
            result = JSONParser.parseReverseMultiGeocoding(resp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
