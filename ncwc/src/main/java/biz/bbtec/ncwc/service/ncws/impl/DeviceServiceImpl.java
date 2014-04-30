package biz.bbtec.ncwc.service.ncws.impl;

import biz.bbtec.ncwc.service.ncws.DeviceService;
import biz.bbtec.ncwc.util.NCWS_URL;
import com.bbtech.ncws.DeviceList2;
import com.bbtech.ncws.JSONFormatter;
import com.bbtech.ncws.JSONParser;
import net.locplus.sdk.wechat.util.HttpUtil;
import org.json.JSONException;

import java.util.List;

/**
 * Created by Dean on 2014/4/30.
 */
public class DeviceServiceImpl implements DeviceService {
    @Override
    public List<DeviceList2> getDeviceList(String session, int start, int timezone, int pageSize) {
        List<DeviceList2> result = null;
        try {
            String req = JSONFormatter.formatDeviceList2(session, start, timezone, pageSize);
            String resp = HttpUtil.getHttpInstance().post(NCWS_URL.DEVICE_LIST2, req);
            result = JSONParser.parseDeviceList2(resp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
