package net.locplus.sdk.wechat.model.req;

/**
 * 文本消息
 * Created by Administrator on 2014/4/18.
 */
public class TextRequestMessage extends BaseRequestMessage {
    /**
     * 文本消息内容
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
