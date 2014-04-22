package net.locplus.sdk.wechat.handler;

import net.locplus.sdk.wechat.model.req.event.*;
import net.locplus.sdk.wechat.model.req.normal.*;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;

/**
 * 用于处理不同的请求，并设置返回消息
 * Created by Dean on 2014/4/22.
 */
public interface MessageProcessingHandler {

    /**
     * 设置回复消息的内容
     *
     * @param responseMessage
     */
    public void setResponseMessage(BaseResponseMessage responseMessage);

    /**
     * 处理文本消息
     *
     * @param requestMessage
     */
    public void onTextMessageReceived(TextRequestMessage requestMessage);

    /**
     * 处理图片消息
     *
     * @param requestMessage
     */
    public void onImageMessageReceived(ImageRequestMessage requestMessage);

    /**
     * 处理语音消息
     *
     * @param requestMessage
     */
    public void onVoiceMessageReceived(VoiceRequestMessage requestMessage);

    /**
     * 处理语音识别消息
     * @param requestMessage
     */
    public void onVoiceRecognitionMessageReceived(VoiceRecognitionRequestMessage requestMessage);

    /**
     * 处理视频消息
     *
     * @param requestMessage
     */
    public void onVideoMessageReceived(VideoRequestMessage requestMessage);

    /**
     * 处理地理位置消息
     *
     * @param requestMessage
     */
    public void onLocationMessageReceived(LocationRequestMessage requestMessage);

    /**
     * 处理链接消息
     *
     * @param requestMessage
     */
    public void onLinkMessageReceived(LinkRequestMessage requestMessage);

    /**
     * 处理自定义菜单事件
     * 点击菜单拉取消息时的事件推送
     * @param requestMessage
     */
    public void onClickEventMessageReceived(ClickEventRequestMessage requestMessage);

    /**
     * 处理自定义菜单事件
     * 点击菜单跳转链接时的事件推送
     * @param requestMessage
     */
    public void onViewEventMessageReceived(ViewEventRequestMessage requestMessage);

    /**
     * 处理上报地理位置事件
     *
     * @param requestMessage
     */
    public void onLocationEventMessageReceived(LocationEventRequestMessage requestMessage);

    /**
     * 扫描带参数二维码事件/关注事件
     * 用户未关注时，进行关注后的事件推送
     * @param requestMessage
     */
    public void onSubscribeEventMessageReceived(SubscribeEventRequestMessage requestMessage);

    /**
     * 处理扫描带参数二维码事件
     * 用户已关注时的事件推送
     *
     * @param requestMessage
     */
    public void onScanEventMessageReceived(ScanEventRequestMessage requestMessage);

    /**
     * 取消关注事件
     * @param requestMessage
     */
    public void onUnsubscribeEventMessageReceived(UnsubscribeEventRequestMessage requestMessage);
}
