package biz.bbtec.ncwc.handler.event.click;

import biz.bbtec.ncwc.service.ncws.WechatUserService;
import net.locplus.sdk.wechat.handler.MsgTypes;
import net.locplus.sdk.wechat.model.req.event.ClickEventRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;
import net.locplus.sdk.wechat.model.resp.TextResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Dean on 2014/4/28.
 */
public class DeviceListClickEventHandlerState implements ClickEventHandlerState {

    @Override
    public BaseResponseMessage handle(ClickEventRequestMessage reqeustMessage) {
        TextResponseMessage responseMessage = new TextResponseMessage();
        responseMessage.setToUserName(reqeustMessage.getFromUserName());
        responseMessage.setFromUserName(reqeustMessage.getToUserName());
        responseMessage.setCreateTime(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        sb.append("设备列表：\n\n");
        for (int i = 0; i < 10; i++) {
            sb.append("<a href=\"http://wx.bbtec.biz/resources/map.html?deviceid=").append(i).append("\">").append(i).append(":").append("device").append(i).append("</a>").append("\n");
        }
        sb.append("\n").append("回复“?”显示帮组菜单");
        responseMessage.setContent(sb.toString());
        responseMessage.setMsgType(MsgTypes.TEXT.getType());
        return responseMessage;
    }
}
