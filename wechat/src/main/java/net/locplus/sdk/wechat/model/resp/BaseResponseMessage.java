package net.locplus.sdk.wechat.model.resp;

/**
 * 发送被动响应消息的基类
 * http://mp.weixin.qq.com/wiki/index.php?title=%E5%8F%91%E9%80%81%E8%A2%AB%E5%8A%A8%E5%93%8D%E5%BA%94%E6%B6%88%E6%81%AF
 * 对于每一个POST请求，开发者在响应包（Get）中返回特定XML结构，对该消息进行响应（现支持回复文本、图片、图文、语音、视频、音乐）。请注意，回复图片等多媒体消息时需要预先上传多媒体文件到微信服务器，只支持认证服务号。
 * Created by Administrator on 2014/4/18.
 */
public class BaseResponseMessage {
    /**
     * 接收方帐号（收到的OpenID）
     */
    private String ToUserName;
    /**
     * 开发者微信号
     */
    private String FromUserName;
    /**
     * 消息创建时间 （整型）
     */
    private long CreateTime;
    /**
     * 消息类型
     */
    private String MsgType;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
