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
public class UnbindUserClickEventHandlerState implements ClickEventHandlerState {

    @Autowired
    private WechatUserService wechatUserService;

    @Override
    public BaseResponseMessage handle(ClickEventRequestMessage reqeustMessage) {

        TextResponseMessage responseMessage = new TextResponseMessage();
        responseMessage.setToUserName(reqeustMessage.getFromUserName());
        responseMessage.setFromUserName(reqeustMessage.getToUserName());
        responseMessage.setCreateTime(System.currentTimeMillis());
        boolean unbindSuccess = wechatUserService.unbind(reqeustMessage.getFromUserName());
        if (unbindSuccess) {
            responseMessage.setContent("解除绑定成功，点击“菜单-》更多...-》绑定账户”可重新绑定...");
        } else {
            responseMessage.setContent("对不起，解除绑定失败，请稍后再试...");
        }

        responseMessage.setMsgType(MsgTypes.TEXT.getType());
        return responseMessage;
    }
}
