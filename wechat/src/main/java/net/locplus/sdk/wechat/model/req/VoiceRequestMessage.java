package net.locplus.sdk.wechat.model.req;

/**
 * 语音消息
 * Created by Administrator on 2014/4/18.
 */
public class VoiceRequestMessage extends BaseRequestMessage {
    /**
     * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private long MediaId;
    /**
     * 语音格式，如amr，speex等
     */
    private String Format;

    public long getMediaId() {
        return MediaId;
    }

    public void setMediaId(long mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}
