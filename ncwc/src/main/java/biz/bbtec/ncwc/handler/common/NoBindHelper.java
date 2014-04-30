package biz.bbtec.ncwc.handler.common;

import net.locplus.sdk.wechat.handler.MsgTypes;
import net.locplus.sdk.wechat.model.req.BaseRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;
import net.locplus.sdk.wechat.model.resp.TextResponseMessage;

/**
 * Created by Administrator on 2014/4/30.
 */
public class NoBindHelper {
    public static BaseResponseMessage remember(BaseRequestMessage requestMessage) {
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
}
