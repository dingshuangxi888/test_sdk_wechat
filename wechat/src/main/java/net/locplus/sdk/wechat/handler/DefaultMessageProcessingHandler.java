package net.locplus.sdk.wechat.handler;

import net.locplus.sdk.wechat.model.Articles;
import net.locplus.sdk.wechat.model.Image;
import net.locplus.sdk.wechat.model.req.event.*;
import net.locplus.sdk.wechat.model.req.normal.*;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;
import net.locplus.sdk.wechat.model.resp.ImageResponseMessage;
import net.locplus.sdk.wechat.model.resp.NewsResponseMessage;
import net.locplus.sdk.wechat.model.resp.TextResponseMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/4/22.
 */
public class DefaultMessageProcessingHandler implements MessageProcessingHandler {

    private BaseResponseMessage responseMessage;

    @Override
    public BaseResponseMessage getResponseMessage() {
        return this.responseMessage;
    }

    @Override
    public void onTextMessageReceived(TextRequestMessage requestMessage) {
        TextResponseMessage message = new TextResponseMessage();
        message.setToUserName(requestMessage.getFromUserName());
        message.setFromUserName(requestMessage.getToUserName());
        message.setCreateTime(System.currentTimeMillis());
        message.setContent("我是圣贤！");
        message.setMsgType(MsgTypes.TEXT.getType());
        this.responseMessage = message;
    }

    @Override
    public void onImageMessageReceived(ImageRequestMessage requestMessage) {

        ImageResponseMessage message = new ImageResponseMessage();
        message.setToUserName(requestMessage.getFromUserName());
        message.setFromUserName(requestMessage.getToUserName());
        message.setCreateTime(System.currentTimeMillis());
        message.setMsgType(MsgTypes.IMAGE.getType());
        Image image = new Image();
        image.setMediaId(23123123L);
        message.setImage(image);
        this.responseMessage = message;
    }

    @Override
    public void onVoiceMessageReceived(VoiceRequestMessage requestMessage) {
        NewsResponseMessage message = new NewsResponseMessage();
        message.setToUserName(requestMessage.getFromUserName());
        message.setFromUserName(requestMessage.getToUserName());
        message.setCreateTime(System.currentTimeMillis());
        message.setMsgType(MsgTypes.NEWS.getType());
        message.setArticleCount(2);
        List<Articles> articles = new ArrayList<Articles>(2);
        Articles article0 = new Articles();
        article0.setTitle("article0");
        article0.setDescription("this is article0");
        article0.setPicUrl("d:/article0");
        article0.setUrl("http://article0");
        articles.add(article0);
        Articles article1 = new Articles();
        article1.setTitle("article0");
        article1.setDescription("this is article0");
        article1.setPicUrl("d:/article0");
        article1.setUrl("http://article0");
        articles.add(article1);
        message.setArticles(articles);
        this.responseMessage = message;
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
