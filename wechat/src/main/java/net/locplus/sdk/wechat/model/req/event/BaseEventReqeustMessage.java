package net.locplus.sdk.wechat.model.req.event;

import net.locplus.sdk.wechat.model.req.BaseRequestMessage;

/**
 * 接收时间推送 基类
 * <p/>
 * 关注/取消关注事件
 * 用户在关注与取消关注公众号时，微信会把这个事件推送到开发者填写的URL。方便开发者给用户下发欢迎消息或者做帐号的解绑。
 * Created by Administrator on 2014/4/18.
 */
public class BaseEventReqeustMessage extends BaseRequestMessage {
    /**
     * 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
     */
    private String Event;

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }
}
