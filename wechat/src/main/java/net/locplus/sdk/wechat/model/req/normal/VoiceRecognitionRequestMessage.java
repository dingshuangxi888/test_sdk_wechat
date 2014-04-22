package net.locplus.sdk.wechat.model.req.normal;

/**
 * 接受语音识别结果
 * http://mp.weixin.qq.com/wiki/index.php?title=%E6%8E%A5%E6%94%B6%E8%AF%AD%E9%9F%B3%E8%AF%86%E5%88%AB%E7%BB%93%E6%9E%9C
 * Created by Administrator on 2014/4/18.
 */
public class VoiceRecognitionRequestMessage extends BaseNormalRequestMessage {
    /**
     * 语音消息媒体id，可以调用多媒体文件下载接口拉取该媒体
     */
    private long MediaId;
    /**
     * 语音格式：amr
     */
    private String Format;
    /**
     * 语音识别结果，UTF8编码
     */
    private String Recognition;
}
