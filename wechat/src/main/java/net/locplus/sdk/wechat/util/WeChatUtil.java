package net.locplus.sdk.wechat.util;

import com.thoughtworks.xstream.XStream;
import net.locplus.sdk.wechat.model.req.AllRequestMessage;
import net.locplus.sdk.wechat.model.req.SignatureMessage;
import org.apache.commons.codec.digest.DigestUtils;
import sun.security.krb5.internal.PAData;

import java.util.*;

/**
 * Created by Administrator on 2014/4/22.
 */
public class WeChatUtil {

    public static final boolean checkSignature(String signature, String timestamp, String nonce, String token) {

        List<String> params = new ArrayList<String>();
        params.add(token);
        params.add(timestamp);
        ;
        params.add(nonce);

        Collections.sort(params, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        String tmp = new StringBuilder(params.get(0)).append(params.get(1)).append(params.get(2)).toString();
        return DigestUtils.sha1Hex(tmp).equals(signature);
    }

    public static AllRequestMessage parseRequestMessage(String xmlString) {
        XStream xs = XStreamFactory.init(false);
        xs.ignoreUnknownElements();
        ;
        xs.alias("xml", AllRequestMessage.class);
        AllRequestMessage msg = (AllRequestMessage) xs.fromXML(xmlString);
        return msg;
    }

    public static String upperFirst(String str) {
        byte[] items = str.toLowerCase().getBytes();
        items[0] = (byte)((char)items[0]-'a'+'A');
        return new String(items);
    }

    public static void main(String[] args) {
        String xmlString = "<xml>\n" +
                "<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                "<FromUserName><![CDATA[FromUser]]></FromUserName>\n" +
                "<CreateTime>123456789</CreateTime>\n" +
                "<MsgType><![CDATA[event]]></MsgType>\n" +
                "<Event><![CDATA[CLICK]]></Event>\n" +
                "<EventKey><![CDATA[EVENTKEY]]></EventKey>\n" +
                "</xml>";
        parseRequestMessage(xmlString);
    }
}
