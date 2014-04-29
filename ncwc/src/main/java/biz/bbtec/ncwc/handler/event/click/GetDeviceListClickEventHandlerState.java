package biz.bbtec.ncwc.handler.event.click;

import net.locplus.sdk.wechat.model.req.event.ClickEventRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;

/**
 * Created by dings_000 on 2014/4/29.
 */
public class GetDeviceListClickEventHandlerState implements ClickEventHandlerState {
    @Override
    public BaseResponseMessage handle(ClickEventRequestMessage reqeustMessage) {
        return null;
    }
}
