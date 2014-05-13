package biz.bbtec.ncwc.handler.common;

import biz.bbtec.ncwc.util.Configuration;
import net.locplus.sdk.wechat.handler.MsgTypes;
import net.locplus.sdk.wechat.model.req.BaseRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;
import net.locplus.sdk.wechat.model.resp.TextResponseMessage;

/**
 * Created by Administrator on 2014/4/30.
 */
public class UserBindHelper {
    public static BaseResponseMessage noBindRemember(BaseRequestMessage requestMessage) {
        TextResponseMessage responseMessage = new TextResponseMessage();
        responseMessage.setToUserName(requestMessage.getFromUserName());
        responseMessage.setFromUserName(requestMessage.getToUserName());
        responseMessage.setCreateTime(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        sb.append("您还没有绑定账户，请点击【更多】->【绑定账户】进行绑定");
        responseMessage.setContent(sb.toString());
        responseMessage.setMsgType(MsgTypes.TEXT.getType());
        return responseMessage;
    }

    public static BaseResponseMessage BindRemember(BaseRequestMessage requestMessage) {
        TextResponseMessage responseMessage = new TextResponseMessage();
        responseMessage.setToUserName(requestMessage.getFromUserName());
        responseMessage.setFromUserName(requestMessage.getToUserName());
        responseMessage.setCreateTime(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        sb.append("登录NC账户，便可查询您设备的信息以及相关位置\n");
        sb.append("<a href=\"" + Configuration.NCWC_URL_DNS + "resources/index.html?openid=");
        sb.append(requestMessage.getFromUserName()).append("\">");
        sb.append("点击这里，立即绑定");
        sb.append("</a>");
        responseMessage.setContent(sb.toString());
        responseMessage.setMsgType(MsgTypes.TEXT.getType());
        return responseMessage;
    }
}
