package biz.bbtec.ncwc.handler.event.click;

import net.locplus.sdk.wechat.model.req.event.ClickEventRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;

/**
 * Created by Dean on 2014/4/28.
 */
public interface ClickEventHandlerState {

    public BaseResponseMessage handle(ClickEventRequestMessage requestMessage);
}
