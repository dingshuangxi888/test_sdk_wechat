package net.locplus.sdk.wechat.model.resp;

import net.locplus.sdk.wechat.model.Voice;

/**
 * 回复语音消息
 * Created by Administrator on 2014/4/18.
 */
public class VoiceResponseMessage extends BaseResponseMessage {
    /**
     * Voice 内容
     */
    private Voice Voice;

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        Voice = voice;
    }
}
