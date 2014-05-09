package biz.bbtec.ncwc.handler.text;

import biz.bbtec.ncwc.handler.common.UnknownInstructionResponseMessage;
import net.locplus.sdk.wechat.model.req.normal.TextRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Dean on 2014/5/8.
 */
public class UnknownTextHandlerState implements TextHandlerState {
    private final static Logger logger = LoggerFactory.getLogger(UnknownTextHandlerState.class);

    @Override
    public BaseResponseMessage handle(TextRequestMessage requestMessage) {

        return UnknownInstructionResponseMessage.getResponseMessage(requestMessage);
    }
}
