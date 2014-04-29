package biz.bbtec.ncwc.handler.common;

import net.locplus.sdk.wechat.handler.MsgTypes;
import net.locplus.sdk.wechat.model.req.BaseRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;
import net.locplus.sdk.wechat.model.resp.TextResponseMessage;

/**
 * Created by Dean on 2014/4/29.
 */
public class HelpHandler {

    public static BaseResponseMessage helpMessage(BaseRequestMessage requestMessage) {
        TextResponseMessage responseMessage = new TextResponseMessage();
        responseMessage.setToUserName(requestMessage.getFromUserName());
        responseMessage.setFromUserName(requestMessage.getToUserName());
        responseMessage.setCreateTime(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        sb.append("您好，请按照以下回复选择服务：").append("\n\n");
        sb.append("1、回复“搜索 【设备编号/名称】” 搜索设备").append("\n");
        sb.append("2、回复“帮助/?” 获取帮助").append("\n");
        responseMessage.setContent(sb.toString());
        responseMessage.setMsgType(MsgTypes.TEXT.getType());
        return responseMessage;
    }
}
