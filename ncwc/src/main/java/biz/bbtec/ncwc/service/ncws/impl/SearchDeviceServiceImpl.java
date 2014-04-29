package biz.bbtec.ncwc.service.ncws.impl;

import biz.bbtec.ncwc.service.ncws.SearchDeviceService;
import biz.bbtec.ncwc.util.NCWS_URL;
import com.bbtech.ncws.JSONFormatter;
import com.bbtech.ncws.JSONParser;
import com.bbtech.ncws.SearchResult;
import net.locplus.sdk.wechat.util.HttpUtil;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/4/29.
 */
@Service
public class SearchDeviceServiceImpl implements SearchDeviceService {

    public List<SearchResult> search(String session, String key) {
        List<SearchResult> result = new ArrayList<SearchResult>();

        try {
            String req = JSONFormatter.formatSearch(session, key);
            String resp = HttpUtil.getHttpInstance().post(NCWS_URL.SEARCH, req);
            result = JSONParser.parseSearch(resp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
