package biz.bbtec.ncwc.wechat.handler;

import net.locplus.sdk.wechat.handler.MessageProcessingHandler;
import net.locplus.sdk.wechat.model.req.event.*;
import net.locplus.sdk.wechat.model.req.normal.*;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;

/**
 * Created by Dean on 2014/4/25.
 */
public class NcwcMessageProcessingHandler implements MessageProcessingHandler {

    private BaseResponseMessage responseMessage;

    @Override
    public BaseResponseMessage getResponseMessage() {
        return this.responseMessage;
    }

    @Override
    public void onTextMessageReceived(TextRequestMessage requestMessage) {

    }

    @Override
    public void onImageMessageReceived(ImageRequestMessage requestMessage) {

    }

    @Override
    public void onVoiceMessageReceived(VoiceRequestMessage requestMessage) {

    }

    @Override
    public void onVoiceRecognitionMessageReceived(VoiceRecognitionRequestMessage requestMessage) {

    }

    @Override
    public void onVideoMessageReceived(VideoRequestMessage requestMessage) {

    }

    @Override
    public void onLocationMessageReceived(LocationRequestMessage requestMessage) {

    }

    @Override
    public void onLinkMessageReceived(LinkRequestMessage requestMessage) {

    }

    @Override
    public void onClickEventMessageReceived(ClickEventRequestMessage requestMessage) {

    }

    @Override
    public void onViewEventMessageReceived(ViewEventRequestMessage requestMessage) {

    }

    @Override
    public void onLocationEventMessageReceived(LocationEventRequestMessage requestMessage) {

    }

    @Override
    public void onSubscribeEventMessageReceived(SubscribeEventRequestMessage requestMessage) {

    }

    @Override
    public void onScanEventMessageReceived(ScanEventRequestMessage requestMessage) {

    }

    @Override
    public void onUnsubscribeEventMessageReceived(UnsubscribeEventRequestMessage requestMessage) {

    }
}
