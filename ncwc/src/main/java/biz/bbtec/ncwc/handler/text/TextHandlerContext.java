package biz.bbtec.ncwc.handler.text;

import net.locplus.sdk.wechat.model.req.normal.TextRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;

/**
 * Created by Dean on 2014/4/29.
 */
public class TextHandlerContext {

    private TextHandlerState state;

    public TextHandlerContext(TextHandlerState state) {
        this.state = state;
    }

    public void setState(TextHandlerState state) {
        this.state = state;
    }

    public BaseResponseMessage request(TextRequestMessage requestMessage) {
        return state.handle(requestMessage);
    }
}
