package biz.bbtec.ncwc.handler.event.click;

import biz.bbtec.ncwc.handler.common.NoBindHelper;
import biz.bbtec.ncwc.service.ncws.ServiceSingletonFactory;
import biz.bbtec.ncwc.service.ncws.WechatUserService;
import biz.bbtec.ncwc.util.MemcachedUtil;
import net.locplus.sdk.wechat.handler.MsgTypes;
import net.locplus.sdk.wechat.model.req.event.ClickEventRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;
import net.locplus.sdk.wechat.model.resp.TextResponseMessage;

/**
 * Created by Dean on 2014/4/28.
 */
public class UnbindUserClickEventHandlerState implements ClickEventHandlerState {

    private WechatUserService wechatUserService = ServiceSingletonFactory.getWechatUserService();

    @Override
    public BaseResponseMessage handle(ClickEventRequestMessage requestMessage) {

        String session = wechatUserService.getSession(requestMessage.getFromUserName());

        if (session == null || session.isEmpty()) {
            return NoBindHelper.remember(requestMessage);
        }

        TextResponseMessage responseMessage = new TextResponseMessage();
        responseMessage.setToUserName(requestMessage.getFromUserName());
        responseMessage.setFromUserName(requestMessage.getToUserName());
        responseMessage.setCreateTime(System.currentTimeMillis());
        boolean unbindSuccess = wechatUserService.unbind(requestMessage.getFromUserName());
        if (unbindSuccess) {
            MemcachedUtil.getInstance().remove("NCWC_SESSION_" + requestMessage.getFromUserName());
            responseMessage.setContent("解除绑定成功，点击“菜单-》更多...-》绑定账户”可重新绑定...");
        } else {
            responseMessage.setContent("对不起，解除绑定失败，请稍后再试...");
        }

        responseMessage.setMsgType(MsgTypes.TEXT.getType());
        return responseMessage;
    }
}
