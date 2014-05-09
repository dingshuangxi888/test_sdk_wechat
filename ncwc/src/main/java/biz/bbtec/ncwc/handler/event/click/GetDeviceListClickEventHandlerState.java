package biz.bbtec.ncwc.handler.event.click;

import biz.bbtec.ncwc.handler.common.DeviceListMessageFormat;
import biz.bbtec.ncwc.handler.common.NoBindHelper;
import biz.bbtec.ncwc.service.ncws.*;
import biz.bbtec.ncwc.util.Configuration;
import biz.bbtec.ncwc.util.MemcachedUtil;
import com.bbtech.ncws.DeviceList2;
import net.locplus.sdk.wechat.handler.MsgTypes;
import net.locplus.sdk.wechat.model.req.event.ClickEventRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;
import net.locplus.sdk.wechat.model.resp.TextResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by dings_000 on 2014/4/29.
 */
public class GetDeviceListClickEventHandlerState implements ClickEventHandlerState {

    private final static Logger logger = LoggerFactory.getLogger(GetDeviceListClickEventHandlerState.class);

    private WechatUserService wechatUserService = ServiceSingletonFactory.getWechatUserService();
    private DeviceService deviceService = ServiceSingletonFactory.getDeviceService();
    private GeoService geoService = ServiceSingletonFactory.getGeoService();
    private OffsetService offsetService = ServiceSingletonFactory.getOffsetService();
    private ShortUrlService shortUrlService = ServiceSingletonFactory.getShortUrlService();

    @Override
    public BaseResponseMessage handle(ClickEventRequestMessage requestMessage) {
        long start = System.currentTimeMillis();
        TextResponseMessage responseMessage = new TextResponseMessage();

        String session = wechatUserService.getSession(requestMessage.getFromUserName());

        if (session == null || session.isEmpty()) {
            return NoBindHelper.remember(requestMessage);
        }

        // Get device list at page=0
        List<DeviceList2> list = deviceService.getDeviceList(session, 0, 8, Configuration.DEVICE_LIST_PAGE_SIZE);
        logger.info("Get device list page = 0");

        responseMessage.setToUserName(requestMessage.getFromUserName());
        responseMessage.setFromUserName(requestMessage.getToUserName());
        responseMessage.setCreateTime(System.currentTimeMillis());

        String content = null;
        if (list != null && !list.isEmpty()) {

            // Reset next device list page=1
            MemcachedUtil.getInstance().set("NCWC_DEVICE_LIST_PAGE_NO_" + requestMessage.getFromUserName(), 1, 60 * 5);
            logger.info("Reset next device list page = 1");

            try {
                content = DeviceListMessageFormat.format(list);
            } catch (Exception e) {
                content = "获取失败，请稍后再试！";
                logger.info("format device list message error: {}", e.getMessage());
            }

        } else {
            content = "该账户下没有设备，请登录<a href=\"http://ncui.bblbs.net\">桌面版</a>添加";
        }

        responseMessage.setContent(content);
        responseMessage.setMsgType(MsgTypes.TEXT.getType());

        logger.info("Using time for get device list {}ms", (System.currentTimeMillis() - start));
        return responseMessage;
    }
}
