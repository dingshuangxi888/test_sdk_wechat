package biz.bbtec.ncwc.handler.event.click;

import net.locplus.sdk.wechat.model.req.event.ClickEventRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;

/**
 * Created by Dean on 2014/4/28.
 */
public class ClickEventHandlerContext {

    private ClickEventHandlerState state;

    public ClickEventHandlerContext(ClickEventHandlerState state) {
        this.state = state;
    }

    public void setState(ClickEventHandlerState state) {
        this.state = state;
    }

    public BaseResponseMessage request(ClickEventRequestMessage requestMessage) {
        return state.handle(requestMessage);
    }
}
