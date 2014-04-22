package net.locplus.sdk.wechat.model.req.normal;

/**
 * 图片消息
 * Created by Administrator on 2014/4/18.
 */
public class ImageRequestMessage extends BaseNormalRequestMessage {
    /**
     * 图片链接
     */
    private String PicUrl;
    /**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private long MediaId;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public long getMediaId() {
        return MediaId;
    }

    public void setMediaId(long mediaId) {
        MediaId = mediaId;
    }
}
