package biz.bbtec.ncwc.handler.text;

import biz.bbtec.ncwc.handler.common.NoBindHelper;
import biz.bbtec.ncwc.service.ncws.GeoService;
import biz.bbtec.ncwc.service.ncws.LocationService;
import biz.bbtec.ncwc.service.ncws.SearchDeviceService;
import biz.bbtec.ncwc.service.ncws.WechatUserService;
import biz.bbtec.ncwc.service.ncws.impl.GeoServiceImpl;
import biz.bbtec.ncwc.service.ncws.impl.LocationServiceImpl;
import biz.bbtec.ncwc.service.ncws.impl.SearchDeviceServiceImpl;
import biz.bbtec.ncwc.service.ncws.impl.WechatUserServiceImpl;
import com.bbtech.ncws.AddressResult;
import com.bbtech.ncws.LastUpdate2;
import com.bbtech.ncws.LatLon;
import com.bbtech.ncws.SearchResult;
import net.locplus.sdk.wechat.handler.MsgTypes;
import net.locplus.sdk.wechat.model.req.normal.TextRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;
import net.locplus.sdk.wechat.model.resp.TextResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dean on 2014/4/29.
 */
public class SearchDeviceTextHandlerState implements TextHandlerState {

    private SearchDeviceService searchDeviceService = new SearchDeviceServiceImpl();

    private WechatUserService wechatUserService = new WechatUserServiceImpl();

    private LocationService locationService = new LocationServiceImpl();

    private GeoService geoService = new GeoServiceImpl();

    @Override
    public BaseResponseMessage handle(TextRequestMessage requestMessage) {
        String session = wechatUserService.getSession(requestMessage.getFromUserName());

        if (session == null || session.isEmpty()) {
            return NoBindHelper.remember(requestMessage);
        }

        String content = requestMessage.getContent();
        String[] strs = content.split("\\s+");
        List<SearchResult> searchResults = searchDeviceService.search(session, strs[1]);

        List<LatLon> latLons = new ArrayList<>();
        for (SearchResult searchResult : searchResults) {
            LastUpdate2 lastUpdate2 = locationService.getLastUpdate(searchResult.getDeviceId(), session);
            LatLon latLon = new LatLon(lastUpdate2.getLatitude(), lastUpdate2.getLongitude());
            latLons.add(latLon);
        }

        List<AddressResult> addressResults = geoService.getMultiGeo(latLons);

        TextResponseMessage responseMessage = new TextResponseMessage();
        responseMessage.setToUserName(requestMessage.getFromUserName());
        responseMessage.setFromUserName(requestMessage.getToUserName());
        responseMessage.setCreateTime(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        sb.append("搜索结果：\n\n");
        for (int i = 0; i < searchResults.size(); i++) {
            SearchResult searchResult = searchResults.get(i);
            sb.append(searchResult.getDeviceId()).append("\n").append(searchResult.getName()).append("\n");
            sb.append("<a href=\"http://wx.bbtec.biz/resources/map.html?deviceid=")
                    .append(searchResult.getDeviceId()).append("&").append("openid=").append(requestMessage.getFromUserName()).append("\">")
                    .append(addressResults.get(i).getAddress()).append("</a>").append("\n");
            sb.append("------------------------------------------------\n");
        }
        responseMessage.setContent(sb.toString());
        responseMessage.setMsgType(MsgTypes.TEXT.getType());
        return responseMessage;
    }
}
