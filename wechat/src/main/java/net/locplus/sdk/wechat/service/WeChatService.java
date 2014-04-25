package net.locplus.sdk.wechat.service;

import net.locplus.sdk.wechat.model.req.SignatureMessage;
import net.locplus.sdk.wechat.util.WeChatUtil;

/**
 * Created by Administrator on 2014/4/21.
 */
public class WeChatService {

    /**
     * 签名检查
     *
     * @param signatureMessage
     * @param token
     * @return
     */
    public static boolean checkSignature(SignatureMessage signatureMessage, String token) {

        return WeChatUtil.checkSignature(signatureMessage.getSignature(), signatureMessage.getTimestamp(), signatureMessage.getNonce(), token);
    }


}
