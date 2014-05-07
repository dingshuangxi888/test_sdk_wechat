package biz.bbtec.ncwc.handler.text;

import biz.bbtec.ncwc.handler.common.NoBindHelper;
import biz.bbtec.ncwc.service.ncws.*;
import biz.bbtec.ncwc.util.Configuration;
import com.bbtech.ncws.*;
import net.locplus.sdk.wechat.handler.MsgTypes;
import net.locplus.sdk.wechat.model.req.normal.TextRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;
import net.locplus.sdk.wechat.model.resp.TextResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dean on 2014/4/29.
 */
@Service
public class SearchDeviceTextHandlerState implements TextHandlerState {

    @Autowired
    private SearchDeviceService searchDeviceService = ServiceSingletonFactory.getSearchDeviceService();

    private WechatUserService wechatUserService = ServiceSingletonFactory.getWechatUserService();

    private LocationService locationService = ServiceSingletonFactory.getLocationService();

    private GeoService geoService = ServiceSingletonFactory.getGeoService();

    private OffsetService offsetService = ServiceSingletonFactory.getOffsetService();

    private ShortUrlService shortUrlService = ServiceSingletonFactory.getShortUrlService();

    @Override
    public BaseResponseMessage handle(TextRequestMessage requestMessage) {
        String session = wechatUserService.getSession(requestMessage.getFromUserName());

        if (session == null || session.isEmpty()) {
            return NoBindHelper.remember(requestMessage);
        }

        String content = requestMessage.getContent();
        String[] strs = content.split("\\s+");
        List<SearchResult> searchResults = searchDeviceService.search(session, strs[1]);

        List<LatLon> latLons = new ArrayList<>(searchResults.size());
        List<LatLng> latLngs = new ArrayList<>(searchResults.size());
        List<LastUpdate2> lastUpdates = new ArrayList<>(searchResults.size());
        for (SearchResult searchResult : searchResults) {
            LastUpdate2 lastUpdate2 = locationService.getLastUpdate(searchResult.getDeviceId(), session);
            lastUpdates.add(lastUpdate2);

            LatLon latLon = new LatLon(lastUpdate2.getLatitude(), lastUpdate2.getLongitude());
            latLons.add(latLon);

            LatLng latLng = new LatLng(lastUpdate2.getLatitude(), lastUpdate2.getLongitude());
            latLngs.add(latLng);
        }

        List<AddressResult> addressResults = geoService.getMultiGeo(latLons);

        List<OffsetResult> offsetResults = offsetService.getGoogleMultiOffset(latLngs);

        List<String> longUrls = new ArrayList<>(searchResults.size());
        for (int i = 0; i < lastUpdates.size(); i++) {
            LastUpdate2 lastUpdate = lastUpdates.get(i);
            StringBuilder sb = new StringBuilder(Configuration.NCWC_URL_DNS)
                    .append("resources/map.html?")
                    .append("deviceid=").append(lastUpdate.getDeviceId())
                    .append("&name=").append(lastUpdate.getName())
                    .append("&time=").append(lastUpdate.getTime())
                    .append("&coordinate=").append(lastUpdate.getLatitude()).append(",").append(lastUpdate.getLongitude())
                    .append(",").append(lastUpdate.getAltitude()).append(",").append(lastUpdate.getAccuracy())
                    .append("&offset=").append(lastUpdate.getLatitude() + offsetResults.get(i).getLatitudeOffset())
                    .append(",").append(lastUpdate.getLongitude() + offsetResults.get(i).getLongitudeOffset())
                    .append("&address=").append(addressResults.get(i).getAddress());
            longUrls.add(sb.toString());
        }

        List<String> shortUrls = shortUrlService.toShortUrls(longUrls);

        TextResponseMessage responseMessage = new TextResponseMessage();
        responseMessage.setToUserName(requestMessage.getFromUserName());
        responseMessage.setFromUserName(requestMessage.getToUserName());
        responseMessage.setCreateTime(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        sb.append("搜索结果：\n\n");
        for (int i = 0; i < searchResults.size(); i++) {
            SearchResult searchResult = searchResults.get(i);
            sb.append(searchResult.getDeviceId()).append("\n")
                    .append(searchResult.getName()).append("\n")
                    .append(lastUpdates.get(i).getTime()).append("\n");
            sb.append("<a href=\"").append(shortUrls.get(i)).append("\">")
                    .append(addressResults.get(i).getAddress()).append("</a>").append("\n");
            sb.append("------------------------------------------------\n");
        }
        responseMessage.setContent(sb.toString());
        responseMessage.setMsgType(MsgTypes.TEXT.getType());
        return responseMessage;
    }
}
