package net.locplus.sdk.wechat.model.resp;

import net.locplus.sdk.wechat.model.Music;

/**
 * 回复音乐消息
 * Created by Administrator on 2014/4/18.
 */
public class MusicResponseMessage extends BaseResponseMessage {
    /**
     * 音乐内容
     */
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }
}
