package net.locplus.sdk.wechat.model.req.normal;

import net.locplus.sdk.wechat.model.req.BaseRequestMessage;

/**
 * 接受普通消息 基类
 * http://mp.weixin.qq.com/wiki/index.php?title=%E6%8E%A5%E6%94%B6%E6%99%AE%E9%80%9A%E6%B6%88%E6%81%AF
 * 当普通微信用户向公众账号发消息时，微信服务器将POST消息的XML数据包到开发者填写的URL上。各消息类型的推送XML数据包结构如下。
 * Created by Administrator on 2014/4/18.
 */
public class BaseNormalRequestMessage extends BaseRequestMessage {


    /**
     * 消息id，64位整型
     */
    private long MsgId;

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }
}
