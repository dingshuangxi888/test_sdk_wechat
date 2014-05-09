package biz.bbtec.ncwc.handler.common;

import net.locplus.sdk.wechat.handler.MsgTypes;
import net.locplus.sdk.wechat.model.req.BaseRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;
import net.locplus.sdk.wechat.model.resp.TextResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Dean on 2014/5/9.
 */
public class UnknownInstructionResponseMessage {
    private final static Logger logger = LoggerFactory.getLogger(UnknownInstructionResponseMessage.class);

    public static BaseResponseMessage getResponseMessage(BaseRequestMessage requestMessage) {

        String respContent = "对不起，您输入的指令不正确，请点击菜单【更多】->【获取帮助】获取指令...";

        TextResponseMessage responseMessage = new TextResponseMessage();
        responseMessage.setToUserName(requestMessage.getFromUserName());
        responseMessage.setFromUserName(requestMessage.getToUserName());
        responseMessage.setCreateTime(System.currentTimeMillis());
        responseMessage.setContent(respContent);
        responseMessage.setMsgType(MsgTypes.TEXT.getType());
        return responseMessage;
    }
}
