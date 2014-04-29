package biz.bbtec.ncwc.handler.event.click;

import biz.bbtec.ncwc.handler.common.HelpHandler;
import net.locplus.sdk.wechat.handler.MsgTypes;
import net.locplus.sdk.wechat.model.req.event.ClickEventRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;
import net.locplus.sdk.wechat.model.resp.TextResponseMessage;

/**
 * Created by Dean on 2014/4/28.
 */
public class GetHelpClickEventHandlerState implements ClickEventHandlerState {
    @Override
    public BaseResponseMessage handle(ClickEventRequestMessage reqeustMessage) {
        return HelpHandler.helpMessage(reqeustMessage);
    }
}
