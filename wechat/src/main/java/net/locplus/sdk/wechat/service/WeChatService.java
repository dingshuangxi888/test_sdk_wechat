package net.locplus.sdk.wechat.service;

import com.thoughtworks.xstream.XStream;
import net.locplus.sdk.wechat.model.req.AllRequestMessage;
import net.locplus.sdk.wechat.model.req.BaseRequestMessage;
import net.locplus.sdk.wechat.model.req.SignatureMessage;
import net.locplus.sdk.wechat.util.WeChatUtil;
import net.locplus.sdk.wechat.util.XStreamFactory;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

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
