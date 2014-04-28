package biz.bbtec.ncwc.handler.event.click;

import net.locplus.sdk.wechat.model.req.event.ClickEventRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;

/**
 * Created by Dean on 2014/4/28.
 */
public class ClickEventContext {

    private ClickEventState state;

    public ClickEventContext(ClickEventState state) {
        this.state = state;
    }

    public void setState(ClickEventState state) {
        this.state = state;
    }

    public BaseResponseMessage request(ClickEventRequestMessage reqeustMessage) {
        return state.handle(reqeustMessage);
    }
}
