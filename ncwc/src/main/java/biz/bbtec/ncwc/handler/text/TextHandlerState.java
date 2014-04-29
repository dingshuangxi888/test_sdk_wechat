package biz.bbtec.ncwc.handler.text;

import net.locplus.sdk.wechat.model.req.normal.TextRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;

/**
 * Created by Dean on 2014/4/29.
 */
public interface TextHandlerState {

    public BaseResponseMessage handle(TextRequestMessage requestMessage);
}
