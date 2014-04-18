package net.locplus.sdk.wechat.model.req;

/**
 * 验证消息真实性
 * http://mp.weixin.qq.com/wiki/index.php?title=%E9%AA%8C%E8%AF%81%E6%B6%88%E6%81%AF%E7%9C%9F%E5%AE%9E%E6%80%A7
 * Created by Administrator on 2014/4/18.
 */
public class SignatureMessage {
    /**
     * 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     */
    private String signature;
    /**
     * 时间戳
     */
    private long timestamp;
    /**
     * 随机数
     */
    private int nonce;
    /**
     * 随机字符串
     */
    private String echostr;

    public SignatureMessage(String signature, long timestamp, int nonce, String echostr) {
        this.signature = signature;
        this.timestamp = timestamp;
        this.nonce = nonce;
        this.echostr = echostr;
    }
}
