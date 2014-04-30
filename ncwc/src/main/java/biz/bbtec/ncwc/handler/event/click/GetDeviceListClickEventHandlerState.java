package biz.bbtec.ncwc.handler.event.click;

import biz.bbtec.ncwc.handler.common.HelpHandler;
import biz.bbtec.ncwc.handler.common.NoBindHelper;
import biz.bbtec.ncwc.service.ncws.DeviceService;
import biz.bbtec.ncwc.service.ncws.GeoService;
import biz.bbtec.ncwc.service.ncws.OffsetService;
import biz.bbtec.ncwc.service.ncws.WechatUserService;
import biz.bbtec.ncwc.service.ncws.impl.DeviceServiceImpl;
import biz.bbtec.ncwc.service.ncws.impl.GeoServiceImpl;
import biz.bbtec.ncwc.service.ncws.impl.OffsetServiceImpl;
import biz.bbtec.ncwc.service.ncws.impl.WechatUserServiceImpl;
import biz.bbtec.ncwc.util.Configuration;
import biz.bbtec.ncwc.util.MemcachedUtil;
import com.bbtech.ncws.*;
import net.locplus.sdk.wechat.handler.MsgTypes;
import net.locplus.sdk.wechat.model.req.event.ClickEventRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;
import net.locplus.sdk.wechat.model.resp.TextResponseMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dings_000 on 2014/4/29.
 */
public class GetDeviceListClickEventHandlerState implements ClickEventHandlerState {

    private WechatUserService wechatUserService = new WechatUserServiceImpl();
    private DeviceService deviceService = new DeviceServiceImpl();
    private GeoService geoService = new GeoServiceImpl();
    private OffsetService offsetService = new OffsetServiceImpl();
    @Override
    public BaseResponseMessage handle(ClickEventRequestMessage requestMessage) {
        TextResponseMessage responseMessage = new TextResponseMessage();

        String session = wechatUserService.getSession(requestMessage.getFromUserName());

        if (session == null || session.isEmpty()) {
            return NoBindHelper.remember(requestMessage);
        }

        List<DeviceList2> list = deviceService.getDeviceList(session, 0, 8, Configuration.DEVICE_LIST_PAGE_SIZE);

        responseMessage.setToUserName(requestMessage.getFromUserName());
        responseMessage.setFromUserName(requestMessage.getToUserName());
        responseMessage.setCreateTime(System.currentTimeMillis());

        StringBuilder sb = new StringBuilder();
        if (list != null && !list.isEmpty()) {

            MemcachedUtil.getInstance().set("NCWC_DEVICE_LIST_PAGE_NO_" + requestMessage.getFromUserName(), 1, 60 * 5);

            List<LatLon> latLons = new ArrayList<>();
            List<LatLng> latLngs = new ArrayList<>();
            for (DeviceList2 deviceList : list) {
                latLons.add(new LatLon(deviceList.getLatitude(), deviceList.getLongitude()));
                latLngs.add(new LatLng(deviceList.getLatitude(), deviceList.getLongitude()));
            }
            List<AddressResult> addressResults = geoService.getMultiGeo(latLons);
            List<OffsetResult> offsetResults = offsetService.getGoogleMultiOffset(latLngs);

            DeviceList2 deviceListVO = null;
            for (int i = 0; i < list.size(); i++) {
                deviceListVO = list.get(i);
                sb.append(deviceListVO.getDeviceId()).append("\n");
                sb.append(deviceListVO.getName()).append("\n");
                sb.append(deviceListVO.getTime()).append("\n");
                sb.append("<a href=\"http://wx.bbtec.biz/resources/map.html?deviceid=")
                        .append(deviceListVO.getDeviceId()).append("&").append("openid=").append(requestMessage.getFromUserName()).append("\">")
                        .append(addressResults.get(i).getAddress()).append("</a>").append("\n");
                sb.append("------------------------------------------------\n");
            }
        } else {
            sb.append("该账户下没有设备，请登录<a href=\"http://ncui.bblbs.net\">桌面版</a>添加");
        }

        responseMessage.setContent(sb.toString());
        responseMessage.setMsgType(MsgTypes.TEXT.getType());

        return responseMessage;
    }
}
