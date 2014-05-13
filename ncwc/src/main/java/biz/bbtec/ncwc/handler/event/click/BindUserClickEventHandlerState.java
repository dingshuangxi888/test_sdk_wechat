package biz.bbtec.ncwc.handler.event.click;

import biz.bbtec.ncwc.handler.common.UserBindHelper;
import net.locplus.sdk.wechat.model.req.event.ClickEventRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;

/**
 * Created by Administrator on 2014/4/29.
 */
public class BindUserClickEventHandlerState implements ClickEventHandlerState {

    @Override
    public BaseResponseMessage handle(ClickEventRequestMessage requestMessage) {
        return UserBindHelper.BindRemember(requestMessage);
    }
}
