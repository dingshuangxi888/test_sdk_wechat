package net.locplus.sdk.wechat.model.req.normal;

/**
 * 视频消息
 * Created by Administrator on 2014/4/18.
 */
public class VideoRequestMessage extends BaseNormalRequestMessage {
    /**
     * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private long MediaId;
    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private long ThumbMediaId;

    public long getMediaId() {
        return MediaId;
    }

    public void setMediaId(long mediaId) {
        MediaId = mediaId;
    }

    public long getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(long thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
}
