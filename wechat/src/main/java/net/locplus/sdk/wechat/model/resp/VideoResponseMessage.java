package net.locplus.sdk.wechat.model.resp;

import net.locplus.sdk.wechat.model.Video;
import net.locplus.sdk.wechat.model.req.BaseRequestMessage;

/**
 * 回复视频消息
 * Created by Administrator on 2014/4/18.
 */
public class VideoResponseMessage extends BaseResponseMessage {
    /**
     * 视频内容
     */
    private Video Video;

    public Video getVideo() {
        return Video;
    }

    public void setVideo(Video video) {
        Video = video;
    }
}
