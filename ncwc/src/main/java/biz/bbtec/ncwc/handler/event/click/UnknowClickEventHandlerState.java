package biz.bbtec.ncwc.handler.event.click;

import biz.bbtec.ncwc.handler.common.UnknownInstructionResponseMessage;
import net.locplus.sdk.wechat.model.req.event.ClickEventRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Dean on 2014/5/9.
 */
public class UnknowClickEventHandlerState implements ClickEventHandlerState {
    private final static Logger logger = LoggerFactory.getLogger(UnknowClickEventHandlerState.class);

    @Override
    public BaseResponseMessage handle(ClickEventRequestMessage requestMessage) {
        return UnknownInstructionResponseMessage.getResponseMessage(requestMessage);
    }
}
