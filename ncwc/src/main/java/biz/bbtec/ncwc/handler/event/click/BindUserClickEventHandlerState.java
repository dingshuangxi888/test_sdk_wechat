package biz.bbtec.ncwc.handler.event.click;

import net.locplus.sdk.wechat.handler.MsgTypes;
import net.locplus.sdk.wechat.model.req.event.ClickEventRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;
import net.locplus.sdk.wechat.model.resp.TextResponseMessage;

/**
 * Created by Administrator on 2014/4/29.
 */
public class BindUserClickEventHandlerState implements ClickEventHandlerState {

    @Override
    public BaseResponseMessage handle(ClickEventRequestMessage reqeustMessage) {
        TextResponseMessage responseMessage = new TextResponseMessage();
        responseMessage.setToUserName(reqeustMessage.getFromUserName());
        responseMessage.setFromUserName(reqeustMessage.getToUserName());
        responseMessage.setCreateTime(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        sb.append("登录NC账户，便可查询您设备的信息以及相关位置\n");
        sb.append("<a href=\"http://wx.bbtec.biz/resources/index.html?openid=");
        sb.append(reqeustMessage.getFromUserName()).append("\">");
        sb.append("点击这里，立即绑定");
        sb.append("</a>");
        responseMessage.setContent(sb.toString());
        responseMessage.setMsgType(MsgTypes.TEXT.getType());
        return responseMessage;
    }
}
