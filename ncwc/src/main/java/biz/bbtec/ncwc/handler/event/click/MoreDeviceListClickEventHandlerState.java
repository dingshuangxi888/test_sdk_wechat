package biz.bbtec.ncwc.handler.event.click;

import biz.bbtec.ncwc.handler.common.DeviceListMessageFormat;
import biz.bbtec.ncwc.handler.common.UserBindHelper;
import biz.bbtec.ncwc.service.ncws.DeviceService;
import biz.bbtec.ncwc.service.ncws.ServiceSingletonFactory;
import biz.bbtec.ncwc.service.ncws.WechatUserService;
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
 * Created by Dean on 2014/4/29.
 */
public class MoreDeviceListClickEventHandlerState implements ClickEventHandlerState {

    private final static Logger logger = LoggerFactory.getLogger(MoreDeviceListClickEventHandlerState.class);

    private WechatUserService wechatUserService = ServiceSingletonFactory.getWechatUserService();
    private DeviceService deviceService = ServiceSingletonFactory.getDeviceService();

    @Override
    public BaseResponseMessage handle(ClickEventRequestMessage requestMessage) {

        long start = System.currentTimeMillis();

        TextResponseMessage responseMessage = new TextResponseMessage();

        String session = wechatUserService.getSession(requestMessage.getFromUserName());

        if (session == null || session.isEmpty()) {
            return UserBindHelper.noBindRemember(requestMessage);
        }

        Integer pageNo = (Integer) MemcachedUtil.getInstance().get("NCWC_DEVICE_LIST_PAGE_NO_" + requestMessage.getFromUserName());
        logger.info("Now device list page = {}", pageNo);
        pageNo = pageNo == null ? 0 : pageNo;
        List<DeviceList2> list = deviceService.getDeviceList(session, pageNo * Configuration.DEVICE_LIST_PAGE_SIZE, 8, Configuration.DEVICE_LIST_PAGE_SIZE);

        responseMessage.setToUserName(requestMessage.getFromUserName());
        responseMessage.setFromUserName(requestMessage.getToUserName());
        responseMessage.setCreateTime(System.currentTimeMillis());

        String content = null;
        if (list != null && !list.isEmpty()) {

            MemcachedUtil.getInstance().set("NCWC_DEVICE_LIST_PAGE_NO_" + requestMessage.getFromUserName(), pageNo + 1, 60 * 5);
            logger.info("Set device list page = {}", pageNo + 1);

            try {
                content = DeviceListMessageFormat.format(list);
            } catch (Exception e) {
                content = "获取失败，请稍后再试！";
                logger.info("format device list message error: {}", e.getMessage());
            }

        } else {
            content = "没有更多设备列表，您可点击【获取列表】重新获取";
        }

        responseMessage.setContent(content);
        responseMessage.setMsgType(MsgTypes.TEXT.getType());

        logger.info("Using time for more device list is {}ms", (System.currentTimeMillis() - start));
        return responseMessage;
    }
}
