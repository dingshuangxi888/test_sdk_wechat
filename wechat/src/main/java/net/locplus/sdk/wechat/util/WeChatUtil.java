package net.locplus.sdk.wechat.util;

import com.thoughtworks.xstream.XStream;
import net.locplus.sdk.wechat.model.Articles;
import net.locplus.sdk.wechat.model.req.AllRequestMessage;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2014/4/22.
 */
public class WeChatUtil {

    public static final boolean checkSignature(String signature, String timestamp, String nonce, String token) {

        List<String> params = new ArrayList<String>();
        params.add(token);
        params.add(timestamp);
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
        xs.alias("xml", AllRequestMessage.class);
        AllRequestMessage msg = (AllRequestMessage) xs.fromXML(xmlString);
        return msg;
    }

    public static String parseResponseMessage(Object obj) {
        XStream xs = XStreamFactory.init(true);
        xs.alias("xml", obj.getClass());
        xs.alias("item", Articles.class);
        return xs.toXML(obj);
    }

    public static String upperFirst(String str) {
        byte[] items = str.toLowerCase().getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    public static String inputStream2String(InputStream in) throws IOException {
        if (in == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        byte[] b = new byte[4096];
        for (int i; (i = in.read(b)) != -1; ) {
            sb.append(new String(b, 0, i, "UTF-8"));
        }
        return sb.toString();
    }
}
