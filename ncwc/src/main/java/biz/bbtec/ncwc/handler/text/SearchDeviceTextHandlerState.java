package biz.bbtec.ncwc.handler.text;

import biz.bbtec.ncwc.handler.common.NoBindHelper;
import biz.bbtec.ncwc.service.ncws.*;
import biz.bbtec.ncwc.util.Configuration;
import biz.bbtec.ncwc.util.ThreadPoolUtil;
import com.bbtech.ncws.*;
import net.locplus.sdk.wechat.handler.MsgTypes;
import net.locplus.sdk.wechat.model.req.normal.TextRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;
import net.locplus.sdk.wechat.model.resp.TextResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by Dean on 2014/4/29.
 */
@Service
public class SearchDeviceTextHandlerState implements TextHandlerState {

    private final static Logger logger = LoggerFactory.getLogger(SearchDeviceTextHandlerState.class);

    @Autowired
    private SearchDeviceService searchDeviceService = ServiceSingletonFactory.getSearchDeviceService();

    private WechatUserService wechatUserService = ServiceSingletonFactory.getWechatUserService();

    private LocationService locationService = ServiceSingletonFactory.getLocationService();

    private GeoService geoService = ServiceSingletonFactory.getGeoService();

    private OffsetService offsetService = ServiceSingletonFactory.getOffsetService();

    private ShortUrlService shortUrlService = ServiceSingletonFactory.getShortUrlService();

    @Override
    public BaseResponseMessage handle(TextRequestMessage requestMessage) {

        long start = System.currentTimeMillis();

        String respContent = null;

        String session = wechatUserService.getSession(requestMessage.getFromUserName());

        if (session == null || session.isEmpty()) {
            return NoBindHelper.remember(requestMessage);
        }

        String content = requestMessage.getContent();
        String[] strs = content.split("\\s+");
        final List<SearchResult> searchResults = searchDeviceService.search(session, strs[1]);

        final List<LatLon> latLons = new ArrayList<>(searchResults.size());
        final List<LatLng> latLngs = new ArrayList<>(searchResults.size());
        final List<LastUpdate2> lastUpdates = new ArrayList<>(searchResults.size());
        for (SearchResult searchResult : searchResults) {
            LastUpdate2 lastUpdate2 = locationService.getLastUpdate(searchResult.getDeviceId(), session);
            lastUpdates.add(lastUpdate2);

            LatLon latLon = new LatLon(lastUpdate2.getLatitude(), lastUpdate2.getLongitude());
            latLons.add(latLon);

            LatLng latLng = new LatLng(lastUpdate2.getLatitude(), lastUpdate2.getLongitude());
            latLngs.add(latLng);
        }

        try {
            FutureTask<List<AddressResult>> addressResultsFutrue = new FutureTask<>(new Callable<List<AddressResult>>() {
                @Override
                public List<AddressResult> call() throws Exception {
                    return geoService.getMultiGeo(latLons);
                }
            });

            ThreadPoolUtil.getPool().execute(addressResultsFutrue);

            FutureTask<List<OffsetResult>> offsetResultsFutrue = new FutureTask<>(new Callable<List<OffsetResult>>() {
                @Override
                public List<OffsetResult> call() throws Exception {
                    return offsetService.getGoogleMultiOffset(latLngs);
                }
            });

            ThreadPoolUtil.getPool().execute(offsetResultsFutrue);

            final List<AddressResult> addressResults = addressResultsFutrue.get();

            final List<OffsetResult> offsetResults = offsetResultsFutrue.get();

            final FutureTask<List<String>> shortUrlsFuture = new FutureTask<>(new Callable<List<String>>() {
                @Override
                public List<String> call() throws Exception {
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
                    return shortUrlService.toShortUrls(longUrls);
                }
            });

            ThreadPoolUtil.getPool().execute(shortUrlsFuture);

            List<String> shortUrls = shortUrlsFuture.get();

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

            respContent = sb.toString();
        } catch (Exception e) {
            content = "获取失败，请稍后再试！";
            logger.info("format device list message error: {}", e.getMessage());
        }

        TextResponseMessage responseMessage = new TextResponseMessage();
        responseMessage.setToUserName(requestMessage.getFromUserName());
        responseMessage.setFromUserName(requestMessage.getToUserName());
        responseMessage.setCreateTime(System.currentTimeMillis());
        responseMessage.setContent(respContent);
        responseMessage.setMsgType(MsgTypes.TEXT.getType());

        logger.info("Using time for this device searching is {}ms", (System.currentTimeMillis() - start));
        return responseMessage;
    }
}
