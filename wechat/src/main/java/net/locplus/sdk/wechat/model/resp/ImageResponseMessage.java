package net.locplus.sdk.wechat.model.resp;

import net.locplus.sdk.wechat.model.Image;
import net.locplus.sdk.wechat.model.req.BaseRequestMessage;

/**
 * 回复图片消息
 * Created by Administrator on 2014/4/18.
 */
public class ImageResponseMessage extends BaseResponseMessage {

    /**
     * 图片内容
     */
    private Image Image;

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }
}
