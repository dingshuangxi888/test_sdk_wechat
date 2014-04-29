package biz.bbtec.ncwc.handler.text;

import biz.bbtec.ncwc.handler.common.HelpHandler;
import net.locplus.sdk.wechat.model.req.normal.TextRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;

/**
 * Created by Dean on 2014/4/29.
 */
public class HelpTextHandlerState implements TextHandlerState {
    @Override
    public BaseResponseMessage handle(TextRequestMessage requestMessage) {
        return HelpHandler.helpMessage(requestMessage);
    }
}
