package net.locplus.sdk.wechat.model.req.event;

/**
 * 自定义菜单事件
 * 用户点击自定义菜单后，微信会把点击事件推送给开发者，请注意，点击菜单弹出子菜单，不会产生上报。
 * 1、点击菜单拉取消息时的事件推送，Event=CLICK
 * 2、点击菜单跳转链接时的事件推送，Event=VIEW
 * Created by Administrator on 2014/4/18.
 */
public class MenuEventRequestMessage extends BaseEventReqeustMessage {

    /**
     * 1、事件KEY值，与自定义菜单接口中KEY值对应，Event=CLICK
     * 2、事件KEY值，设置的跳转URL，Event=VIEW
     */
    private String EventKey;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
