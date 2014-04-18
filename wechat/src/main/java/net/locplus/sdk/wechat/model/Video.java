package net.locplus.sdk.wechat.model;

/**
 * 视频内容
 * Created by Administrator on 2014/4/18.
 */
public class Video {
    /**
     * 通过上传多媒体文件，得到的id
     */
    private long MediaId;
    /**
     * 视频消息的标题
     */
    private String Title;
    /**
     * 视频消息的描述
     */
    private String Description;

    public long getMediaId() {
        return MediaId;
    }

    public void setMediaId(long mediaId) {
        MediaId = mediaId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
