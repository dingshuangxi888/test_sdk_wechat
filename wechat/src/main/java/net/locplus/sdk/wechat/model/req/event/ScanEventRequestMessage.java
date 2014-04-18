package net.locplus.sdk.wechat.model.req.event;

/**
 * 扫描带参数二维码事件
 * 用户扫描带场景值二维码时，可能推送以下两种事件：
 * 1、如果用户还未关注公众号，则用户可以关注公众号，关注后微信会将带场景值关注事件推送给开发者。
 * 2、如果用户已经关注公众号，则微信会将带场景值扫描事件推送给开发者。
 * Created by Administrator on 2014/4/18.
 */
public class ScanEventRequestMessage extends BaseEventReqeustMessage {
    /**
     * 事件KEY值，qrscene_为前缀，后面为二维码的参数值
     */
    private String EventKey;
    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private String Ticket;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }
}
