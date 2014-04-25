package net.locplus.sdk.wechat.model.resp;

/**
 * 回复文本消息
 * Created by Administrator on 2014/4/18.
 */
public class TextResponseMessage extends BaseResponseMessage {
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
