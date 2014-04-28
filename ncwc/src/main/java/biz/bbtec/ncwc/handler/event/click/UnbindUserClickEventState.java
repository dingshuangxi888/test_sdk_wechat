package biz.bbtec.ncwc.handler.event.click;

import net.locplus.sdk.wechat.handler.MsgTypes;
import net.locplus.sdk.wechat.model.req.event.ClickEventRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;
import net.locplus.sdk.wechat.model.resp.TextResponseMessage;

/**
 * Created by Dean on 2014/4/28.
 */
public class UnbindUserClickEventState implements ClickEventState {
    @Override
    public BaseResponseMessage handle(ClickEventRequestMessage reqeustMessage) {
        TextResponseMessage responseMessage = new TextResponseMessage();
        responseMessage.setToUserName(reqeustMessage.getFromUserName());
        responseMessage.setFromUserName(reqeustMessage.getToUserName());
        responseMessage.setCreateTime(System.currentTimeMillis());
        responseMessage.setContent("解除绑定成功，点击“菜单-》更多...-》绑定账户”可重新绑定");
        responseMessage.setMsgType(MsgTypes.TEXT.getType());
        return responseMessage;
    }
}
