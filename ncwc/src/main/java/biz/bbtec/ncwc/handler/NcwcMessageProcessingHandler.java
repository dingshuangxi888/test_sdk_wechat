package biz.bbtec.ncwc.handler;

import biz.bbtec.ncwc.handler.event.click.*;
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
        String event = requestMessage.getEventKey();
        ClickEventState state = null;
        if ("DEVICE_LIST".equals(event)) {
            state = new DeviceListClickEventState();
        }
        if ("UNBIND_USER".equals(event)) {
            state = new UnbindUserClickEventState();
        }
        if ("GET_HELP".equals(event)) {
            state = new GetHelpClickEventState();
        }
        ClickEventContext context = new ClickEventContext(state);
        this.responseMessage = context.request(requestMessage);
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
